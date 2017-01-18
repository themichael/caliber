angular.module("app").config(function ($stateProvider, $locationProvider, $urlRouterProvider) {
    // go to home on startup
    $urlRouterProvider.otherwise('test');
    // app states
    $stateProvider.state("qc", {
        abstract: true,
        url: "/qc",
        templateUrl: "app/partials/qc.html"
    });
});