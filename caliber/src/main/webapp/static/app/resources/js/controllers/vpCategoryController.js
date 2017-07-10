/*******************************************************************************
 * Team: HKM Team Lead: Humberto Corea Authors: Humberto Corea, s Rex Toothman,
 * Carlos Vallejo
 * 
 * Humberto worked on adding functionality to deactivate an assessment no longer
 * being used in Reavture Rex worked on adding functionality that allows a vp to
 * add a new assessment Carlos worked on adding functionality to edit and
 * assessment. Humberto and Carlos are working concurrently in the edit
 * assessments to include the functionality to be able to toggle an assessment
 * from active to inactive.
 * ******************************************************************************
 */

angular.module("vp").controller(
		"vpCategoryController",
		function($scope, $log, caliberDelegate) {
			caliberDelegate.vp.getAllCategories().then(function(categories) {
				$log.debug(categories);
				$scope.categories = categories;
			});
			$scope.UpdateCategory = function(category) {
				caliberDelegate.vp.updateCategory($scope.category).then(
						function(category) {
							$log.debug("Category Updated: " + response);
						});
			};
			$scope.SaveCategory = function(categoryForm) {
				var newCategory = categoryForm;
				createCategoryObject(newCategory);
				caliberDelegate.vp.saveCategory(newCategory).then(
						function(response) {
							$log.debug("Category created: " + response);
						});
			};
			function createCategoryObject(category) {
				category = $scope.categoryForm;
				$log.debug(category);
			};

		});