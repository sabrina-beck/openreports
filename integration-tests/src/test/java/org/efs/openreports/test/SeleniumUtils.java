package org.efs.openreports.test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class SeleniumUtils {
    public static void executeScript(WebDriver driver, String script) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript(script);
        } else {
            throw new IllegalStateException("This driver does not support JavaScript!");
        }
    }
}
