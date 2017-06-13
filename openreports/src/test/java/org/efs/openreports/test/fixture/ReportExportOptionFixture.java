package org.efs.openreports.test.fixture;

import org.efs.openreports.objects.ReportExportOption;

import java.util.Random;

public class ReportExportOptionFixture {
    public static ReportExportOption aReportExportOption() {
        ReportExportOption reportExportOption = new ReportExportOption();

        reportExportOption.setId(new Random().nextInt());
        reportExportOption.setHtmlRemoveEmptySpaceBetweenRows(false);
        reportExportOption.setXlsRemoveEmptySpaceBetweenRows(false);
        reportExportOption.setXlsOnePagePerSheet(true);
        reportExportOption.setHtmlUsingImagesToAlign(true);
        reportExportOption.setHtmlWhitePageBackground(true);
        reportExportOption.setHtmlWrapBreakWord(true);
        reportExportOption.setXlsAutoDetectCellType(true);
        reportExportOption.setXlsWhitePageBackground(true);

        return reportExportOption;
    }
}
