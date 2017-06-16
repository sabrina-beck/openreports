package org.efs.openreports.test.pages;

import org.efs.openreports.test.pages.subpage.MenuSubPage;
import org.openqa.selenium.WebDriver;

public abstract class AuthenticatedBasePage {

    protected final String baseUrl;
    protected final WebDriver driver;
    private final MenuSubPage menu;

    public AuthenticatedBasePage(String baseUrl, WebDriver driver) {
        this.baseUrl = baseUrl;
        this.driver = driver;
        this.menu = new MenuSubPage(baseUrl, driver);
    }

    public MenuSubPage getMenu() {
        return menu;
    }
}
