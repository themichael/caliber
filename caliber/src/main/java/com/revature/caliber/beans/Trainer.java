package com.revature.caliber.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 * The type Trainer.
 */
@Entity
@Table(name="CALIBER_TRAINER")
public class Trainer {

	@Id
	@Column(name="TRAINER_ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAINER_ID_SEQUENCE")
	@SequenceGenerator(name = "TRAINER_ID_SEQUENCE", sequenceName= "TRAINER_ID_SEQUENCE")
	@NotNull
	@JsonProperty
    private int trainerId;
    
	@Column(name="NAME", nullable=false)
	@NotNull
	@JsonProperty
    private String name;
    
	@Column(name="TITLE", nullable=false)
	@NotNull
	@JsonProperty
    private String title;
    
	@Column(name="EMAIL", nullable=false, unique=true)
	@Email
	@NotNull
	@JsonProperty
    private String email;
    
	@Column(name="SF_ACCOUNT", nullable=false)
	@NotNull
	@JsonProperty
    private String salesforceAccount;
    
	@Column(name="SF_AUTHENTICATION_TOKEN", nullable=false)
	@NotNull
	@JsonProperty
    private String salesforceAuthenticationToken;
    
	@Column(name="SF_REFRESH_TOKEN", nullable=false)
	@NotNull
	@JsonProperty
    private String salesforceRefreshToken;
    
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "TIER", nullable = false)
	@JsonProperty
    private Tier tier;

	@OneToMany(mappedBy="trainer", fetch=FetchType.EAGER)
	@JsonIgnore
    private Set<Batch> batches;

    /**
     * Gets trainer id.
     *
     * @return the trainer id
     */
    public int getTrainerId() {
        return trainerId;
    }

    /**
     * Sets trainer id.
     *
     * @param trainerId the trainer id
     */
    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets salesforce account.
     *
     * @return the salesforce account
     */
    public String getSalesforceAccount() {
        return salesforceAccount;
    }

    /**
     * Sets salesforce account.
     *
     * @param salesforceAccount the salesforce account
     */
    public void setSalesforceAccount(String salesforceAccount) {
        this.salesforceAccount = salesforceAccount;
    }

    /**
     * Gets salesforce authentication token.
     *
     * @return the salesforce authentication token
     */
    public String getSalesforceAuthenticationToken() {
        return salesforceAuthenticationToken;
    }

    /**
     * Sets salesforce authentication token.
     *
     * @param salesforceAuthenticationToken the salesforce authentication token
     */
    public void setSalesforceAuthenticationToken(String salesforceAuthenticationToken) {
        this.salesforceAuthenticationToken = salesforceAuthenticationToken;
    }

    /**
     * Gets tier.
     *
     * @return the tier
     */
    public Tier getTier() {
        return tier;
    }

    /**
     * Sets tier.
     *
     * @param tier the tier
     */
    public void setTier(Tier tier) {
        this.tier = tier;
    }

    /**
     * Gets batches.
     *
     * @return the batches
     */
    public Set<Batch> getBatches() {
        return batches;
    }

    /**
     * Sets batches.
     *
     * @param batches the batches
     */
    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }

    /**
     * Instantiates a new Trainer.
     */
    public Trainer() {
        super();
    }

    /**
     * Instantiates a new Trainer.
     *
     * @param trainerId         the trainer id
     * @param name              the name
     * @param title             the title
     * @param email             the email
     * @param salesforceAccount the salesforce account
     * @param tier              the tier
     */
    public Trainer(int trainerId, String name, String title, String email, String salesforceAccount, Tier tier) {
        super();
        this.trainerId = trainerId;
        this.name = name;
        this.title = title;
        this.email = email;
        this.salesforceAccount = salesforceAccount;
        this.tier = tier;
    }

    /**
     * Instantiates a new Trainer.
     *
     * @param trainerId         the trainer id
     * @param name              the name
     * @param title             the title
     * @param email             the email
     * @param salesforceAccount the salesforce account
     * @param tier              the tier
     * @param batches           the batches
     */
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

    /**
     * Gets salesforce refresh token.
     *
     * @return the salesforce refresh token
     */
    public String getSalesforceRefreshToken() {
        return salesforceRefreshToken;
    }

    /**
     * Sets salesforce refresh token.
     *
     * @param salesforceRefreshToken the salesforce refresh token
     */
    public void setSalesforceRefreshToken(String salesforceRefreshToken) {
        this.salesforceRefreshToken = salesforceRefreshToken;
    }

	@Override
	public String toString() {
		return "Trainer{" +
				"trainerId=" + trainerId +
				", name='" + name + '\'' +
				", title='" + title + '\'' +
				", email='" + email + '\'' +
				", salesforceAccount='" + salesforceAccount + '\'' +
				", salesforceAuthenticationToken='" + salesforceAuthenticationToken + '\'' +
				", salesforceRefreshToken='" + salesforceRefreshToken + '\'' +
				", tier=" + tier +
				", batches=" + batches +
				'}';
	}
}
