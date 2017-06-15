package org.efs.openreports.test.fixture;

import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportUser;
import org.efs.openreports.objects.ReportUserAlert;

public class ReportUserAlertFixture {
    public static ReportUserAlert aReportUserAlert(ReportUser reportUser, Report report) {
        ReportUserAlert reportUserAlert = new ReportUserAlert();

        reportUserAlert.setAlert(ReportAlertFixture.aReportAlert());
        reportUserAlert.setLimit(100);
        reportUserAlert.setOperator("foobar");
        reportUserAlert.setUser(reportUser);
        reportUserAlert.setCount(10);
        reportUserAlert.setReport(report);

        return reportUserAlert;
    }
}
