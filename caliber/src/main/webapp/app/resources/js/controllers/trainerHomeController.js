angular.module("trainer").controller(
    "trainerHomeController",
    function ($scope, $log, caliberDelegate, chartsDelegate, allBatches) {
        $log.debug("Booted trainer home controller.");

    /*********************************************** UI ***************************************************/
    var viewCharts = 0;

    $scope.batches = allBatches;
    $scope.currentBatch = {};
    $scope.currentBatch.trainingName = "Batch";
    $scope.currentTech = "Technology";
    $scope.currentTrainee = {};
    $scope.currentTrainee.name = "Trainee";

        // on batch selection
        $scope.selectCurrentBatch = function (index) {
            $scope.currentTech = "Tech";
            $scope.currentTrainee.name = "Trainee";
            // turn of batches
            if (index === -1) {
                viewCharts = 0;
                $scope.currentBatch.trainingName = "Batch";
            }
            else {
                $scope.currentBatch = $scope.batches[index];
                viewCharts = 1;
                createBatchCharts();
            }
        };

        // on tech selection
        $scope.selectCurrentTech = function (index) {
            if (index === -1) {
                $scope.currentTrainee.name = "Trainee";
                $scope.currentTech = "Tech";
                viewCharts = 1;
            } else {
                $scope.currentTrainee.name = "Trainee";
                $scope.currentTech = $scope.tech[index];
                viewCharts = 2;
                createTechCharts();
            }
        };

        // on trainee selection
        $scope.selectCurrentTrainee = function (index) {
            if (index === -1) {
                $scope.currentTrainee.name = "Trainee";
                viewCharts = 2;
            }
            else {
                $scope.currentTech = "Tech";
                $scope.currentTrainee = $scope.currentBatch.trainees[index];
                viewCharts = 3;
                createTraineeCharts();
            }
        };

        // hide filter tabs
        $scope.hideOtherTabs = function () {
            return $scope.currentBatch !== "Batch";

        };

        // show charts
        $scope.showCharts = function (charts) {
            return charts === viewCharts;

        };

        // create charts on batch selection
        function createBatchCharts() {

            caliberDelegate.agg.getAggTechBatch($scope.currentBatch.batchId)
                .then(function(data){
                    var radarChartObject = chartsDelegate.radar.getBatchRankComparisonChart(data);
                    $scope.radarData = radarChartObject.data;
                    $scope.radarLabels = radarChartObject.labels;
                    $scope.radarSeries = radarChartObject.series;
                    $scope.radarOptions = radarChartObject.options;
                });

            caliberDelegate.agg.getAggWeekBatch($scope.currentBatch.batchId)
                .then(function(data){
                    var lineChartObject = chartsDelegate.line.getBatchProgressChart(data);
                    $scope.batchProgressLabels = lineChartObject.labels;
                    $scope.batchProgressData = lineChartObject.data;
                    $scope.batchProgressSeries = lineChartObject.series;
                    $scope.batchProgressOptions = lineChartObject.options;
                    $scope.batchProgressDatasetOverride = lineChartObject.datasetOverride;
                });
        }

        // create charts on tech selection
        function createTechCharts() {
            // Sample Data representing all trainee averages per technology
            var sampleHbarData = [
                {trainee: "Rikki", average: ranNum()},
                {trainee: "Kyle", average: ranNum()},
                {trainee: "Osher", average: ranNum()},
                {trainee: "Karina", average: ranNum()},
                {trainee: "Bryan", average: ranNum()},
                {trainee: "Shehar", average: ranNum()},
                {trainee: "Louis", average: ranNum()},
                {trainee: "Andrew", average: ranNum()},
                {trainee: "Sam", average: ranNum()},
                {trainee: "Ilya", average: ranNum()},
                {trainee: "David", average: ranNum()},
                {trainee: "Travis", average: ranNum()},
                {trainee: "Andrew", average: ranNum()}];

            // Horizontal bar chart for trainee averages per technology
            var hbarChartObject = chartsDelegate.hbar.getBatchAvgChart(sampleHbarData);
            $scope.hbarLabels = hbarChartObject.labels;
            $scope.hbarData = hbarChartObject.data;
            $scope.hbarOptions = hbarChartObject.options;
        }

        // create charts on trainee selection
        function createTraineeCharts() {

            caliberDelegate.agg.getAggWeekTrainee($scope.currentTrainee.traineeId)
                .then(function(data){
                    $log.debug(data);
                    var lineChartObject = chartsDelegate.line.getTraineeProgressChart(data);
                    $scope.lineLabels = lineChartObject.labels;
                    $scope.lineSeries = lineChartObject.series;
                    $scope.lineData = lineChartObject.data;
                    $scope.lineDatasetOverride = lineChartObject.datasetOverride;
                    $scope.lineOptions = lineChartObject.options;
                });

            caliberDelegate.agg.getAggTechTrainee($scope.currentTrainee.traineeId)
                .then(function(data){
                    $log.debug(data);
                    var radarChartObject = chartsDelegate.radar.getTraineeTechProgressChart(data);
                    $scope.radarData = radarChartObject.data;
                    $scope.radarLabels = radarChartObject.labels;
                    $scope.radarSeries = radarChartObject.series;
                    $scope.radarOptions = radarChartObject.options;
                });
        }


        /**************************************** Default Charts *******************************************/

            // batch rank comparison - sample data
        var sample7 = [
                {name: "Batch1342", score: ranNum()}, {name: "Batch1526", score: ranNum()},
                {name: "Batch0354", score: ranNum()}, {name: "Batch1822", score: ranNum()},
                {name: "Batch9355", score: ranNum()}, {name: "Batch1232", score: ranNum()},
                {name: "Batch7241", score: ranNum()}, {name: "Batch1782", score: ranNum()},
                {name: "Batch7341", score: ranNum()}, {name: "Batch2312", score: ranNum()},
                {name: "Batch8453", score: ranNum()}, {name: "Batch6345", score: ranNum()},
                {name: "Batch1431", score: ranNum()}];

        // batch rank comparison - hbar chart
        var hbarChartObject = chartsDelegate.hbar.getAllBatchesEvalChart(sample7);
        $scope.allBatchesRankLabels = hbarChartObject.labels;
        $scope.allBatchesRankData = hbarChartObject.data;
        $scope.allBatchesRankSeries = hbarChartObject.series;

        // random number gen - sample data only!
        function ranNum() {
            var num = (Math.random() * 50) + 50;
            return num.toFixed(2);
        }

        (function start(){
            caliberDelegate.agg.getAggTechAllBatch()
                .then(function(data){
                    $log.debug(data);
                    chartsDelegate.hbar.getAllBatchesEvalChart(data);
                    $scope.allBatchesRankLabels = hbarChartObject.labels;
                    $scope.allBatchesRankData = hbarChartObject.data;
                    $scope.allBatchesRankSeries = hbarChartObject.series;
                });
        })();

    });

