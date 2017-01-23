angular.module("charts").factory("pieChartFactory", function ($log) {
    var lineChart = {};

    /**
     * Getters and Setters for pie chart objects
     */
    // get/ set labels
    pieChart.setLabels = function (labels) {
        charts.labels = labels;
    };
    pieChart.getLabels = function () {
        if (!charts.labels)
            return null;
        return charts.labels;
    };

    // get/ set series
    pieChart.setSeries = function (series) {
        charts.series = series;
    };
    pieChart.getSeries = function () {
        if (!charts.series)
            return null;
        return charts.series;
    };

    // get/ set data
    pieChart.setData = function (data) {
        charts.data = data;
    };
    pieChart.getData = function () {
        if (!charts.data)
            return null;
        return charts.data;
    };

    return pieChart;
});