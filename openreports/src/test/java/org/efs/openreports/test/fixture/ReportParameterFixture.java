package org.efs.openreports.test.fixture;

import org.efs.openreports.objects.ReportParameter;
import org.efs.openreports.objects.ReportParameterValue;

import java.util.Random;

public class ReportParameterFixture {

    public static ReportParameter aReportParameter() {
        ReportParameter reportParameter = new ReportParameter();

        reportParameter.setId(new Random().nextInt());
        reportParameter.setDataSource(ReportDataSourceFixture.aDataSource());
        reportParameter.setValues(new ReportParameterValue[] {
                ReportParameterValueFixture.aReportParameterValue(),
                ReportParameterValueFixture.aReportParameterValue()
        });
        reportParameter.setClassName("class name");
        reportParameter.setData("data data");
        reportParameter.setName("my report parameter");
        reportParameter.setType("my type");
        reportParameter.setDescription("report parameter description");
        reportParameter.setRequired(false);
        reportParameter.setMultipleSelect(false);
        reportParameter.setDefaultValue("default");

        return reportParameter;
    }

}
