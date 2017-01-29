package com.revature.caliber.training.beans;

/**
 * Bean for Trainee
 */
public class Trainee {

	/**
	 * id for Trainee (PK)
	 */
	private int traineeId;

	/**
	 * Full name of the Trainee
	 */
	private String name;

	/**
	 * Trainee's e-mail
	 */
	private String email;

	/**
	 * Status like "Signed", "Selected", etc.
	 */
	private String trainingStatus;

	/**
	 * Batch that the Trainee belongs to
	 */
	private Batch batch;

	public Trainee() {
		super();
	}

	public Trainee(String name, String email, String trainingStatus, Batch batch) {
		super();
		this.name = name;
		this.email = email;
		this.trainingStatus = trainingStatus;
		this.batch = batch;
	}

	public Trainee(int traineeId, String name, String email, String trainingStatus, Batch batch) {
		super();
		this.traineeId = traineeId;
		this.name = name;
		this.email = email;
		this.trainingStatus = trainingStatus;
		this.batch = batch;
	}

	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	public int getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

    @Override
    public String toString() {
        return "Trainee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", trainingStatus='" + trainingStatus + '\'' +
                ", batch=" + batch +
                '}';
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Trainee trainee = (Trainee) o;

		if (!name.equals(trainee.name)) return false;
		if (!email.equals(trainee.email)) return false;
		return trainingStatus.equals(trainee.trainingStatus);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + email.hashCode();
		result = 31 * result + trainingStatus.hashCode();
		return result;
	}
}
