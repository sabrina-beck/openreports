package org.efs.openreports.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExportedReportPage {
    private final WebDriver driver;

    public ExportedReportPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPdfLoaded() {
        WebElement pdfElement = driver.findElement(By.tagName("embed"));
        return pdfElement.getAttribute("type").equals("application/pdf");
    }

    public boolean isHtmlLoaded() {
        return driver.findElements(By.tagName("table")).size() > 0;
    }

    public boolean isInNewWindow() {
        return driver.getWindowHandles().size() > 1;
    }
}
