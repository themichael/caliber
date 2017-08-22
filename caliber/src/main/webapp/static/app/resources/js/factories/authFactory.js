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
			
//			var vpManage = "vp.manage";
//			var qcManage = "qc.manage";
//			var trainerManage = "/trainer/home";
//			
//			var vpAudit = "vp.audit";
//			var qcAudit = "qc.audit";
//			
//			var vpReports = "vp.reports";
//			var qcReports = "qc.reports";
//			var trainerReports = "trainer.reports";
//			var stagingReports = "staging.reports";
//			
//			var trainerImport = "trainer.import";
//			
//			var vpAssess = "vp.assess";
//			var trainerAssess = "trainer.assess";
//			
//			var vpCategory = "vp.category";
//			
//			var vpTrainers = "vp.trainers";

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
				else if (role === vpRole)
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
				else if (role === qcRole)
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
				else if (role === vpRole)
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
            
            /* if user has one of the specified roles, continue on. Otherwise,
             * take the user to his/her homepage. */
            auth.authManage = function() {
            		var role = getCookie();
            		
            		switch(role) {
	            		case vpRole:
	            			$log.debug("Changing state to: " + vpManage);
	            			break;
	            		case qcRole:
	            			$log.debug("Changing state to: " + qcManage);
	            			break;
	            		case trainerRole:
	            			$log.debug("Changing state to: " + trainerManage);
	            			break;
	            		default:
	            			auth.auth();
            		}
            };
            
            /* if user has one of the specified roles, continue on. Otherwise,
             * take the user to his/her homepage. */
            auth.authAudit = function() {
				var role = getCookie();

                switch (role) {
	                case vpRole:
	                    $log.debug("Changing state to: " + vpAudit);
						break;
					case qcRole:
                        $log.debug("Changing state to: " + qcAudit);
						break;
					default:
						auth.auth();
                }
			};
			
			/* if user has one of the specified roles, continue on. Otherwise,
             * take the user to his/her homepage. */
			auth.authReports = function() {
	        		var role = getCookie();
	        		
	        		switch(role) {
		        		case vpRole:
		        			$log.debug("Changing state to: " + vpReports);
		        			break;
		        		case qcRole:
		        			$log.debug("Changing state to: " + qcReports);
		        			break;
		        		case trainerRole:
		        			$log.debug("Changing state to: " + trainerReports);
		        			break;
		        		case stagingRole:
		        			$log.debug("Changing state to: " + stagingReports);
		        		default:
		        			auth.auth();
	        		}
        };
        
        /* if user has one of the specified roles, continue on. Otherwise,
         * take the user to his/her homepage. */
        auth.authImport = function() {
	        	var role = getCookie();
	    		
	    		switch(role) {
		    		case trainerRole:
		    			$log.debug("Changing state to: " + trainerImport);
		    			break;
		    		default:
		    			auth.auth();
	    		}
        };
        
        /* if user has one of the specified roles, continue on. Otherwise,
         * take the user to his/her homepage. */
        auth.authAssess = function() {
	        	var role = getCookie();
	    		
	    		switch(role) {
		    		case vpRole:
		    			$log.debug("Changing state to: " + vpAssess);
		    			break;
		    		case trainerRole:
		    			$log.debug("Changing state to: " + trainerAssess);
		    			break;
		    		default:
		    			auth.auth();
	    		}
        };
        
        /* if user has one of the specified roles, continue on. Otherwise,
         * take the user to his/her homepage. */
        auth.authCategory = function() {
        		var role = getCookie();
        		
        		switch(role) {
	        		case vpRole:
	        			$log.debug("Changing state to: " + vpCategory);
	        			break;
	        		default:
	        			auth.auth();
        		}
        };
        
        /* if user has one of the specified roles, continue on. Otherwise,
         * take the user to his/her homepage. */
        auth.authTrainers = function() {
        		var role = getCookie();
        		
        		switch(role) {
	        		case vpRole:
	        			$log.debug("Changing state to: " + vpTrainers);
	        			break;
	        		default:
	        			auth.auth();
        		}
        };
        
			return auth;
		});