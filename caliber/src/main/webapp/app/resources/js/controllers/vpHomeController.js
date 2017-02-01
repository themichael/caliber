angular.module("vp").controller(
    "vpHomeController",
    function ($scope, $log, caliberDelegate, chartsDelegate, allBatches) {
        $log.debug("Booted vp home controller.");
        $log.debug("VP ALL Batches: ");
        $log.debug(allBatches);

        /*********************************************** UI ***************************************************/
        // decides what charts are to be shown
        var viewCharts = 0;

        // UI - Dropdown menu selection
        $scope.batches = allBatches;
        $scope.tech = ["Spring", "Hibernate", "JSP"];
        $scope.trainees = ["Osher", "Kyle", "Rikki"];

        // dropdown defaults
        $scope.currentBatch = {};
        $scope.currentBatch.trainingName = "Batch";
        $scope.currentTech = {};
        $scope.currentTech.name = "Tech";
        $scope.currentTrainee = {};
        $scope.currentTrainee.name = "Trainee";

        //  none selected check
        // $scope.noneSelected = function(){
        //     if($scope.currentBatch !== "Batch")
        //         return $scope.currentBatch.trainingName;
        //     return $scope.currentBatch;
        // };

        // on batch selection
        $scope.selectCurrentBatch = function (index) {
            $scope.currentTech.name = "Tech";
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
                $scope.currentTech.name = "Tech";
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
                $scope.currentTech.name = "Tech";
                $scope.currentTrainee = $scope.currentBatch.trainees[index];
                viewCharts = 3;
                createTraineeCharts($scope.currentTrainee.traineeId);
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

        /************************************** Chart Creation Functions *************************************/

        // create charts batch selection
        function createBatchCharts() {

            // batch rank comparison - radar chart
            var sample1 = [{tech: "Java", average: ranNum()},
                {tech: "Servlet", average: ranNum()}, {tech: "Spring", average: ranNum()},
                {tech: "Hibernate", average: ranNum()}, {tech: "REST", average: ranNum()},
                {tech: "SOAP", average: ranNum()}, {tech: "Javascript", average: ranNum()},
                {tech: "Angular", average: ranNum()}];

            var sample2 = [{tech: "Java", average: ranNum()},
                {tech: "Servlet", average: ranNum()}, {tech: "Spring", average: ranNum()},
                {tech: "Hibernate", average: ranNum()}, {tech: "REST", average: ranNum()},
                {tech: "SOAP", average: ranNum()}, {tech: "Javascript", average: ranNum()},
                {tech: "Angular", average: ranNum()}];

            // batch week by week sample data
            var sample3 = [{week: "Week 1", average: ranNum()}, {week: "Week 2", average: ranNum()},
                {week: "Week 3", average: ranNum()}, {week: "Week 4", average: ranNum()},
                {week: "Week 5", average: ranNum()}, {week: "Week 6", average: ranNum()},
                {week: "Week 7", average: ranNum()}, {week: "Week 8", average: ranNum()},
                {week: "Week 9", average: ranNum()}, {week: "Week 10", average: ranNum()},
                {week: "Week 11", average: ranNum()}, {week: "Week 12", average: ranNum()}];

            // create batch radar chart
            var radarChartObject = chartsDelegate.radar.getBatchRankComparisonChart(sample1, sample2);
            $scope.batchRankLabels = radarChartObject.labels;
            $scope.batchRankData = radarChartObject.data;
            $scope.batchRankSeries = radarChartObject.series;
            $scope.batchRankOptions = radarChartObject.options;

            // create other charts
            var lineChartObject = chartsDelegate.line.getBatchProgressChart(sample3);
            $scope.batchProgressLabels = lineChartObject.labels;
            $scope.batchProgressData = lineChartObject.data;
            $scope.batchProgressSeries = lineChartObject.series;
            $scope.batchProgressOptions = lineChartObject.options;
            $scope.batchProgressDatasetOverride = lineChartObject.datasetOverride;
        }

        // create charts on tech selection
        function createTechCharts() {

            var sample4 = [{trainee: "Rikki", average: ranNum()},
                {trainee: "Kyle", average: ranNum()},
                {trainee: "Osher", average: ranNum()},
                {trainee: "Danny P", average: ranNum()},
                {trainee: "Bryan", average: ranNum()},
                {trainee: "Brayn", average: ranNum()},
                {trainee: "Thomas", average: ranNum()},
                {trainee: "Mark", average: ranNum()},
                {trainee: "Michael", average: ranNum()}];

            var techBarData = chartsDelegate.hbar.getBatchTechEvalChart(sample4);
            $scope.batchTechLabels = techBarData.labels;
            $scope.batchTechData = techBarData.data;
            $scope.batchTechSeries = techBarData.series;
        }

        // create charts on trainee section
        function createTraineeCharts(traineeId) {

            // Sample Data representing trainee average over 12 weeks
            var sample5 = [{week: "Week 1", average: 79}, {week: "Week 2", average: 89},
                {week: "Week 3", average: 67}, {week: "Week 4", average: 79},
                {week: "Week 5", average: 86}, {week: "Week 6", average: 76},
                {week: "Week 7", average: 79}, {week: "Week 8", average: 89},
                {week: "Week 9", average: 72}, {week: "Week 10", average: 94},
                {week: "Week 11", average: 86}, {week: "Week 12", average: 65}];

            // Sample Data representing trainee strengths per technology
            var sample6 = [{skillCategory: "Core Java", average: ranNum()},
                {skillCategory: "SQL", average: ranNum()},
                {skillCategory: "Spring", average: ranNum()},
                {skillCategory: "Hibernate", average: ranNum()},
                {skillCategory: "AngularJS", average: ranNum()},
                {skillCategory: "REST", average: ranNum()}];

            // Create line chart
            var lineChartObject = chartsDelegate.line.getTraineeProgressChart(sample5);
            $scope.traineeProgressLabels = lineChartObject.labels;
            $scope.traineeProgressSeries = lineChartObject.series;
            $scope.traineeProgressData = lineChartObject.data;
            $scope.traineeProgressDatasetOverride = lineChartObject.datasetOverride;
            $scope.traineeProgressOptions = lineChartObject.options;

            var radarChartObject = chartsDelegate.radar.getTraineeTechProgressChart(sample6);
            $scope.techScoreLabels = radarChartObject.labels;
            $scope.techScoreData = radarChartObject.data;
            $scope.techScoreOptions = radarChartObject.options;

            caliberDelegate.agg.getAggTechTrainee(traineeId);
            caliberDelegate.agg.getAggWeekTrainee(traineeId);
        }

        /**************************************** Default Charts *******************************************/
            // trainer rank comparison - sample data
        var sample6 = [{name: "Patrick", score: ranNum()}, {name: "Joe", score: ranNum()},
                {name: "Brian", score: ranNum()}, {name: "Ryan", score: ranNum()},
                {name: "Karan", score: ranNum()}, {name: "Steven", score: ranNum()},
                {name: "Nick", score: ranNum()}, {name: "Richard", score: ranNum()},
                {name: "Fred", score: ranNum()}, {name: "Geneses", score: ranNum()},
                {name: "Emily", score: ranNum()}, {name: "Ankit", score: ranNum()},
                {name: "Ankit", score: ranNum()}];

        // create trainer hbar chart
        var hbarChartObject = chartsDelegate.hbar.getTrainerEvalChart(sample6);
        $scope.trainerRankLabels = hbarChartObject.labels;
        $scope.trainerRankData = hbarChartObject.data;
        $scope.trainerRankSeries = hbarChartObject.series;

        // batch rank comparison - sample data
        var sample7 = [{name: "Batch1342", score: ranNum()}, {name: "Batch1526", score: ranNum()},
            {name: "Batch0354", score: ranNum()}, {name: "Batch1822", score: ranNum()},
            {name: "Batch9355", score: ranNum()}, {name: "Batch1232", score: ranNum()},
            {name: "Batch7241", score: ranNum()}, {name: "Batch1782", score: ranNum()},
            {name: "Batch7341", score: ranNum()}, {name: "Batch2312", score: ranNum()},
            {name: "Batch8453", score: ranNum()}, {name: "Batch6345", score: ranNum()},
            {name: "Batch1431", score: ranNum()}];

        // batch rank comparison - hbar chart
        var hbarChartObject2 = chartsDelegate.hbar.getAllBatchesEvalChart(sample7);
        $scope.allBatchesRankLabels = hbarChartObject2.labels;
        $scope.allBatchesRankData = hbarChartObject2.data;
        $scope.allBatchesRankSeries = hbarChartObject2.series;


        /***************************** Agg Functions ****************************/
        (function(){
            caliberDelegate.agg.getAggTechAllBatch()
                .then(function(data){
                    $scope.aggBatchData = [];
                    angular.forEach(data, function(value, key){
                        var sum = 0; var numBatches = 0;
                        angular.forEach(value, function(value, key2){
                            sum = sum + value[0]; numBatches++;
                        });
                        var average = sum/numBatches;
                        $scope.aggBatchData.push({name: key, score: average});
                    });
                    var hbarChartObject2 = chartsDelegate.hbar.getAllBatchesEvalChart($scope.aggBatchData);
                    $scope.allBatchesRankLabels = hbarChartObject2.labels;
                    $scope.allBatchesRankData = hbarChartObject2.data;
                    $scope.allBatchesRankSeries = hbarChartObject2.series;
                    $log.debug($scope.aggBatchData);
                }, function(response){
                // on error
            });
        })();

        // random number gen - sample data only!
        function ranNum() {
            var num = (Math.random() * 50) + 50;
            return num.toFixed(2);
        }
    });