package com.revature.salesforce.beans;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class SalesforceTraineeResponse extends SalesforceResponse{

	@JsonProperty("records")
	@JsonSerialize(as=SalesforceTrainee[].class)
	private SalesforceTrainee[] records;
	
	public SalesforceTraineeResponse() {
		super();
	}

	public SalesforceTrainee[] getRecords() {
		return records;
	}

	public void setRecords(SalesforceTrainee[] records) {
		this.records = records;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(records);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) 
			return false;
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesforceTraineeResponse other = (SalesforceTraineeResponse) obj;
		if (!Arrays.equals(records, other.records))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesforceTraineeResponse [records=" + Arrays.toString(records) + "]";
	}

}
