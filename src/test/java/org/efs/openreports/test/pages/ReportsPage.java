package org.efs.openreports.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReportsPage extends AuthenticatedBasePage {
    public ReportsPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasAccessToUploadReportFiles() {
        return driver.findElements(By.linkText("Upload Report Files")).size() > 0;
    }

    public UploadReportFilesPage clickUploadReportFiles() {
        WebElement link = driver.findElement(By.linkText("Upload Report Files"));
        link.click();
        return new UploadReportFilesPage(driver);
    }
}
