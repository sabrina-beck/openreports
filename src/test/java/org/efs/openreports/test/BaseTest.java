package org.efs.openreports.test;

import org.assertj.core.api.SoftAssertions;
import org.graphwalker.core.machine.ExecutionContext;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    public static String BASE_URL = "http://localhost:8080/openreports";

    protected WebDriver driver;
    protected SoftAssertions softly;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        softly = new SoftAssertions();
        driver.get(BASE_URL);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
