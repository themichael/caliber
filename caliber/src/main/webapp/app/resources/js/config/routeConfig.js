angular.module("app").config(
    function ($stateProvider, $locationProvider, $urlRouterProvider,
              ChartJsProvider) {

        // chart options
        ChartJsProvider.setOptions({
            chartColors: ['rgba(128, 54, 144, 0.5)', 'rgba(0, 173, 249, 0.5)',
                          'rgba(255, 255, 102, 0.5)', 'rgba(253, 180, 92, 0.5)', 
                          'rgba(148, 159, 177, 0.5)', 'rgba(77, 83, 96, 0.5)']
        });

        // go to home on startup
        $urlRouterProvider.otherwise('/routing');

        // app states
        $stateProvider
            .state("routing", {
                url: "/routing",
                templateUrl: "app/partials/helloWorld.html"
            })

            // qc
            .state("qc", {
                abstract: true,
                url: "/qc",
                templateUrl: "app/partials/abstracts/qc.html"
            })
            .state("qc.home", {
                url: "/home",
                templateUrl: "app/partials/home/qc-home.html",
                controller: "qcHomeController"
            })
           
			// trainer
			.state("trainer", {
				abstract : true,
				url : "/trainer",
				templateUrl : "app/partials/abstracts/trainer.html"
			}).state("trainer.home", {
				templateUrl : "app/partials/home/trainer-home.html",
				url : "/home",
				controller: "trainerHomeController"
			})

            // vp
            .state("vp", {
                abstract: true,
                templateUrl: "app/partials/abstracts/vp.html",
                url: "/vp"
            })
            .state("vp.home", {
                templateUrl: "app/partials/home/vp-home.html",
                url: "/home",
                controller: "vpHomeController"
            });
    });