package com.revature.caliber.training.data.implementations;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.data.BatchDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository(value = "batchDAO")
public class BatchDAOImplementation implements BatchDAO{

    private Session session;

    public BatchDAOImplementation() {}

    public void setSession(Session session) {this.session = session;}

    public void createBatch(Batch batch) {session.save(batch);}
    public List<Batch> getAll() {return session.createCriteria(Batch.class).list();}

    public List<Batch> getTrainerBatch(String name) {
        Criteria criteria = session.createCriteria(Batch.class);
        criteria.add(Restrictions.eq("trainer.name", name));
        return criteria.list();
    }

    public List<Batch> getCurrentBatch() {
        Criteria criteria = session.createCriteria(Batch.class);
        criteria.add(Restrictions.ge("startDate", new Date().getTime() ));
        criteria.add(Restrictions.le("endDate", new Date().getTime() ));
        return criteria.list();
    }

    public List<Batch> getCurrentBatch(String name) {
        Criteria criteria = session.createCriteria(Batch.class);
        criteria.add(Restrictions.eq("Trainer.name", name ));
        criteria.add(Restrictions.ge("startDate", new Date().getTime() ));
        criteria.add(Restrictions.le("endDate", new Date().getTime() ));
        return criteria.list();
    }

    public Batch getBatch(Integer id) {return (Batch)session.get(Batch.class, id);}
    public void updateBatch(Batch batch) {session.saveOrUpdate(batch);}
    public void deleteBatch(Batch batch) {session.delete(batch);}
}
