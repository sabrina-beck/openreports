package org.efs.openreports.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdministrationPage extends AuthenticatedBasePage {
    public AdministrationPage(String baseUrl, WebDriver driver) {
        super(baseUrl, driver);
    }

    public boolean hasAccessToReports() {
        return driver.findElements(By.linkText("Reports")).size() > 0;
    }

    public ReportsPage clickReports() {
        WebElement link = driver.findElement(By.cssSelector("td.img-report a"));
        link.click();
        return new ReportsPage(baseUrl, driver);
    }
}
