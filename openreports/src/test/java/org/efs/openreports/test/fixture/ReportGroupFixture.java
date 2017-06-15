package org.efs.openreports.test.fixture;

import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportGroup;

import java.util.Arrays;
import java.util.Random;

public class ReportGroupFixture {


    public static ReportGroup aGroup(Report report) {
        ReportGroup reportGroup = new ReportGroup();

        reportGroup.setReports(Arrays.asList(report));
        reportGroup.setName("My Group");
        reportGroup.setDescription("my group description");
        reportGroup.setId(new Random().nextInt());

        return reportGroup;
    }
}
