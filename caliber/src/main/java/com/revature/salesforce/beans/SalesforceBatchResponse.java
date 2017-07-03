package com.revature.salesforce.beans;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class SalesforceBatchResponse extends SalesforceResponse{

	@JsonProperty("records")
	@JsonSerialize(as=SalesforceBatch[].class)
	private SalesforceBatch[] records;
	
	public SalesforceBatchResponse() {
		super();
	}

	public SalesforceBatch[] getRecords() {
		return records;
	}

	public void setRecords(SalesforceBatch[] records) {
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
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesforceBatchResponse other = (SalesforceBatchResponse) obj;
		if (!Arrays.equals(records, other.records))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesforceBatchResponse [records=" + Arrays.toString(records) + "]";
	}
	
}
