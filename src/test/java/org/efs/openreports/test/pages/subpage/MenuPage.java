package org.efs.openreports.test.pages.subpage;

import org.efs.openreports.test.pages.AdministrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPage {

    private final WebDriver driver;

    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPresent() {
        return driver.findElements(By.cssSelector("div.menu")).size() > 0;
    }

    public boolean hasAccessToAdministrationPanel() {
        return driver.findElements(By.id("adminButton")).size() > 0;
    }

    public AdministrationPage clickAdministration() {
        WebElement adminButton = driver.findElement(By.id("adminButton"));
        adminButton.click();
        return new AdministrationPage(driver);
    }
}
