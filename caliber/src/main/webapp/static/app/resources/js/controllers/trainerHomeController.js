angular.module("trainer").controller(
    "trainerHomeController",
    function ($scope, $log, caliberDelegate, chartsDelegate, allBatches) {
        $log.debug("Booted trainer home controller.");
        console.log('test code');
    /*********************************** On Start *****************************/
    (function start(){
        //Finishes any left over ajax animation from another page
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

    function createDefaultCharts(){
        //Finishes any left over ajax animation from another page
        NProgress.done();
        NProgress.start();
        caliberDelegate.agg.getAggBatchAllTrainer(allBatches[0].trainer.trainerId)
            .then(function(data){
                $log.debug(data);
                NProgress.done();
                var hbarChartObject = chartsDelegate.hbar.getAllBatchesEvalChart(data);
                $scope.allBatchesRankLabels = hbarChartObject.labels;
                $scope.allBatchesRankData = hbarChartObject.data;
                $scope.allBatchesRankSeries = hbarChartObject.series;
            }, function(){
                NProgress.done();
            });
    }

    /*********************************************** UI ***************************************************/
    var viewCharts = 0;

    $scope.batches = allBatches;
    $scope.currentBatch = {trainingName: "Batch"};
    $scope.currentTrainee = {name: "Trainee"};

        // on batch selection
        $scope.selectCurrentBatch = function (index) {
            $scope.currentTrainee = {name: "Trainee"};
            // turn of batches
            if (index === -1) {
                viewCharts = 0;
                $scope.currentBatch = {trainingName: "Batch"};
                createDefaultChart();
            }
            else {
                $scope.currentBatch = $scope.batches[index];
                viewCharts = 1;
                createBatchCharts();
            }
        };

        // on trainee selection
        $scope.selectCurrentTrainee = function (index) {
            if (index === -1) {
                $scope.currentTrainee = {name: "Trainee"};
                viewCharts = 1;
                createBatchCharts();
            }
            else {
                $scope.currentTrainee = $scope.currentBatch.trainees[index];
                viewCharts = 3;
                createTraineeCharts();
            }
        };

        // hide filter tabs
        $scope.hideOtherTabs = function () {
            return $scope.currentBatch.trainingName !== "Batch";
        };

        // show charts
        $scope.showCharts = function (charts) {
            return charts === viewCharts;
        };

        // create charts on batch selection
        function createBatchCharts() {
            NProgress.start();
            caliberDelegate.agg.getAggTechBatch($scope.currentBatch.batchId)
                .then(function(data){
                    var radarChartObject = chartsDelegate.radar.getBatchRankComparisonChart(data);
                    $scope.batchTechData = radarChartObject.data;
                    $scope.batchTechLabels = radarChartObject.labels;
                    $scope.batchTechSeries = radarChartObject.series;
                    $scope.batchTechOptions = radarChartObject.options;
                }, function(){
                    NProgress.done();
                });

            caliberDelegate.agg.getAggWeekBatch($scope.currentBatch.batchId)
                .then(function(data){
                    NProgress.done();
                    var lineChartObject = chartsDelegate.line.getBatchProgressChart(data);
                    $scope.batchProgressLabels = lineChartObject.labels;
                    $scope.batchProgressData = lineChartObject.data;
                    $scope.batchProgressSeries = lineChartObject.series;
                    $scope.batchProgressOptions = lineChartObject.options;
                    $scope.batchProgressDatasetOverride = lineChartObject.datasetOverride;
                }, function(){
                    NProgress.done();
                });
        }

        // create charts on trainee selection
        function createTraineeCharts() {

            NProgress.start();
            caliberDelegate.agg.getAggWeekTrainee($scope.currentTrainee.traineeId)
                .then(function(data){
                    $log.debug(data);
                    NProgress.done();
                    var lineChartObject2 = chartsDelegate.line.getTraineeProgressChart(data);
                    $scope.traineeProgressLabels = lineChartObject2.labels;
                    $scope.traineeProgressSeries = lineChartObject2.series;
                    $scope.traineeProgressData = lineChartObject2.data;
                    $scope.traineeProgressDatasetOverride = lineChartObject2.datasetOverride;
                    $scope.traineeProgressOptions = lineChartObject2.options;
                }, function(){
                    NProgress.done();
                });

            caliberDelegate.agg.getAggTechTrainee($scope.currentTrainee.traineeId)
                .then(function(data){
                    $log.debug(data);
                    var radarChartObject2 = chartsDelegate.radar.getTraineeTechProgressChart(data);
                    $scope.traineeTechData = radarChartObject2.data;
                    $scope.traineeTechLabels = radarChartObject2.labels;
                    $scope.traineeTechSeries = radarChartObject2.series;
                    $scope.traineeTechOptions = radarChartObject2.options;
                }, function(){
                    NProgress.done();
                });
        }

    });

