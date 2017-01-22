package com.revature.caliber.training.data;

        import com.revature.caliber.training.beans.Batch;

        import java.util.List;

/**
 * Batch Dao interface for data tier.
 */
public interface BatchDAO {

    // Create a new batch
    public void createBatch(Batch batch);

    //Get all batches in a table
    public List<Batch> getAllBatch();

    // Get all batches associated to a trainer
    public List<Batch> getTrainerBatch(Integer id);

    // Get in active batches
    public List<Batch> getCurrentBatch();

    // Get all active batches associated to a trainer
    public List<Batch> getCurrentBatch(Integer id);

    // Get a batch with an id
    public Batch getBatch(Integer id);

    // Update a batch
    public void updateBatch(Batch batch);

    // Delete a batch
    public void deleteBatch(Batch batch);
}
