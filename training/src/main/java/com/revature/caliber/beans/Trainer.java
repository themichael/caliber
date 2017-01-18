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

	public int getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalesforceAccount() {
		return salesforceAccount;
	}

	public void setSalesforceAccount(String salesforceAccount) {
		this.salesforceAccount = salesforceAccount;
	}

	public String getSalesforceAuthenticationToken() {
		return salesforceAuthenticationToken;
	}

	public void setSalesforceAuthenticationToken(String salesforceAuthenticationToken) {
		this.salesforceAuthenticationToken = salesforceAuthenticationToken;
	}

	public Tier getTier() {
		return tier;
	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}

	public Set<Batch> getBatches() {
		return batches;
	}

	public void setBatches(Set<Batch> batches) {
		this.batches = batches;
	}

	public Trainer() {
		super();
	}

	public Trainer(int traineeId, String name, String title, String email, String salesforceAccount, Tier tier) {
		super();
		this.traineeId = traineeId;
		this.name = name;
		this.title = title;
		this.email = email;
		this.salesforceAccount = salesforceAccount;
		this.tier = tier;
	}

	public Trainer(int traineeId, String name, String title, String email, String salesforceAccount, Tier tier,
			Set<Batch> batches) {
		super();
		this.traineeId = traineeId;
		this.name = name;
		this.title = title;
		this.email = email;
		this.salesforceAccount = salesforceAccount;
		this.tier = tier;
		this.batches = batches;
	}
	
	
	
}
