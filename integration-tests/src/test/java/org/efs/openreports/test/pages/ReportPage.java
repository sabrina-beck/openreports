package org.efs.openreports.test.pages;

import org.efs.openreports.ReportConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

import static org.efs.openreports.test.SeleniumUtils.executeScript;

public class ReportPage extends AuthenticatedBasePage {
    public ReportPage(String baseUrl, WebDriver driver) {
        super(baseUrl, driver);
    }

    public void open(int reportId) {
        driver.get(baseUrl + "/reportDetail.action?reportId=" + reportId);
    }

    public boolean forceSelectExportType(ReportConstants.ExportType exportTypeName) {
        Optional<WebElement> radioButton = driver.findElements(By.cssSelector("input[type=radio]"))
                .stream()
                .filter(webElement ->
                        Integer.parseInt(webElement.getAttribute("value")) == exportTypeName.getCode())
                .findFirst();

        radioButton.ifPresent(button -> button.click());
        return radioButton.map(webElement -> true).orElse(false);
    }

    public ExportedReportPage clickRun() {
        WebElement runButton = getButtonByName("Run");

        runButton.click();
        return new ExportedReportPage(driver);
    }

    public ExportedReportPage clickRunInNewWindow() {
        WebElement runButton = getButtonByName("Run in New Window");

        runButton.click();
        return new ExportedReportPage(driver);
    }

    private WebElement getButtonByName(String buttonName) {
        return driver.findElements(By.cssSelector("input[type=submit]"))
                .stream()
                .filter(webElement -> webElement.getAttribute("value").equals(buttonName))
                .findFirst()
                .get();
    }

    public void forceSelectExportType(int exportTypeCode) {
        executeScript(driver,
                "document.querySelector('input[type=radio]').setAttribute('value', '" + exportTypeCode + "')");
        WebElement radioButton = driver.findElement(By.cssSelector("input[type=radio]"));
        radioButton.sendKeys(Integer.toString(exportTypeCode));
    }

    public boolean hasInvalidExportTypeMessage() {
        return driver.getPageSource().indexOf("Invalid export type!") > 0;
    }

    public boolean hasNotAuthorizedToSeeReportMessage() {
        return driver.getPageSource().indexOf("You are not authorized to view this Report.") > 0;
    }

    public boolean hasNotFoundReportMessage() {
        return driver.getPageSource().indexOf("Report not found.") > 0;
    }

}
