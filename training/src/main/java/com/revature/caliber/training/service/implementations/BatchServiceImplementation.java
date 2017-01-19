package com.revature.caliber.training.service.implementations;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.data.Facade;
import com.revature.caliber.training.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BatchServiceImplementation implements BatchService{

    Facade facade;
    @Autowired
    public void setFacade(Facade facade) { this.facade = facade; }

    public void createBatch(Batch batch) {facade.createBatch(batch);}
    public List<Batch> getAll() {return facade.getAllBatch();}
    public List<Batch> getTrainerBatch(String name) {return facade.getTrainerBatch(name);}
    public List<Batch> getCurrentBatch() {return facade.getCurrentBatch();}
    public List<Batch> getCurrentBatch(String name) {return facade.getCurrentBatch(name);}
    public Batch getBatch(Integer id) {return facade.getBatch(id);}
    public void updateBatch(Batch batch) {facade.updateBatch(batch);}
    public void deleteBatch(Batch batch) {facade.deleteBatch(batch);}
}
