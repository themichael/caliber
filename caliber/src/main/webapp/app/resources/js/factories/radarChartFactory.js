angular.module("charts").factory("radarChartFactory", function ($log) {
    $log.debug("Booted Radar Chart Factory");

    var radarChart = {};

    radarChart.getBatchRankComparisonChart = function (batch) {
        var chartData = {};

        $log.log(batch);

        // series
        chartData.series = ["Batch"];

        // labels and data
        chartData.labels = [];
        chartData.data = [];

        // push empty arrays for data
        chartData.data.push([]);

        // loop through batch data
        angular.forEach(batch, function (value, key) {
            chartData.labels.push(key);
            chartData.data[0].push(value[0]);
            $log.debug(value);
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

    radarChart.getTraineeTechProgressChart = function (dataArray) {
        var chartData = {};

        // This relies on both data charts having the same labels

        // series
        chartData.series = ["Technology"];

        // labels and data
        chartData.labels = [];
        chartData.data = [];

        // push empty arrays for data
        chartData.data.push([]);

        // loop through data
        dataArray.forEach(function (element) {
            chartData.data[0].push(element.average);
            chartData.labels.push(element.skillCategory);
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
