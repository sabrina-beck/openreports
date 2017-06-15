package org.efs.openreports.test.fixture;

import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportUser;

import java.util.*;

public class ReportUserFixture {


    public static ReportUser aReportUser(Report report) {
        ReportUser reportUser = new ReportUser();
        reportUser.setId(new Random().nextInt());
        reportUser.setPassword("password");
        reportUser.setName("foo bar");

        reportUser.setGroups(Arrays.asList(
            ReportGroupFixture.aGroup(report),
            ReportGroupFixture.aGroup(report)
        ));

        reportUser.setEmail("foo@bar.com");
        reportUser.setExternalId("foobar519595");
        reportUser.setPdfExportType(1);

        reportUser.setAlerts(Arrays.asList(
            ReportUserAlertFixture.aReportUserAlert(reportUser, report),
            ReportUserAlertFixture.aReportUserAlert(reportUser, report)
        ));

        reportUser.setDefaultReport(report);

        HashSet<String> roles = new HashSet<>();
        roles.add("ADMIN");
        reportUser.setRoles(roles);

        reportUser.setRootAdmin(true);
        reportUser.setScheduler(true);
        reportUser.setAdvancedScheduler(true);
        reportUser.setDashboardUser(true);
        reportUser.setDataSourceAdmin(true);
        reportUser.setReportAdmin(true);
        reportUser.setParameterAdmin(true);
        reportUser.setUserAdmin(true);
        reportUser.setGroupAdmin(true);
        reportUser.setChartAdmin(true);
        reportUser.setAlertAdmin(true);
        reportUser.setAlertUser(true);
        reportUser.setLogViewer(true);
        reportUser.setUploader(true);
        reportUser.setSchedulerAdmin(true);

        reportUser.setLocale(new Locale("th"));
        reportUser.setTimeZone(TimeZone.getTimeZone("America/Denver"));

        return reportUser;
    }
}
