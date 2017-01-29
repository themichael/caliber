angular.module("qc").controller("qcHomeController", function
    ($scope, $log, caliberDelegate, chartsDelegate) {

    $scope.batches = ["Batch1311", "Batch1612", "Batch1512", "Batch1812", "Batch0910", "Batch0805", "Batch0408"];
    $scope.tech = ["Spring", "Hibernate", "JSP"];
    $scope.trainees = ["Osher", "Kyle", "Rikki"];

    /*********************************************** UI ***************************************************/
    var viewCharts = 0;

    $scope.batches = [ "Batch1311", "Batch1612", "Batch1512",
        "Batch1812", "Batch0910", "Batch0805", "Batch0408" ];
    $scope.tech = [ "Spring", "Hibernate", "JSP" ];
    $scope.trainees = [ "Osher", "Kyle", "Rikki" ];

    $scope.currentBatch = "Batch";

    $scope.currentTech = "Technology";

    $scope.currentTrainee = "Trainee";

    // on batch selection
    $scope.selectCurrentBatch = function(index){
        $scope.currentTech = "Tech";
        $scope.currentTrainee = "Trainee";
        // turn of batches
        if(index === -1){
            viewCharts = 0;
            $scope.currentBatch = "Batch";
        }
        else {
            $scope.currentBatch = $scope.batches[index];
            viewCharts = 1;
            createBatchCharts();
        }
    };

    // on tech selection
    $scope.selectCurrentTech = function(index) {
        if (index === -1) {
            $scope.currentTrainee = "Trainee";
            $scope.currentTech = "Tech";
            viewCharts = 1;
        }else{
            $scope.currentTrainee = "Trainee";
            $scope.currentTech = $scope.tech[index];
            viewCharts = 2;
            createTechCharts();
        }
    };

    // on trainee selection
    $scope.selectCurrentTrainee = function(index) {
        if (index === -1) {
            $scope.currentTrainee = "Trainee";
            viewCharts = 2;
        }
        else{
            $scope.currentTech = "Tech";
            $scope.currentTrainee = $scope.trainees[index];
            viewCharts = 3;
            createTraineeCharts();
        }
    };

    // hide filter tabs
    $scope.hideOtherTabs = function(){
        if($scope.currentBatch === "Batch")
            return false;
        return true;
    };

    // show charts
    $scope.showCharts = function(charts){
        if(charts === viewCharts)
            return true;
        return false;
    };

    // create charts on batch selection
    function createBatchCharts(){
        // batch week by week sample data
        var sample3 = [{week: "Week 1", average: ranNum()}, {week: "Week 2", average: ranNum()},
            {week: "Week 3", average: ranNum()}, {week: "Week 4", average: ranNum()},
            {week: "Week 5", average: ranNum()}, {week: "Week 6", average: ranNum()},
            {week: "Week 7", average: ranNum()}, {week: "Week 8", average: ranNum()},
            {week: "Week 9", average: ranNum()}, {week: "Week 10", average: ranNum()},
            {week: "Week 11", average: ranNum()}, {week: "Week 12", average: ranNum()}];

        // create other charts
        var lineChartObject = chartsDelegate.line.getBatchProgressChart(sample3);
        $scope.batchProgressLabels = lineChartObject.labels;
        $scope.batchProgressData = lineChartObject.data;
        $scope.batchProgressSeries = lineChartObject.series;
        $scope.batchProgressOptions = lineChartObject.options;
        $scope.batchProgressDatasetOverride = lineChartObject.datasetOverride;
    }

    // create charts on tech selection
    function createTechCharts(){
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
    function createTraineeCharts(){

        // Sample Data representing trainee average over 12 weeks
        var sampleLineData = [
            {week: "Week 1", average: ranNum()}, {week: "Week 2", average: ranNum()},
            {week: "Week 3", average: ranNum()}, {week: "Week 4", average: ranNum()},
            {week: "Week 5", average: ranNum()}, {week: "Week 6", average: ranNum()},
            {week: "Week 7", average: ranNum()}, {week: "Week 8", average: ranNum()},
            {week: "Week 9", average: ranNum()}, {week: "Week 10", average: ranNum()},
            {week: "Week 11", average: ranNum()}, {week: "Week 12", average: ranNum()}];

        // Sample Data representing trainee strengths per technology
        var samplePieData =[
            {skillCategory:"Core Java", average: 85},
            {skillCategory:"SQL", average: 75},
            {skillCategory:"Spring", average: 95},
            {skillCategory:"Hibernate", average: 75},
            {skillCategory:"AngularJS", average: 90},
            {skillCategory:"REST", average: 80}];

        // line chart function that retrieves
        // Week by week progression for a trainee/ batch on a line chart
        var lineChartObject = chartsDelegate.line.getTraineeProgressChart(sampleLineData);
        $scope.lineLabels = lineChartObject.labels;
        $scope.lineSeries = lineChartObject.series;
        $scope.lineData = lineChartObject.data;
        $scope.lineDatasetOverride = lineChartObject.datasetOverride;
        $scope.lineOptions = lineChartObject.options;

        // pie chart function that retrieves
        // data for batch/ trainee technology strengths
        var pieChartObject = chartsDelegate.pie.getTraineeTechProgressChart(samplePieData);
        $scope.pieLabels = pieChartObject.labels;
        $scope.pieData = pieChartObject.data;
        $scope.pieOptions = pieChartObject.options;
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
    function ranNum(){
        var num = (Math.random() * 50) + 50;
        return num.toFixed(2);
    }
});