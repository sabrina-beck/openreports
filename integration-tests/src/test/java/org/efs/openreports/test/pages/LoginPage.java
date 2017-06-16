package org.efs.openreports.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private final String baseUrl;
    private final WebDriver driver;

    public LoginPage(String baseUrl, WebDriver driver) {
        this.baseUrl = baseUrl;
        this.driver = driver;
    }

    public void setUsername(String username) {
        WebElement input = driver.findElement(By.id("userName"));
        input.sendKeys(username);
    }

    public String getUsername() {
        WebElement input = driver.findElement(By.id("userName"));
        return input.getAttribute("value");
    }

    public void setPassword(String password) {
        WebElement input = driver.findElement(By.cssSelector("input[type=password]"));
        input.sendKeys(password);
    }

    public String getPassword() {
        WebElement input = driver.findElement(By.cssSelector("input[type=password]"));
        return input.getAttribute("value");
    }

    public GroupsPage submit() {
        WebElement button = driver.findElement(By.cssSelector("input[type=submit]"));
        button.click();
        return new GroupsPage(baseUrl, driver);
    }
}
