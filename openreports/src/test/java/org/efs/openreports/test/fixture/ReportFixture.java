package org.efs.openreports.test.fixture;

import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportParameterMap;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReportFixture {
    public static Report aReport() {
        Report report = new Report();

        report.setId(new Random().nextInt());
        report.setDescription("Report Description");
        report.setFile("src/test/resources/valid_file.jasper");
        report.setName("My Report");

        List<ReportParameterMap> parameters = Arrays.asList(
                ReportParameterMapFixture.aParameterMapFixture(report, 1),
                ReportParameterMapFixture.aParameterMapFixture(report, 2));
        report.setParameters(parameters);

        report.setDataSource(ReportDataSourceFixture.aDataSource());
        report.setReportChart(ReportChartFixture.aReportChart(report));
        report.setCsvExportEnabled(true);
        report.setHtmlExportEnabled(true);
        report.setPdfExportEnabled(true);
        report.setXlsExportEnabled(true);
        report.setQuery("SELECT * FROM people WHERE age >= 18");
        report.setExcelExportEnabled(true);
        report.setRtfExportEnabled(true);
        report.setTextExportEnabled(true);
        report.setImageExportEnabled(true);
        report.setReportExportOption(ReportExportOptionFixture.aReportExportOption());
        report.setVirtualizationEnabled(true);
        report.setHidden(false);
        report.setDisplayInline(true);

        return report;
    }
}
