package com.revature.caliber.assessments.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import java.util.Set;

@Entity(name = "CALIBER_ASSESSMENT_CATEGORY")
public class Category {

	@Id
	@Column(name = "CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_ID_SEQUENCE")
	@SequenceGenerator(name = "CATEGORY_ID_SEQUENCE", sequenceName = "CATEGORY_ID_SEQUENCE")
    private int categoryId;
	
	@Column(name = "CATEGORY_SKILL")
    private String skillCategory;

    // Bi-directional mapping -- to avoid recursion, make DTO to send to UI
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private Set<Assessment> assessments;

    private Set<Integer> weeks;

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

    public Category(int categoryId, String skillCategory, Set<Assessment> assessments, Set<Integer> weeks) {
        super();
        this.categoryId = categoryId;
        this.skillCategory = skillCategory;
        this.assessments = assessments;
        this.weeks = weeks;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", skillCategory='" + skillCategory + '\'' +
                ", assessments=" + assessments +
                ", weeks=" + weeks +
                '}';
    }

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

    public Set<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(Set<Assessment> assessments) {
        this.assessments = assessments;
    }

    public Set<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(Set<Integer> weeks) {
        this.weeks = weeks;
    }


}
