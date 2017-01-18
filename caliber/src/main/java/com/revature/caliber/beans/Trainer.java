package com.revature.caliber.beans;

import java.util.Set;

public class Trainer {

	private int traineeId;
	private String name;
	private String title;
	private String email;
	private String salesforceAccount;
	private String salesforceAuthenticationToken;
	private Tier tier;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	private Set<Batch> batches;
	
}
