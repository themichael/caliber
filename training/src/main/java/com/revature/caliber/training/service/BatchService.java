package com.revature.caliber.training.service;

import com.revature.caliber.training.beans.Batch;

import java.util.List;

public interface BatchService {
    public void createBatch(Batch batch);
    public List<Batch> getAllBatch();
    public List<Batch> getTrainerBatch(String name);
    public List<Batch> getCurrentBatch();
    public List<Batch> getCurrentBatch(String name);
    public Batch getBatch(Integer id);
    public void updateBatch(Batch batch);
    public void deleteBatch(Batch batch);
}
