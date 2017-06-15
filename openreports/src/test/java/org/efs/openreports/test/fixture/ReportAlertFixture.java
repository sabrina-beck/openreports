package org.efs.openreports.test.fixture;

import org.efs.openreports.objects.ReportAlert;

import java.util.Random;

public class ReportAlertFixture {

    public static ReportAlert aReportAlert() {
        ReportAlert reportAlert = new ReportAlert();

        reportAlert.setId(new Random().nextInt());
        reportAlert.setDescription("my alert description");
        reportAlert.setName("My alert");
        reportAlert.setDataSource(ReportDataSourceFixture.aDataSource());
        reportAlert.setQuery("SELECT * FROM people;");

        return reportAlert;
    }
}
