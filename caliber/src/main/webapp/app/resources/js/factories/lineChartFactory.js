angular.module("charts").factory("lineChartFactory", function ($log) {
    $log.debug("Booted Line Chart Factory.");

    var lineChart = {};

    /**
     * Getters and Setters for line chart objects
     */
    // get/ set labels
    lineChart.setLabels = function (labels) {
        charts.labels = labels;
    };
    lineChart.getLabels = function () {
        if (!charts.labels)
            return null;
        return charts.labels;
    }
    ;

    // get/ set series
    lineChart.setSeries = function (series) {
        charts.series = series;
    };
    lineChart.getSeries = function () {
        if (!charts.series)
            return null;
        return charts.series;
    };

    // get/ set data
    lineChart.setData = function(data)
    {
        charts.data = data;
    };
    lineChart.getData = function()
    {
        if (!charts.data)
            return null;
        return charts.data;
    };


    return lineChart;
});