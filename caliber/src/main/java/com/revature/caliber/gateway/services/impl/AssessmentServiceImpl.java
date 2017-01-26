package com.revature.caliber.gateway.services.impl;

import com.revature.caliber.gateway.services.AssessmentService;

public class AssessmentServiceImpl implements AssessmentService {
    private String hostname;
    private String portNumber;

    /////////// SETTERS ////////////////
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }
}
