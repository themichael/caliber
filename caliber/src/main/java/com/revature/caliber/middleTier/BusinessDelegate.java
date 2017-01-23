package com.revature.caliber.middleTier;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service(value = "businessDelegate")
public class BusinessDelegate {
    private Set<Batch> batches;

    public Set<Batch> getAllBatches() {
        return batches;
    }


    public Batch getBatchById() {
        return new Batch();
    }

    public BusinessDelegate() {
        batches = new HashSet<>();
        Batch batch;
        int[] batchId = {25, 31, 69, 40, 47, 34, 74, 67, 96, 91};
        int[] batchNumbers = {9048, 4018, 4508, 5660, 3748, 1391, 3572, 5979, 1771, 7993, 7503};
        String[] trainingNames = new String[batchNumbers.length];
        for (int i = 0; i < batchNumbers.length; i++) {
            trainingNames[i] = "batch" + batchNumbers[i];
        }
        String[] trainers = {"Jane Simpson", "Andrew Russell", "Karen Thompson", "Deborah Willis",
                "Christine Mitchell", "Paul Little", "Donald Barnes", "Betty Ramirez", "Albert Mills", "Theresa Murphy"};
        String[] coTrainers = {"Joseph Ramirez", "Patrick Matthews", "Wayne Hanson", "Theresa Hansen", "Rebecca Hansen",
                "Frances Gutierrez", "Eugene Day", "Frances Russell", "Victor Matthews", "Adam Reid"};
        String[] skillTypes = {"Atmel AVR", "eGain", "SSH", "Web Design", "Object Oriented Modeling", "AKTA", "XCAP",
                "Cvent", "Record Keeping", "FTPS"};
        String trainingType = "Revature";
        int[] startDates = {1429913095, 1436827021, 1441890633, 1432327856, 1428452953, 1453161711, 1423378033,
                1443416709, 1443077710, 1430841149};
        int[] endDates = {1459124694, 1478607103, 1467611004, 1480540985, 1455629335, 1478596131, 1484690696,
                1468452110, 1464374102, 1483964494};

        String[] locations = {"Wesoła", "Katsuta", "Xiangride", "Alfenas", "Pariaman", "Ayapel", "Monastirákion",
                "Крива Паланка", "Georgetown", "Papelón"};
        short[] goodGradeThresholds = {78, 43, 20, 68, 28, 78, 6, 22, 37, 64};
        short[] borderlineGradeThresholds = {31, 14, 60, 21, 27, 39, 53, 60, 46, 32};
        for (int i = 0; i < 10; i++) {
            batch = new Batch();
            batch.setBatchId(batchId[i]);
            batch.setBorderlineGradeThreshold(borderlineGradeThresholds[i]);
            batch.setTrainingName(trainingNames[i]);
            batch.setCoTrainer(new Trainer());
            batch.setEndDate(new Date(endDates[i]));
            batch.setGoodGradeThreshold(goodGradeThresholds[i]);
            batch.setLocation(locations[i]);
            batch.setSkillType(skillTypes[i]);
            batch.setStartDate(new Date(startDates[i]));
            batch.setTrainingType(trainingType);
            batches.add(new Batch());
        }
    }
}
