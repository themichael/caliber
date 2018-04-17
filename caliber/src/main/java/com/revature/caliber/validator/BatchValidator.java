package com.revature.caliber.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.revature.caliber.beans.Batch;

@Component
public class BatchValidator implements ConstraintValidator<ValidBatch, Batch> {

	@Override
	public void initialize(ValidBatch constraint) {
		// unused lifecycle method
	}

	@Override
	public boolean isValid(Batch batch, ConstraintValidatorContext ctx) {
		if (batch == null) {
			return false;
		}
		
		boolean endDateBeforeStartDate = batch.getEndDate().before(batch.getStartDate());
		boolean poorGradeGreaterThanGoodGrade = batch.getBorderlineGradeThreshold() > batch.getGoodGradeThreshold();
		boolean cotrainerSameAsTrainer = false;

		if (batch.getTrainer() != null) {
			cotrainerSameAsTrainer = batch.getTrainer().equals(batch.getCoTrainer());
		}

		// validate the batch
		if (endDateBeforeStartDate || poorGradeGreaterThanGoodGrade || cotrainerSameAsTrainer) {
			return false;
		} else {
			return true;
		}
	}
}
