/**
 *
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("radarChartFactory", function ($log) {
    $log.debug("Booted Radar Chart Factory");

    var radarChart = {};

    radarChart.getBatchRankComparisonChart = function (data) {
        var chartData = {};

        // series
        chartData.series = ["Batch"];

        // labels and data
        chartData.labels = [];
        chartData.data = [];

        // push empty arrays for data
        chartData.data.push([]);

        // loop through batch data
        angular.forEach(data, function (value, key) {
            chartData.labels.push(key);
            chartData.data[0].push(value[0]);
        });

        // set radar options
        chartData.options = {
            legend: {
                display: true,
                position: 'bottom'
            }
        };
        return chartData;
    };

    radarChart.getTraineeTechProgressChart = function (data) {
        var chartData = {};

        // series
        chartData.series = ["Technology"];

        // labels and data
        chartData.labels = [];
        chartData.data = [];

        // push empty arrays for data
        chartData.data.push([]);

        // loop through batch data
        angular.forEach(data, function (value, key) {
            chartData.labels.push(key);
            chartData.data[0].push(value[0]);
        });
        // set radar options
        chartData.options = {
            legend: {
                display: true,
                position: 'bottom'
            }
        };
        return chartData;
    };

    radarChart.getAllBatchRankComparisonChart = function (data) {
        var chartData = {};

        // series
        chartData.series = [];

        // labels and data
        chartData.labels = [];
        chartData.data = [];

        // push empty arrays for data
        for(var i = 0; i < data.length; i++ ){
            chartData.data.push([]);
        }

        // set index
        var index = 0;

        angular.forEach(data[0], function(value, key){
            chartData.labels(key);
        });

        // loop through batch data
        angular.forEach(data, function (value, key) {
            chartData.series.push(key);
            index++;
            angular.forEach(value, function(value2, key2){
                chartData.data[index].push(value2);
            });
            // chartData.labels.push(key);
            // chartData.data[0].push(value[0]);
        });

        // set radar options
        chartData.options = {
            legend: {
                display: true,
                position: 'bottom'
            }
        };
        return chartData;
    };

    return radarChart;
});
