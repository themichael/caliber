package com.revature.caliber.training.data;

import java.util.List;

import com.revature.caliber.training.beans.Week;

public interface WeekDAO {
	List<Week> getAllWeeks();
	Week getWeek(Integer weekId);
	void createWeek(Week week);
}
