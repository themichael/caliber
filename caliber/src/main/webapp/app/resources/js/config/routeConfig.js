angular.module("app").config(function($stateProvider, $locationProvider, $urlRouterProvider){
	// go to home on startup
	$urlRouterProvider.otherwise('vp/home');
	
	// app states
	$stateProvider
		.state("test",{
			url: "/test",
			templateUrl: "app/partials/main.html",
		})
		.state("vp", {
			abstract: true,
			templateUrl: "app/partials/vp.html",
			url: "/vp"
		})
		.state("vp.home", {
			templateUrl: "app/partials/vp-home.html",
			url: "/home"
		});
  
});