angular.module("app").config(function ($stateProvider, $locationProvider, $urlRouterProvider) {
    // go to home on startup
    $urlRouterProvider.otherwise('/routing');
    // app states
    $stateProvider.state("routing", {
    	url: "/routing",
   		templateUrl: "app/partials/helloWorld.html"
    })
    .state("qc", {
        abstract: true,
        url: "/qc",
        templateUrl: "app/partials/qc.html"
    })
    .state("trainer", {
    	abstract: true,
    	url: "/trainer",
    	templateUrl: "app/partials/abstracts/trainer.html"
    });
});