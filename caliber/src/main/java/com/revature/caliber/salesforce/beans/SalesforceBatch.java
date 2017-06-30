package com.revature.caliber.salesforce.beans;

import java.util.Date;

import com.revature.caliber.beans.Batch;


public class SalesforceBatch {

	private String resourceId;
	private String trainingName;
	private String trainer;
	private Date startDate;
	
	
	
	
	public SalesforceBatch() {
		super();
	}


	public SalesforceBatch(String resourceId, String trainingName, String trainer, Date startDate) {
		super();
		this.resourceId = resourceId;
		this.trainingName = trainingName;
		this.trainer = trainer;
		this.startDate = startDate;
	}
	

	public String getResourceId() {
		return resourceId;
	}


	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}


	public String getTrainingName() {
		return trainingName;
	}


	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}


	public String getTrainer() {
		return trainer;
	}


	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	

	@Override
	public String toString() {
		return "SalesforceBatch [resourceId=" + resourceId + ", trainingName=" + trainingName + ", trainer=" + trainer
				+ ", startDate=" + startDate + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resourceId == null) ? 0 : resourceId.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((trainer == null) ? 0 : trainer.hashCode());
		result = prime * result + ((trainingName == null) ? 0 : trainingName.hashCode());
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
		SalesforceBatch other = (SalesforceBatch) obj;
		if (resourceId == null) {
			if (other.resourceId != null)
				return false;
		} else if (!resourceId.equals(other.resourceId))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (trainer == null) {
			if (other.trainer != null)
				return false;
		} else if (!trainer.equals(other.trainer))
			return false;
		if (trainingName == null) {
			if (other.trainingName != null)
				return false;
		} else if (!trainingName.equals(other.trainingName))
			return false;
		return true;
	}


	
	
}
