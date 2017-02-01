/**
 *
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("hbarChartFactory", function ($log) {
        $log.debug("Booted Horizontal Bar Chart Factory");

        var hbarChart = {};

        hbarChart.getBatchAvgChart = function (dataArray) {
            var chartData = {};

            // data and labels
            chartData.data = [];
            chartData.labels = [];

            // traverse through array of objects and grab labels and data
            dataArray.forEach(function (element) {
                chartData.labels.push(element.trainee);
                chartData.data.push(element.average);
            });

            chartData.datasetOverride = [{
                xAxisID: 'x-axis-1'
            }];

            return chartData;
        };

        hbarChart.getTrainerEvalChart = function (dataArray) {
            var chartData = {};

            // series
            chartData.series = ["QC Eval"];

            // labels and data
            chartData.data = [];
            chartData.labels = [];

            // loop through object array
            dataArray.forEach(function (element) {
                chartData.data.push(element.score);
                chartData.labels.push(element.name);
            });

            return chartData;
        };

        hbarChart.getAllBatchesEvalChart = function (dataArray) {
            var chartData = {};

            // series
            chartData.series = ["All Batch Eval"];

            // labels and data
            chartData.data = [];
            chartData.labels = [];

            // loop through object array
            dataArray.forEach(function (element) {
                chartData.data.push(element.score);
                chartData.labels.push(element.name);
            });

            return chartData;
        };

        hbarChart.getBatchTechEvalChart = function (dataArray) {
            var chartData = {};

            // series
            chartData.series = ["Tech Batch Eval"];

            // labels and data
            chartData.data = [];
            chartData.labels = [];

            // loop through object array
            dataArray.forEach(function (element) {
                chartData.data.push(element.average);
                chartData.labels.push(element.trainee);
            });

            return chartData;
        };


        return hbarChart;
    });