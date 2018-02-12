package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.PanelStatus;
import com.revature.caliber.model.SimplePanelFeedback;

@Repository
public interface PanelFeedbackRepository extends JpaRepository<SimplePanelFeedback, Long> {
	
	List<SimplePanelFeedback> findByPanelId(int id);
	
	List<SimplePanelFeedback> findByPanelIdAndStatus(int id, PanelStatus status);
	
	void deleteByPanelId(int panelId);
}
