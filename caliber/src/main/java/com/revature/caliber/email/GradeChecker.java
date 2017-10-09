package com.revature.caliber.email;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.services.AssessmentService;

/**
 * @author Andrew Bonds
 * 
 */

public class GradeChecker {

	
	static AssessmentService assess = new AssessmentService();
	
	

	
	
	public static void grabAssessments() {
		System.out.println("We in here");
		List<Assessment> list = assess.findAllAssessments();
		int num = list.size();
		System.out.println(num);
//		for(Assessment i : list) {
//			System.out.println("Assessment Id: " + i.getTitle());
//		}
		
	}
	
	public static void main(String[] args) {
		grabAssessments();
	}
	
	
}
