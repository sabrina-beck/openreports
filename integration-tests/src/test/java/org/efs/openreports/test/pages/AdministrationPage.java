package org.efs.openreports.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdministrationPage extends AuthenticatedBasePage {
    private static By REPORTS_SELECTOR = By.cssSelector("td.img-report a");

    public AdministrationPage(String baseUrl, WebDriver driver) {
        super(baseUrl, driver);
    }

    public void open() {
        driver.get(baseUrl + "/reportAdmin.action");
    }

    public boolean hasAccessToReports() {
        return driver.findElements(By.linkText("Reports")).size() > 0;
    }

    public ReportsPage clickReports() {
        WebElement link = driver.findElement(REPORTS_SELECTOR);
        link.click();
        return new ReportsPage(baseUrl, driver);
    }

    public boolean hasReports() {
        return driver.findElements(REPORTS_SELECTOR).size() > 0;
    }
}
