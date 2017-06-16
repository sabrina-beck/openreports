package org.efs.openreports.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupsPage extends AuthenticatedBasePage {
    public GroupsPage(String baseUrl, WebDriver driver) {
        super(baseUrl, driver);
    }

    public boolean hasAccessTo(String groupName) {
        return driver.findElements(By.linkText(groupName)).size() > 0;
    }

    public void open(int groupId) {
        driver.get(baseUrl + "/reportList.action?groupId=" + groupId);
    }

    public ReportsPage clickGroup(String groupName) {
        driver.findElement(By.linkText(groupName)).click();
        return new ReportsPage(baseUrl, driver);
    }

    public boolean hasNotAuthorizedToSeeGroupMessage() {
        return driver.getPageSource().indexOf("You are not authorized to view this Group.") > 0;
    }

    public boolean hasNotFoundGroupMessage() {
        return driver.getPageSource().indexOf("Group not found.") > 0;
    }
}
