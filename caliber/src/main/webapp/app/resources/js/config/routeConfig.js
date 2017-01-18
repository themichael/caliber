angular.module("app").config(function($stateProvider, $locationProvider, $urlRouterProvider){
	// go to home on startup
	$urlRouterProvider.otherwise('test');
	
	// app states
	$stateProvider
		// public area
		.state("test",{
			url: "/test",
			templateUrl: "app/partials/main.html",
		})
  
});