package com.revature.caliber.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Trainer.
 */
@Entity
@Table(name="CALIBER_TRAINER")
public class Trainer implements Serializable{

	private static final long serialVersionUID = -2546407792912483570L;

	@Id
	@Column(name="TRAINER_ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAINER_ID_SEQUENCE")
	@SequenceGenerator(name = "TRAINER_ID_SEQUENCE", sequenceName= "TRAINER_ID_SEQUENCE")
    @JsonProperty
    private int trainerId;
	
	@Column(name="NAME", nullable=false)
    @JsonProperty
    private String name;
	
	@Column(name="TITLE", nullable=false)
    @JsonProperty
    private String title;
	
	@Column(name="EMAIL", nullable=false, unique=true, updatable=false)
	@Email
    @JsonProperty
    private String email;

	@Enumerated(EnumType.STRING)
	@Column(name="TIER")
	private TrainerRole tier;

	@OneToMany(mappedBy="trainer", fetch=FetchType.LAZY)
	@JsonIgnore
    private Set<Batch> batches;

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TrainerRole getTier() {
		return tier;
	}

	public void setTier(TrainerRole tier) {
		this.tier = tier;
	}

	public Set<Batch> getBatches() {
		return batches;
	}

	public void setBatches(Set<Batch> batches) {
		this.batches = batches;
	}

	public Trainer() {
		super();
	}

	public Trainer(String name, String title, String email, TrainerRole tier) {
		super();
		this.name = name;
		this.title = title;
		this.email = email;
		this.tier = tier;
	}

	@Override
	public String toString() {
		return "Trainer [trainerId=" + trainerId + ", name=" + name + ", title=" + title + ", email=" + email
				+ ", tier=" + tier + "]";
	}
  
}
