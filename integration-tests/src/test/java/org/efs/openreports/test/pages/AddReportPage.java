package org.efs.openreports.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddReportPage extends AuthenticatedBasePage {
    public AddReportPage(String baseUrl, WebDriver driver) {
        super(baseUrl, driver);
    }

    public boolean isOpen() {
        return driver.getCurrentUrl().endsWith("editReport.action?command=add");
    }

    public void clickSave() {
        WebElement saveButton = driver.findElement(By.cssSelector("input[type=submit]"));
        saveButton.click();
    }

    public void fillReportForm(String report) {
        WebElement nameInput = driver.findElement(By.name("name"));
        nameInput.sendKeys(report);
    }

    public boolean hasReportForm() {
        return driver.findElements(By.tagName("form")).size() > 0;
    }

    public boolean hasInvalidReportMessage() {
        return driver.getPageSource().indexOf("error.report.invalid") > 0;
    }

}
