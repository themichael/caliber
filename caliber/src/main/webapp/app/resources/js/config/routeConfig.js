angular.module("app").config(function ($stateProvider, $locationProvider, $urlRouterProvider) {
    // go to home on startup
    $urlRouterProvider.otherwise('/routing');
    
    // app states
    $stateProvider.state("routing", {
    	url: "/routing",
   		templateUrl: "app/partials/helloWorld.html"
    })
    
    // qc
    .state("qc", {
        abstract: true,
        url: "/qc",
        templateUrl: "app/partials/qc.html"
    })
    
    // trainer
    .state("trainer", {
    	abstract: true,
    	url: "/trainer",
    	templateUrl: "app/partials/abstracts/trainer.html"
    })
    
    // vp
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