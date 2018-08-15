package com.revature.caliber.revpro.beans;

/**
 * Degree info from RevPro
 * 
 * @author Patrick Walsh
 *
 */
public class RevProEducation {

	private String degree;
	private String major;
	
	public RevProEducation() {
		super();
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@Override
	public String toString() {
		return "RevProEducation [degree=" + degree + ", major=" + major + "]";
	}
	
}
