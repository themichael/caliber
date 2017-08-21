angular.module("auth").factory("authFactory",
		function($log, $http, $cookies, $state, $location) {
			$log.debug("Booted Authentication Factory");

			var auth = {};
			var devMode = false;
			var DEBUG_URL = "/caliber/";

			// Roles
			var vpRole = "ROLE_VP";
			var qcRole = "ROLE_QC";
			var trainerRole = "ROLE_TRAINER";
			var stagingRole = "ROLE_STAGING";

			// home states
			var vpState = "vp.home";
			var qcState = "qc.home";
			var trainerState = "trainer.home";
			var stagingState = "staging.home";

			// home
			var vpHome = "/vp/home";
			var qcHome = "/qc/home";
			var trainerHome = "/trainer/home";
			
			var vpManage = "vp.manage";
			var qcManage = "qc.manage";
			var trainerManage = "trainer.manage";
			
			var vpAudit = "vp.audit";
			var qcAudit = "qc.audit";
			
			var vpReports = "vp.reports";
			var qcReports = "qc.reports";
			var trainerReports = "trainer.reports";
			
			var trainerImport = "trainer.import";
			
			var vpAssess = "vp.assess";
			var trainerAssess = "trainer.assess";
			
			var vpCategory = "vp.category";
			var vpTrainers = "vp.trainers";

			/**
			 * Retrieves role from cookie
			 * 
			 * @returns A cookie that contains the role
			 */
			function getCookie() {
				return $cookies.get("role");
			}

			//

			/**
			 * Moves user to home page when entering root
			 */
			auth.auth = function() {
				var role = getCookie();

                switch (role) {
					case trainerRole:
					    $log.debug("Changing state to: " + trainerState);
						$state.go(trainerState);
						break;
					case qcRole:
                        $log.debug("Changing state to: " + qcState);
						$state.go(qcState);
						break;
					case vpRole:
                        $log.debug("Changing state to: " + vpState);
						$state.go(vpState);
						break;
					case stagingRole:
                        $log.debug("Changing state to: " + stagingState);
						$state.go(stagingState);
						break;
                }
			};

			/**
			 * Moves user to home page if user is not of role qc
			 */
			auth.authQC = function() {
				var role = getCookie();
				if (role === qcRole)
					$log.debug("Authenticated user as QC");
				else if (role === trainerRole)
					$location.path(trainerHome);
				else
					$location.path(vpHome);
			};

			//

			/**
			 * moves user to home page if user is not of role vp
			 */
			auth.authVP = function() {
				var role = getCookie();
				if (role === vpRole)
					$log.debug("Authenticate user as VP");
				else if (role === trainerRole)
					$location.path(trainerHome);
				else
					$location.path(qcHome);
			};

			/**
			 * Moves user to home page if user is not of role trainer
			 */
			auth.authTrainer = function() {
				var role = getCookie();
				if (role === trainerRole)
					$log.debug("Authenticated user as Trainer");
				else if (role === qcRole)
					$location.path(qcHome);
				else
					$location.path(vpHome);
			};

			auth.authStaging = function () {
				var role = getCookie();

                $log.debug("Authorizing staging role");

                if(role !== stagingRole) {
                	if (devMode)
                		$location.path(DEBUG_URL);
                	else
                		$location.path("/");
                }
            };

			return auth;
		});