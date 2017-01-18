package com.revature.caliber.training.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="CALIBER_TRAINER")
public class Trainer {

	@Id
	@Column(name="TRAINER_ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAINER_ID_SEQUENCE")
	@SequenceGenerator(name = "TRAINER_ID_SEQUENCE", sequenceName= "TRAINER_ID_SEQUENCE")
	@NotNull
	private int trainerId;
	
	@Column(name="NAME", nullable=false)
	@NotNull
	private String name;
	
	@Column(name="TITLE", nullable=false)
	@NotNull
	private String title;
	
	@Column(name="EMAIL", nullable=false)
	@Email
	@NotNull
	private String email;
	
	@Column(name="SALESFORCE_ACCOUNT", nullable=false)
	@NotNull
	private String salesforceAccount;
	
	@Column(name="SALESFORCE_AUTHENTICATION_TOKEN", nullable=false)
	@NotNull
	private String salesforceAuthenticationToken;
	
	@Column(name="SALESFORCE_REFRESH_TOKEN", nullable=false)
	@NotNull
	private String salesforceRefreshToken;
	
	@JoinColumn(name="TIER", nullable=false)
	@NotNull
	private Tier tier;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	@OneToMany(mappedBy="trainer")
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

	public String getSalesforceRefreshToken() {
		return salesforceRefreshToken;
	}

	public void setSalesforceRefreshToken(String salesforceRefreshToken) {
		this.salesforceRefreshToken = salesforceRefreshToken;
	}

	public Trainer(int trainerId, String name, String title, String email, String salesforceAccount, 
			String salesforceAuthenticationToken, String salesforceRefreshToken, Tier tier) {
		super();
		this.trainerId = trainerId;
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
	
}
