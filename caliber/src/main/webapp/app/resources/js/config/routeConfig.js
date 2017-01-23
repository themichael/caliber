angular.module("app").config(
    function ($stateProvider, $locationProvider, $urlRouterProvider,
              ChartJsProvider, $logProvider) {

    	// Turn on/off debug messages
    	$logProvider.debugEnabled(false);
    	
        // chart options
        ChartJsProvider.setOptions({
        	 chartColors: ['#803690', '#00adf9',
                 '#ffff66', '#fdb45c', 
                 '#949fb1', '#4d5360']
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