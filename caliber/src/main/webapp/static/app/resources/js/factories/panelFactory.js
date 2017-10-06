/**
 * 
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

	// Get feedback for trainee
	panel.getTraineeFeedback = function (id) {
		return $http({
			url: '/panelfeedback/' + id + '/',
			method: 'GET'
		}).then(function (response) {
			$log.debug('Trainee feedback successfully retreived');
			$log.debug(response);
			return response.data;
		}, function (error) {
			$log.error('There was an error: ' + error.data);
		});
	};

	// TODO: Update feedback

	// TODO: Delete feedback by id
	
});
