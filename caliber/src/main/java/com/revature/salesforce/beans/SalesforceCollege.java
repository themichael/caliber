package com.revature.salesforce.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Salesforce Data Transfer Object
 * (current as of 7/3/2017) 
 * @author Patrick Walsh
 *
 */
public class SalesforceCollege {

	@JsonProperty("attributes")
	@JsonSerialize(as=Attributes.class)
	private Attributes attributes;
	
	@JsonProperty("Name")
	private String name;

	public SalesforceCollege() {
		super();
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SalesforceCollege other = (SalesforceCollege) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesforceCollege [attributes=" + attributes + ", name=" + name + "]";
	}
	
}
