package org.efs.openreports.test.pages;

import org.efs.openreports.test.pages.subpage.MenuPage;
import org.openqa.selenium.WebDriver;

public abstract class AuthenticatedBasePage {

    protected final WebDriver driver;
    private final MenuPage menu;

    public AuthenticatedBasePage(WebDriver driver) {
        this.driver = driver;
        this.menu = new MenuPage(driver);
    }

    public MenuPage getMenu() {
        return menu;
    }
}
