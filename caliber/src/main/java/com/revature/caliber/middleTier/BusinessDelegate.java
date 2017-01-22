package com.revature.caliber.middleTier;

import com.github.javafaker.Faker;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Tier;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
        Faker faker = new Faker();
        Set<Trainee> trainees = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            batches.add(new Batch(faker.number().numberBetween(1, 100000),
                    "batch" + faker.number().numberBetween(1000, 9999),
                    new Trainer(faker.number().numberBetween(1, 100000), faker.name().fullName(), faker.name().title(),
                            faker.internet().emailAddress(),
                            faker.superhero().name() + faker.number().numberBetween(1, 999), new Tier()),
                    new Trainer(faker.number().numberBetween(1, 100000), faker.name().fullName(), faker.name().title(),
                            faker.internet().emailAddress(),
                            faker.superhero().name() + faker.number().numberBetween(1, 999), new Tier()),
                    faker.educator().course(), "Revature", faker.date().past(36500, TimeUnit.DAYS),
                    faker.date().future(36500, TimeUnit.DAYS), faker.address().toString(),
                    (short) faker.number().numberBetween(80, 100), (short) faker.number().numberBetween(60, 80),
                    new HashSet<>(), new HashSet<>()));
        }
    }
}
