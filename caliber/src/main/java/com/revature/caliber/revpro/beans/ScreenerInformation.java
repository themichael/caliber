package com.revature.caliber.revpro.beans;

/**
 * Screening feedback from RevPro
 * @author Patrick Walsh
 *
 */
public class ScreenerInformation {
	
	private String screenerName;
	private String screenerFeedback; // hopefully becomes INT \(^_^ )/
	
	public ScreenerInformation() {
		super();
	}
	public String getScreenerName() {
		return screenerName;
	}
	public void setScreenerName(String screenerName) {
		this.screenerName = screenerName;
	}
	public String getScreenerFeedback() {
		return screenerFeedback;
	}
	public void setScreenerFeedback(String screenerFeedback) {
		this.screenerFeedback = screenerFeedback;
	}
	@Override
	public String toString() {
		return "ScreenerInformation [screenerName=" + screenerName + ", screenerFeedback=" + screenerFeedback + "]";
	}
	
}
