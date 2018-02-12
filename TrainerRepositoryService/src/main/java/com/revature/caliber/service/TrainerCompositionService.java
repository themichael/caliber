package com.revature.caliber.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.model.Batch;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleTrainer;
import com.revature.caliber.model.Trainer;
import com.revature.caliber.repository.TrainerRepository;

@RestController
@RequestMapping(value = "/trainer")
public class TrainerCompositionService {

	@Autowired
	AmqpTemplate rabbitTemplate;

	@Autowired
	public TrainerRepository trainerRepository;

	@Autowired
	private TrainerCompositionMessagingService trainerCompositionMessagingService;

	/**
	 * Save a SimpleTrainer
	 *
	 * @param trainer
	 *
	 * @return
	 */
	public void save(Trainer trainer) {
		SimpleTrainer simpleTrainer = new SimpleTrainer(trainer);
		
		if(trainer.getTrainerId() == 0) simpleTrainer.setTrainerId(null);
		trainerRepository.save(simpleTrainer);
	}

	/**
	 * Update a Trainer
	 *
	 * @param trainer
	 *
	 * @return
	 */
	public void update(Trainer trainer) {
		save(trainer);
	}

	/**
	 * Delete a single Trainer
	 *
	 * @param trainer
	 *
	 * @return
	 */
	public void delete(Trainer trainer) {
		trainerRepository.delete(trainer.getTrainerId());
	}

	/**
	 * Find a single Trainer by trainerId
	 *
	 * @param trainerId
	 *
	 * @return Trainer
	 */
	public Trainer findOne(Integer trainerId) {
		SimpleTrainer basis = trainerRepository.findOne(trainerId);
		Trainer result = composeTrainer(basis);

		System.out.println(result);
		return result;
	}

	/**
	 * Find a single Trainer by email
	 *
	 * @param trainerId
	 *
	 * @return Trainer
	 */
	public Trainer findByEmail(String email) {
		SimpleTrainer basis = trainerRepository.findByEmail(email);
		Trainer result = composeTrainer(basis);

		return result;
	}

	/**
	 * Find all Trainers
	 *
	 * @param
	 *
	 * @return List of Trainers
	 */
	public List<Trainer> findAll() {
		List<Trainer> result = new ArrayList();
		List<SimpleTrainer> basis = trainerRepository.findAll();
		for (SimpleTrainer t : basis) {
			result.add(composeTrainer(t));
		}

		return result;
	}

	/**
	 * convert a SimpleTrainer in to a Trainer Trainer has a set of batches
	 * associated with it SimpleTrainer does not
	 *
	 * @param src
	 *
	 * @return Trainer
	 */
	private Trainer composeTrainer(SimpleTrainer src) {
		List<SimpleBatch> batchSet = trainerCompositionMessagingService
				.sendSingleSimpleBatchRequest((Integer) src.getTrainerId());

		Trainer dest = new Trainer(src);

		dest.setBatches(batchSet.stream().map(x -> new Batch(x)).collect(Collectors.toSet()));

		return dest;
	}

}