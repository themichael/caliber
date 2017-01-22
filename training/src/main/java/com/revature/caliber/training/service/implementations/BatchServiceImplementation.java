package com.revature.caliber.training.service.implementations;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.data.Facade;
import com.revature.caliber.training.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchServiceImplementation implements BatchService{

    Facade facade;
    @Autowired
    public void setFacade(Facade facade) { this.facade = facade; }

    public void createBatch(Batch batch)            {facade.createBatch(batch);}
    public List<Batch> getAllBatch()                {return facade.getAllBatch();}
    public List<Batch> getTrainerBatch(Integer id)  {return facade.getTrainerBatch(id);}
    public List<Batch> getCurrentBatch()            {return facade.getCurrentBatch();}
    public List<Batch> getCurrentBatch(Integer id)  {return facade.getCurrentBatch(id);}
    public Batch getBatch(Integer id)               {return facade.getBatch(id);}
    public void updateBatch(Batch batch)            {facade.updateBatch(batch);}
    public void deleteBatch(Batch batch)            {facade.deleteBatch(batch);}
}
