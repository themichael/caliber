package com.revature.caliber.training.beans;

import java.util.Set;


/**
 * Bean for Trainer
 */

public class Trainer {

	/**
	 * id for Trainer -- PK
	 */
	private int trainerId;

	/**
	 * Full name of the Trainer
	 */
	private String name;

	/**
	 * Title of the Trainer
	 */
	private String title;

	/**
	 * Email of the Trainer
	 */
	private String email;

	/**
	 * Salesforce account of the Trainer
	 */
	private String salesforceAccount;

	/**
	 * Salesforce authentication token of the Trainer
	 */
	private String salesforceAuthenticationToken;

	/**
	 * Salesforce refresh token of the Trainer
	 */
	private String salesforceRefreshToken;

	/**
	 * Tier of the Trainer
	 */
	private Tier tier;

	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI

	private Set<Batch> batches;

	public Trainer() {
		super();
	}

	public Trainer(String name, String title, String email, String salesforceAccount,
			String salesforceAuthenticationToken, String salesforceRefreshToken, Tier tier) {
		super();
		this.name = name;
		this.title = title;
		this.email = email;
		this.salesforceAccount = salesforceAccount;
		this.salesforceAuthenticationToken = salesforceAuthenticationToken;
		this.salesforceRefreshToken = salesforceRefreshToken;
		this.tier = tier;
	}

	public Trainer(String name, String title, String email, String salesforceAccount,
			String salesforceAuthenticationToken, String salesforceRefreshToken, Tier tier, Set<Batch> batches) {
		super();
		this.name = name;
		this.title = title;
		this.email = email;
		this.salesforceAccount = salesforceAccount;
		this.salesforceAuthenticationToken = salesforceAuthenticationToken;
		this.salesforceRefreshToken = salesforceRefreshToken;
		this.tier = tier;
		this.batches = batches;
	}

	public Trainer(int trainerId, String name, String title, String email, String salesforceAccount, Tier tier) {
		super();
		this.trainerId = trainerId;
		this.name = name;
		this.title = title;
		this.email = email;
		this.salesforceAccount = salesforceAccount;
		this.tier = tier;
	}

	public Trainer(int trainerId, String name, String title, String email, String salesforceAccount, Tier tier,
			Set<Batch> batches) {
		super();
		this.trainerId = trainerId;
		this.name = name;
		this.title = title;
		this.email = email;
		this.salesforceAccount = salesforceAccount;
		this.tier = tier;
		this.batches = batches;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
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

	public String getSalesforceRefreshToken() {
		return salesforceRefreshToken;
	}

	public void setSalesforceRefreshToken(String salesforceRefreshToken) {
		this.salesforceRefreshToken = salesforceRefreshToken;
	}

}
