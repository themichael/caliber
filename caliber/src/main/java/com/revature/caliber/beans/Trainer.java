package com.revature.caliber.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

/**
 * The type Trainer.
 */
public class Trainer {

    @JsonProperty
    private int trainerId;
    @JsonProperty
    private String name;
    @JsonProperty
    private String title;
    @JsonProperty
    private String email;
    @JsonProperty
    private String salesforceAccount;
    @JsonProperty
    private String salesforceAuthenticationToken;
    @JsonProperty
    private String salesforceRefreshToken;
    @JsonProperty
    private Tier tier;

    // Bi-directional mapping -- to avoid recursion, make DTO to send to UI
    private Set<Batch> batches;

    /**
     * Gets trainee id.
     *
     * @return the trainee id
     */
    public int getTraineeId() {
        return trainerId;
    }

    /**
     * Sets trainee id.
     *
     * @param traineeId the trainee id
     */
    public void setTraineeId(int traineeId) {
        this.trainerId = traineeId;
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
     * @param traineeId         the trainee id
     * @param name              the name
     * @param title             the title
     * @param email             the email
     * @param salesforceAccount the salesforce account
     * @param tier              the tier
     */
    public Trainer(int traineeId, String name, String title, String email, String salesforceAccount, Tier tier) {
        super();
        this.trainerId = traineeId;
        this.name = name;
        this.title = title;
        this.email = email;
        this.salesforceAccount = salesforceAccount;
        this.tier = tier;
    }

    /**
     * Instantiates a new Trainer.
     *
     * @param traineeId         the trainee id
     * @param name              the name
     * @param title             the title
     * @param email             the email
     * @param salesforceAccount the salesforce account
     * @param tier              the tier
     * @param batches           the batches
     */
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
