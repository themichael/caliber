package com.revature.caliber.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Category.
 */
@Entity
@Table(name = "CALIBER_CATEGORY")
public class Category implements Serializable {

	private static final long serialVersionUID = 3363756954535297728L;

	@Id
	@Column(name = "CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_ID_SEQUENCE")
	@SequenceGenerator(name = "CATEGORY_ID_SEQUENCE", sequenceName = "CATEGORY_ID_SEQUENCE")
	@JsonProperty(value = "categoryId")
	private int categoryId;

	@JsonProperty(value = "skillCategory")
	@Column(name = "SKILL_CATEGORY")
	private String skillCategory;

	/**
	 * Instantiates a new Category.
	 */
	public Category() {
		super();
	}

	/**
	 * Instantiates a new Category.
	 *
	 * @param skillCategory
	 *            the skill category
	 */
	public Category(String skillCategory) {
		super();
		this.skillCategory = skillCategory;
	}

	/**
	 * Instantiates a new Category.
	 *
	 * @param categoryId
	 *            the category id
	 * @param skillCategory
	 *            the skill category
	 */
	public Category(int categoryId, String skillCategory) {
		super();
		this.categoryId = categoryId;
		this.skillCategory = skillCategory;
	}

	/**
	 * Gets category id.
	 *
	 * @return the category id
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * Sets category id.
	 *
	 * @param categoryId
	 *            the category id
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Gets skill category.
	 *
	 * @return the skill category
	 */
	public String getSkillCategory() {
		return skillCategory;
	}

	/**
	 * Sets skill category.
	 *
	 * @param skillCategory
	 *            the skill category
	 */
	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}

	@Override
	public String toString() {
		return skillCategory;
	}

	
	

}