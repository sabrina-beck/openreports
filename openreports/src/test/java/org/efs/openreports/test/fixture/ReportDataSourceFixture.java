package org.efs.openreports.test.fixture;

import org.efs.openreports.objects.ReportDataSource;

import java.util.Random;

public class ReportDataSourceFixture {
    public static ReportDataSource aDataSource() {
        ReportDataSource reportDataSource = new ReportDataSource();

        reportDataSource.setJndi(false);
        reportDataSource.setId(new Random().nextInt());
        reportDataSource.setName("my data source");

        return reportDataSource;
    }
}
