package com.revature.caliber.beans;

/**
 * Determines if notes are created by Trainer or QC 
 * and where they are applied (to the trainee or batch).
 * Public notes are made by QC that are not visible to trainers and below.
 * 
 * @author Patrick Walsh
 *
 */
public enum NoteType {
	TRAINEE, BATCH, QC_TRAINEE, QC_BATCH, PUBLIC_TRAINEE, PUBLIC_BATCH
}
