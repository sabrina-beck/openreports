package org.efs.openreports.test.pages.elements;

import org.openqa.selenium.WebElement;

public class ReportFile {

    private final String reportFileName;

    private final WebElement downloadLink;

    public ReportFile(String reportFileName, WebElement downloadLink) {
        this.reportFileName = reportFileName;
        this.downloadLink = downloadLink;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public WebElement getDownloadLink() {
        return downloadLink;
    }

}
