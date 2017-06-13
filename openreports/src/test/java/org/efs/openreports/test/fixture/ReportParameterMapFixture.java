package org.efs.openreports.test.fixture;

import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportParameterMap;

public class ReportParameterMapFixture {


    public static ReportParameterMap aParameterMapFixture(Report report, int sortOrder) {
        ReportParameterMap reportParameterMap = new ReportParameterMap();
        reportParameterMap.setReport(report);
        reportParameterMap.setReportParameter(ReportParameterFixture.aReportParameter());
        reportParameterMap.setRequired(false);
        reportParameterMap.setSortOrder(sortOrder);
        reportParameterMap.setStep(1);

        return reportParameterMap;
    }
}
