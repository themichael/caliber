/**
 * API that makes panel related AJAX calls to the back-end
 * @author Nathan Koszuta
 * @author Daniel Fairbanks
 * @author Emma Bownes
 * 
 * @param $log
 * @param $http
 * @returns {{}}
 */

angular.module('api').factory('panelFactory', function($log, $http) {
	$log.debug('Booted Panel API');
	let panel = {};

	/* ******************* Panel Feedback ********************** */

	// Create new feedback for panel
	panel.createFeedback = function () {
		return $http({
			url: '/panelfeedback/create',
			method: 'POST'
		}).then(function (response) {
			$log.debug('Panel feedback successfully created');
			$log.debug(response);
			return response.data;
		}, function (error) {
			$log.error('There was an error: ' + error.data);
		});
	};

	// Get panel feedback for all trainees
	panel.getAllFeedbacks = function () {
		return $http({
			url: '/panelfeedback/all',
			method: 'GET'
		}).then(function (response) {
			$log.debug('Successfully retrieved panel feedback for all trainees');
			$log.debug(response);
			return response.data;
		}, function (error) {
			$log.error('There was an error: ' + error.data);
		});
	};

	// Get all feedback for trainee
	panel.getTraineeFeedback = function (traineeId) {
		return $http({
			url: '/panelfeedback/all/' + traineeId + '/',
			method: 'GET'
		}).then(function (response) {
			$log.debug('Trainee feedback successfully retreived');
			$log.debug(response);
			return response.data;
		}, function (error) {
			$log.error('There was an error: ' + error.data);
		});
	};

	// Update feedback by id
	panel.updateFeedback = function (feedbackId) {
		return $http({
			url: '/panelfeedback/update/' + feedbackId + '/',
			method: 'PUT'
		}).then(function (response) {
			$log.debug('Feeback #' + feedbackId + ' successfully updated');
			$log.debug(response);
			return response.data;
		}, function (error) {
			$log.error('There was an error: ' + error.data);
		});
	};

	// Delete feedback by id
	panel.deleteFeedback = function (feedbackId) {
		return $http({
			url: '/panelfeedback/delete/' + feedbackId + '/',
			method: 'DELETE'
		}).then(function (response) {
			$log.debug('Feeback #' + feedbackId + ' successfully deleted');
			$log.debug(response);
			return response.data;
		}, function (error) {
			$log.error('There was an error: ' + error.data);
		});
	};
	
	/* ******************* Panel ********************** */
	
	// Get all panels for trainees in a batch
	panel.reportPanelTable = function(batchId) {
		return $http(
				{
					url : "/all/reports/batch/"+ batchId +"/panel-batch-all-trainees",
					method : "GET"
				}).then(function(response) {
			$log.debug("Agg - Batch - batchId -- success");
			$log.debug(response);
			return response.data;
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	}
	
	// Get all panels for a single trainee
	panel.reportTraineePanels = function(traineeId) {
		return $http(
				{
					url : "/panel/trainee/" + traineeId,
					method : "GET"
				}).then(function(response) {
			$log.debug('Retrieve all panels for trainee successful');
			$log.debug(response);
			return response.data;
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	}
	
	// Get all repanels across all trainees
	panel.reportAllRepanels = function() {
		return $http(
				{
					url : "/panel/repanel/all",
					method : "GET"
				}).then(function(response) {
			$log.debug('Retrieve all repanels across all trainees successful');
			$log.debug(response);
			return response.data;
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	}
	
	// Update a panel
	panel.updatePanel = function(panelObj) {
		return $http(
				{
					url : "/panel/update",
					method : "PUT",
					data : panelObj
				}).then(function(response) {
			$log.debug('Panel updated successfully');
			$log.debug(response);
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	}
	
	// Create a panel
	panel.createPanel = function(panelObj) {
		return $http(
				{
					url : "/panel/create",
					method : "POST",
					data : panelObj
				}).then(function(response) {
			$log.debug('Panel created successfully');
			$log.debug(response);
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	}
	
	// Delete a panel by panelId
	panel.deletePanel = function(panelId) {
		return $http(
				{
					url : "/panel/delete/{panelId}",
					method : "DELETE"
				}).then(function(response) {
			$log.debug('Panel deleted successfully');
			$log.debug(response);
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	}
	
	return panel;
});
