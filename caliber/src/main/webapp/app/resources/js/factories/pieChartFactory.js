angular.module("charts").factory("pieChartFactory", function($log) {
	var lineChart = {};

	/**
	 * Getters and Setters for pie chart objects
	 */
	// get/ set labels
	pieChart.setLabels = funtion(labels)
	{
		charts.labels = labels;
	}
	;
	pieChart.getLabels = funtion()
	{
		if (!charts.labels)
			return null;
		return charts.labels;
	}
	;

	// get/ set series
	pieChart.setSeries = funtion(series)
	{
		charts.series = series;
	}
	;
	pieChart.getSeries = funtion()
	{
		if (!charts.series)
			return null;
		return charts.series;
	}
	;

	// get/ set data
	pieChart.setData = funtion(data)
	{
		charts.data = data;
	}
	;
	pieChart.getData = funtion()
	{
		if (!charts.data)
			return null;
		return charts.data;
	}
	;

	return pieChart;
});