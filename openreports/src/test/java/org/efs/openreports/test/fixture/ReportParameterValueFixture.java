package org.efs.openreports.test.fixture;

import org.efs.openreports.objects.ReportParameterValue;

import java.util.Random;

public class ReportParameterValueFixture {

    public static ReportParameterValue aReportParameterValue() {
        ReportParameterValue reportParameterValue = new ReportParameterValue();

        reportParameterValue.setId(new Random().nextInt());
        reportParameterValue.setDescription("report parameter value description");

        return reportParameterValue;
    }

}
