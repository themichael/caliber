angular
		.module("charts")
		.controller(
				"allReportController",
				function($scope, $log, caliberDelegate, chartsDelegate, allBatches) {

					$log.debug("Booted Report Controller");
					$log.debug("Peacepapi is here!!!!!");

					(function start() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();

						// batch null check
						if (!allBatches || allBatches.length === 0) {
							$scope.noBatches = true;
							$scope.noBatchesMessage = "No Batches belonging to you were found.";
						} else {
							$scope.noBatches = false;
							createDefaultCharts();
						}
					})();

					function createDefaultCharts() {
						// Finishes any left over ajax animation from another
						// page
//						NProgress.done();
//						NProgress.start();
//						chartsDelegate.doughnut.data
//								.getQCStatsData(50, 1)
//								.then(
//										function(data) {
//											$log.debug(data);
//											NProgress.done();
//											var doughnutChartObject = chartsDelegate.doughnut
//													.getQCStats(data);
//											$scope.qcStatsLabels = doughnutChartObject.labels;
//											$scope.qcStatsData = doughnutChartObject.data;
//											$scope.qcStatsOptions = doughnutChartObject.options;
//										}, function() {
//											NProgress.done();
//										});
					}
					
					/**
					 * Generates a PDF by sending HTML to server.
					 * Downloads automatically in new tab.
					 */
					$scope.generatePDF = function() {
						var html = "<div>Extract report contents into here</div>";
						caliberDelegate.all.generatePDF(html).then(
								function(pdf) {
									// extract PDF bytes
									var file = new Blob([ pdf ], {
										type : "application/pdf"
									});
									// create temporary 'url' and download automatically
									var fileURL = URL.createObjectURL(file);
									var a = document.createElement("a");
									a.href = fileURL;
									a.target = "_blank";
									a.download = "report.pdf";
									document.body.appendChild(a);
									a.click();

								}, function(error) {
									$log.debug(reason);
								}, function(value) {
									$log.debug(value);
								});
					}

				});
