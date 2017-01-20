package com.revature.caliber.data.implementations;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.training.beans.Tier;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.data.TierDAO;

public class TierDAOImplementationTest {
	private static ApplicationContext context;
	private static Logger logger;
	
    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
    }
    
    @Ignore
    public void createTierTest() {
    	
    	short id = 2;
    	short rank = 2;
    	TierDAO tDao = (TierDAO) context.getBean("tierDAO");
        Tier tier = new Tier();
        
        tier.setTierId(id);
        tier.setTier("QC");
        tier.setRanking(rank);
        
        tDao.createTier(tier);
       
    }
    
    @Ignore
    public void getTierByIdTest(){
    	
    	short num = 1;
        TierDAO tDAO = (TierDAO) context.getBean("tierDAO");

        Tier tier = tDAO.getTier(num);
    }
    
    @Ignore
    public void getTierByNameTest(){
    	
        TierDAO tDAO = (TierDAO) context.getBean("tierDAO");
        Tier tier = tDAO.getTier("VP");
    }
    
    @Ignore
    public void getAllTiers() {
    	TierDAO tDAO = (TierDAO) context.getBean("tierDAO");
    	List<Tier> tiers = new ArrayList<Tier>();
    	
    	tiers = tDAO.getAllTiers();
    }
    
    @Test
    public void getTrainersInTierTest() {
    	short num = 1;
    	
    	TierDAO tDao = (TierDAO) context.getBean("tierDAO");
        List<Trainer> trainers = tDao.getTrainersInTier(num);
        
        assertNotNull(trainers);
        assertNotEquals(0, trainers.size());
        
        for (Trainer t : trainers) {
        	System.out.println(t.getName());
        }
    }
    
    @Ignore
    public void deleteTier() {
    	TierDAO tDAO = (TierDAO) context.getBean("tierDAO");
    
    	Tier tier = tDAO.getTier("VP");
    	assertNotNull(tier);
    	
    	short id = tier.getTierId();
    	tDAO.deleteTier(tier);
    	
    	tier = tDAO.getTier(id);
    	assertNull(tier);
    }   
}
