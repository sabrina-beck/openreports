package org.efs.openreports.test.pages;

import org.efs.openreports.ReportConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

import static org.efs.openreports.test.SeleniumUtils.executeScript;

public class ReportCreate extends AuthenticatedBasePage {
	
	public ReportCreate(String baseUrl, WebDriver driver) {
        super(baseUrl, driver);
    }

	public void open() {
		driver.get(baseUrl + "/editReport.action?command=add");
	}

	public void clickSave() {
		WebElement saveButton = getButtonByName("Save");
		saveButton.click();
	}

	public void setName(String nameRel) {
		WebElement chooseFileInput = driver.findElement(By.name("name"));
		chooseFileInput.sendKeys(nameRel);
	}

	private WebElement getButtonByName(String buttonName) {
		return driver.findElements(By.cssSelector("input[type=submit]")).stream()
				.filter(webElement -> webElement.getAttribute("value").equals(buttonName)).findFirst().get();
	}

	public boolean hasInvalidName() {
		return driver.getPageSource().indexOf("Invalid Name Report") > 0;
	}

	public boolean hasEmptyName() {
		return driver.getPageSource().indexOf("Empty Name Report") > 0;
	}

	public boolean hasDuplicateName() {
		return driver.getPageSource().indexOf("Duplicated Name Report ") > 0;
	}
	
	public boolean hasSaveOk() {
		return driver.getPageSource().indexOf("Saved") > 0;
	}


}
