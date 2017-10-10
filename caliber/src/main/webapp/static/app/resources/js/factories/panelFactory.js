/**
 * API that makes panel related AJAX calls to the back-end
 * @author Nathan Koszuta
 * 
 * @param $log
 * @param $http
 * @returns {{}}
 */

angular.module('api').factory('panelFactory', function($log, $http) {
	$log.debug('Booted Trainer API');
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
			$log.debug('Feeback #' + feedback + ' successfully updated');
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
			$log.debug('Feeback #' + feedback + ' successfully deleted');
			$log.debug(response);
			return response.data;
		}, function (error) {
			$log.error('There was an error: ' + error.data);
		});
	};
	
});
