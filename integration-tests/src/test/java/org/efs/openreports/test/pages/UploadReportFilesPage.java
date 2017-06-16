package org.efs.openreports.test.pages;

import org.efs.openreports.test.pages.elements.ReportFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class UploadReportFilesPage extends AuthenticatedBasePage {
    public UploadReportFilesPage(String baseUrl, WebDriver driver) {
        super(baseUrl, driver);
    }

    public boolean isOpen() {
        return driver.getCurrentUrl().endsWith("reportUpload.action");
    }

    public void chooseFile(File file) {
        WebElement chooseFileInput = driver.findElement(By.name("reportFile"));
        chooseFileInput.sendKeys(file.getAbsolutePath());
    }

    public String getChoosedFileName() {
        WebElement chooseFileInput = driver.findElement(By.name("reportFile"));
        return chooseFileInput.getAttribute("value");
    }

    public void clickUpload() {
        WebElement uploadButton = driver.findElement(By.cssSelector("input[type=submit]"));
        uploadButton.click();
    }

    public List<ReportFile> listReportFiles() {
        List<WebElement> records = driver.findElements(By.cssSelector("div.img-report + table tr td"));
        return records.stream().map(record -> {
            String fileName = record.findElement(By.tagName("b")).getText();
            WebElement downloadLink = record.findElement(By.tagName("a"));
            return new ReportFile(fileName, downloadLink);
        }).collect(Collectors.toList());
    }

    public boolean hasRequiredFileErrorMessage() {
        return driver.getPageSource().indexOf("You must choose a file!") > 0;
    }

    public boolean hasInvalidFileErrorMessage() {
        return driver.getPageSource().indexOf("You must choose a file with a valid format!") > 0;
    }

    public boolean hasSuccessMessage() {
        return driver.getPageSource().indexOf("File uploaded successfully.") > 0;
    }
}
