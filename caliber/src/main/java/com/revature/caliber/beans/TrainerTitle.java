package com.revature.caliber.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Enum for Trainer Titles
 * 
 * @author Stanley Chouloute
 *
 */
public enum TrainerTitle implements Serializable{

//	@JsonProperty("Vice_President_of_Technology")
//	Vice_President_of_Technology("Vice President of Technology"),
	@JsonProperty("Lead_Trainer")
	Lead_Trainer("Lead Trainer"),
	@JsonProperty("Senior_Trainer")
	Senior_Trainer("Senior Trainer"),
	@JsonProperty("Senior_Java_Developer")
	Senior_Java_Developer("Senior Java Developer"),
	@JsonProperty("Senior_Technical_Manager")
	Senior_Technical_Manager("Senior Technical Manager");

	private String title;

	private TrainerTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	//This is needed to display the roles without the underscore
	@Override
	public String toString(){
		return title;
	}
	
	
}
