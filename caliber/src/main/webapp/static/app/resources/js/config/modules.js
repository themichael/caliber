/** modules * */
angular.module(
		"app",
		[ "ui.router", "ngCookies", "auth", "chart.js", "vp", "qc", "trainer",
				"api", "delegate", "charts", "reportApi", "ngAnimate" ]).run(
		function($rootScope, $timeout) {
			$rootScope.$on('test', function() {
				$timeout(function() {
					$rootScope.$broadcast('trainerasses');
					$rootScope.$broadcast('qcBatchOverall')
				}, 100);
			});
		})
// add modules here
angular.module("vp", []);
angular.module("qc", []);
angular.module("trainer", []);
angular.module("delegate", []);
angular.module("api", []);
angular.module("charts", []);
angular.module("auth", []);
angular.module("reportApi", []);

angular.module("app").directive("sticky",function($window){
	return function(scope,element,attrs){
		elOriginalPos = element[0].getClientRects()[0].top;
		angular.element($window).bind("scroll", function(){
			console.log('window  ' + this.pageYOffset);
			var elYPos = element[0].getClientRects()[0].top + 100;
			console.log('my element  ' + elYPos);
			if(this.pageYOffset > elYPos){
				element.addClass("fixed-style-table-header");
			}else if(this.pageYOffset <= elOriginalPos){
				element.removeClass("fixed-style-table-header");
			}
			scope.$apply();
		});
	}
}); 
