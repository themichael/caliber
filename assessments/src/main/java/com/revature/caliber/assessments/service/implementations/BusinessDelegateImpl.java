package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.data.Facade;
import com.revature.caliber.assessments.service.BusinessDelegate;

import java.util.HashSet;

public class BusinessDelegateImpl {

    private Facade facade;

//    Spring setter based DI
    public void setFacade(Facade facade) {
        this.facade = facade;
    }

}
