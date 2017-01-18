package com.revature.caliber.training.beans;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Category {

	private int categoryId;
	private String skillCategory;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Category_Weeks")
	private Set<Week> weeks;
	
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getSkillCategory() {
		return skillCategory;
	}
	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}
	public Set<Week> getWeeks() {
		return weeks;
	}
	public void setWeeks(Set<Week> weeks) {
		this.weeks = weeks;
	}
	public Category() {
		super();
	}
	public Category(String skillCategory) {
		super();
		this.skillCategory = skillCategory;
	}
	public Category(int categoryId, String skillCategory) {
		super();
		this.categoryId = categoryId;
		this.skillCategory = skillCategory;
	}
	public Category(int categoryId, String skillCategory, Set<Week> weeks) {
		super();
		this.categoryId = categoryId;
		this.skillCategory = skillCategory;
		this.weeks = weeks;
	}
	
	
	
}
