package com.revature.caliber.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.caliber.beans.Batch;

/**
 * Spring Data operations for the type {@link Batch}
 * @author Emily Higgins
 *
 */
@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer>{

}
