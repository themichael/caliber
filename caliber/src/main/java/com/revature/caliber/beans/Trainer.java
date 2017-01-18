package com.revature.caliber.beans;

import java.util.Set;

public class Trainer {

	private int trainerId;
	private String name;
	private String title;
	private String email;
	private String salesforceAccount;
	private String password;
	private String salesforceAuthenticationToken;
	private String salesforceRefreshToken;
	private Tier tier;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	private Set<Batch> batches;

	public int getTraineeId() {
		return trainerId;
	}

	public void setTraineeId(int traineeId) {
		this.trainerId = traineeId;
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
		this.trainerId = traineeId;
		this.name = name;
		this.title = title;
		this.email = email;
		this.salesforceAccount = salesforceAccount;
		this.tier = tier;
	}

	public Trainer(int traineeId, String name, String title, String email, String salesforceAccount, Tier tier,
			Set<Batch> batches) {
		super();
		this.trainerId = traineeId;
		this.name = name;
		this.title = title;
		this.email = email;
		this.salesforceAccount = salesforceAccount;
		this.tier = tier;
		this.batches = batches;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalesforceRefreshToken() {
		return salesforceRefreshToken;
	}

	public void setSalesforceRefreshToken(String salesforceRefreshToken) {
		this.salesforceRefreshToken = salesforceRefreshToken;
	}

	public Trainer(int trainerId, String name, String title, String email, String salesforceAccount, String password,
			String salesforceAuthenticationToken, String salesforceRefreshToken, Tier tier) {
		super();
		this.trainerId = trainerId;
		this.name = name;
		this.title = title;
		this.email = email;
		this.salesforceAccount = salesforceAccount;
		this.password = password;
		this.salesforceAuthenticationToken = salesforceAuthenticationToken;
		this.salesforceRefreshToken = salesforceRefreshToken;
		this.tier = tier;
	}
	
	
	
}
