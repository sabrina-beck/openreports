package org.efs.openreports.test.fixture;

import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportChart;

import java.util.Random;

public class ReportChartFixture {
    public static ReportChart aReportChart(Report report) {
        ReportChart reportChart = new ReportChart();

        reportChart.setId(new Random().nextInt());
        reportChart.setDescription("report chart description");
        reportChart.setName("My Report Chart");
        reportChart.setDataSource(ReportDataSourceFixture.aDataSource());
        reportChart.setQuery("SELECT * FROM people WHERE age >= 18");
        reportChart.setChartType(1);
        reportChart.setHeight(300);
        reportChart.setWidth(400);
        reportChart.setXAxisLabel("X axis");
        reportChart.setYAxisLabel("Y axis");
        reportChart.setShowLegend(true);
        reportChart.setShowTitle(true);
        reportChart.setPlotOrientation(90);
        reportChart.setDrillDownReport(report);
        reportChart.setShowValues(true);

        return reportChart;
    }
}
