angular.module("charts").factory("lineChartFactory", function($log) {
	var lineChart = {};

	/**
	 * Getters and Setters for line chart objects
	 */
	// get/ set labels
	lineChart.setLabels = funtion(labels)
	{
		charts.labels = labels;
	}
	;
	lineChart.getLabels = funtion()
	{
		if (!charts.labels)
			return null;
		return charts.labels;
	}
	;

	// get/ set series
	lineChart.setSeries = funtion(series)
	{
		charts.series = series;
	}
	;
	lineChart.getSeries = funtion()
	{
		if (!charts.series)
			return null;
		return charts.series;
	}
	;

	// get/ set data
	lineChart.setData = funtion(data)
	{
		charts.data = data;
	}
	;
	lineChart.getData = funtion()
	{
		if (!charts.data)
			return null;
		return charts.data;
	}
	;
	

	return lineChart;
});