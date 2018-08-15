package com.revature.caliber.revpro.beans;

import java.util.List;
import java.util.Random;

/**
 * Trainees from RevPro
 * 
 * @author Patrick Walsh
 *
 */
public class RevProTrainee {

	private String salesforceId;
	private String firstName;
	private String lastName;
	private String trainingStatus;
	private String selectedStatus;
	private String phone;
	private String mobilePhone;
	private String recruiterName;
	private String recruiterEmail;
	private double currentProjectCompletionPercentage;
	private List<RevProEducation> educations;
	private List<ScreenerInformation> screenerInformation;

	public RevProTrainee() {
		super();
	}

	public String getSalesforceId() {
		return salesforceId;
	}

	public void setSalesforceId(String salesforceId) {
		this.salesforceId = salesforceId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}

	public String getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public String getRecruiterEmail() {
		return recruiterEmail;
	}

	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
	}

	public double getCurrentProjectCompletionPercentage() {
		return currentProjectCompletionPercentage;
	}

	public void setCurrentProjectCompletionPercentage(double currentProjectCompletionPercentage) {
		this.currentProjectCompletionPercentage = currentProjectCompletionPercentage;
	}

	public List<RevProEducation> getEducations() {
		return educations;
	}

	public void setEducations(List<RevProEducation> educations) {
		this.educations = educations;
	}

	public List<ScreenerInformation> getScreenerInformation() {
		return screenerInformation;
	}

	public void setScreenerInformation(List<ScreenerInformation> screenerInformation) {
		this.screenerInformation = screenerInformation;
	}

	@Override
	public String toString() {
		return "RevProTrainee [salesforceId=" + salesforceId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", trainingStatus=" + trainingStatus + ", mobilePhone=" + mobilePhone + ", recruiterEmail="
				+ recruiterEmail + "]";
	}

	/**
	 * TODO fix this critical issue Randomly generate unique email until RevPro
	 * gives us the trainee emails..
	 * 
	 * @return
	 */
	public String getEmail() {
		StringBuilder sb = new StringBuilder();
		sb.append(firstName.replaceAll("\\s", "").replaceAll("'", ".")).append(".")
				.append(lastName.replaceAll("\\s", "").replaceAll("'", ".")).append(".")
				.append(new Random().nextInt(10_000)).append("@revature.com");
		return sb.toString();
	}

}
