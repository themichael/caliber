package com.revature.caliber.training.service;

import com.revature.caliber.training.beans.Batch;

import java.util.List;

public interface BatchService {
    // Create a new batch
    public void createBatch(Batch batch);

    //Get all batches in a table
    public List<Batch> getAllBatch();

    // Get all batches associated to a trainer
    public List<Batch> getTrainerBatch(String name);

    // Get in active batches
    public List<Batch> getCurrentBatch();

    // Get all active batches associated to a trainer
    public List<Batch> getCurrentBatch(String name);

    // Get a batch with an id
    public Batch getBatch(Integer id);

    // Update a batch
    public void updateBatch(Batch batch);

    // Delete a batch
    public void deleteBatch(Batch batch);
}
