package com.revature.salesforce.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Salesforce Data Transfer Object
 * (current as of 7/3/2017) 
 * @author Patrick Walsh
 *
 */
public class SalesforceTrainee {

	@JsonProperty("attributes")
	private Attributes attributes;
	
	@JsonProperty("Id")
	private String id;
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Training_Status__c")
	private String trainingStatus;
	
	@JsonProperty("Phone")
	private String phone;
	
	@JsonProperty("Email")
	private String email;
	
	@JsonProperty("MobilePhone")
	private String mobilePhone;

	@JsonProperty("Training_Batch__c")
	private String batchId;
	
	@JsonProperty("Training_Batch__r")
	private SalesforceBatch batch;
	
	@JsonProperty("rnm__Recruiter__r")
	private SalesforceRecruiter recruiter;
	
	@JsonProperty("Account")
	private SalesforceCollege college;
	
	@JsonProperty("eintern_current_project_completion_pct__c")
	private String projectCompletion;

	public SalesforceTrainee() {
		super();
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public SalesforceBatch getBatch() {
		return batch;
	}

	public void setBatch(SalesforceBatch batch) {
		this.batch = batch;
	}

	public SalesforceRecruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(SalesforceRecruiter recruiter) {
		this.recruiter = recruiter;
	}

	public SalesforceCollege getCollege() {
		return college;
	}

	public void setCollege(SalesforceCollege college) {
		this.college = college;
	}

	public String getProjectCompletion() {
		return projectCompletion;
	}

	public void setProjectCompletion(String projectCompletion) {
		this.projectCompletion = projectCompletion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + ((batchId == null) ? 0 : batchId.hashCode());
		result = prime * result + ((college == null) ? 0 : college.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((projectCompletion == null) ? 0 : projectCompletion.hashCode());
		result = prime * result + ((recruiter == null) ? 0 : recruiter.hashCode());
		result = prime * result + ((trainingStatus == null) ? 0 : trainingStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesforceTrainee other = (SalesforceTrainee) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (batchId == null) {
			if (other.batchId != null)
				return false;
		} else if (!batchId.equals(other.batchId))
			return false;
		if (college == null) {
			if (other.college != null)
				return false;
		} else if (!college.equals(other.college))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (projectCompletion == null) {
			if (other.projectCompletion != null)
				return false;
		} else if (!projectCompletion.equals(other.projectCompletion))
			return false;
		if (recruiter == null) {
			if (other.recruiter != null)
				return false;
		} else if (!recruiter.equals(other.recruiter))
			return false;
		if (trainingStatus == null) {
			if (other.trainingStatus != null)
				return false;
		} else if (!trainingStatus.equals(other.trainingStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesforceTrainee [attributes=" + attributes + ", id=" + id + ", name=" + name + ", trainingStatus="
				+ trainingStatus + ", phone=" + phone + ", email=" + email + ", mobilePhone=" + mobilePhone
				+ ", batchId=" + batchId + ", batch=" + batch + ", recruiter=" + recruiter + ", college=" + college
				+ ", projectCompletion=" + projectCompletion + "]";
	}
	
}
