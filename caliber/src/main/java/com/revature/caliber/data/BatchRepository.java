package com.revature.caliber.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;

/**
 * Spring Data operations for the type {@link Batch}
 * @author Emily Higgins
 *
 */
@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer>{
	
	/**
	 * Save an batch to the database
	 *
	 * @param batch
	 * @return updated batch
	 */
	@SuppressWarnings("unchecked")
	public Batch save(Batch batch);

	/**
	 * Find batch with the specified id, including trainees who have been dropped
	 * 
	 * @param id
	 * @return batch
	 */
	public Batch findOne(int id);
	
	/**
	 * Find batch with the specified id, NOT including trainees who have been dropped
	 * TODO
	 * @param id
	 * @return batch
	 */
	
	/**
	 * Find all current batches, NOT including trainees who have been dropped
	 * TODO
	 * @return batch
	 */
	
	/**
	 * Find all current batches for the specified trainer or co-trainer, 
	 * NOT including trainees who have been dropped
	 * TODO
	 * @param trainerId
	 * @return list of batches
	 */
	
	
	
	/**
	 * Remove the specified batch from the database
	 * 
	 * @param batch
	 */
	public void delete(Batch batch);

	
	

	

}
