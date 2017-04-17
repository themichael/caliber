/** modules * */
var app =angular.module("app", [ "ui.router", "ngCookies", "auth", "chart.js", "vp",
		"qc", "trainer", "api", "delegate", "charts", "reportApi","ngAnimate"]).run(function($rootScope){
			$rootScope.$on('test',function(){
				$rootScope.$broadcast('trainerasses');
				$rootScope.$broadcast('qcassess');
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