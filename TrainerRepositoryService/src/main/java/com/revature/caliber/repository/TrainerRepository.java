package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.model.SimpleTrainer;
import com.revature.caliber.model.TrainerRole;

@Repository
public interface TrainerRepository extends JpaRepository<SimpleTrainer, Integer> {

	/**
	 * Create a list of all Trainers without references to other tables
	 *
	 * @param
	 *
	 * @return List of SimpleTrainers
	 */
	@Query("select distinct t from SimpleTrainer t where t.tier<>com.revature.caliber.model.TrainerRole.ROLE_INACTIVE")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	List<SimpleTrainer> findAll();

	/**
	 * Find a single Trainer without references by trainer id
	 *
	 * @param id
	 *
	 * @return SimpleTrainer
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	SimpleTrainer findByTrainerId(int id);
	
	/**
	 * Create a list of all unique titles for trainers
	 *
	 * @param
	 *
	 * @return List of Strings
	 */
	@Query("select distinct t.title from SimpleTrainer t")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	List<String> findAllTrainerTitles();
	
	/**
	 * Find a single Trainer without references by email
	 *
	 * @param email
	 *
	 * @return SimpleTrainer
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	SimpleTrainer findByEmail(String email);
	
	/**
	 * Update a single trainer's name, title, tier by finding them by an id
	 *
	 * @param name, title,tier, userId
	 *
	 * @return
	 */
	@Modifying
	@Query("update SimpleTrainer t set t.name = ?1, t.title = ?2, t.tier = ?3 where t.trainerId = ?4")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateTrainerInfoById(String name, String title, TrainerRole tier, Integer userId);
}
