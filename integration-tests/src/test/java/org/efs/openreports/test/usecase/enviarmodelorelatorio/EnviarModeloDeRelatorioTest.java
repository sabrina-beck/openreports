package org.efs.openreports.test.usecase.enviarmodelorelatorio;

import org.efs.openreports.test.BaseIntegrationTest;
import org.efs.openreports.test.pages.*;
import org.junit.Test;

public class EnviarModeloDeRelatorioTest extends BaseIntegrationTest {

    @Test
    public void ct1_enviarModeloDeRelatorio() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("admin");
        loginPage.setPassword("admin");

        GroupsPage groupsPage = loginPage.submit();
        softly.assertThat(groupsPage.hasAccessTo("Administration")).isTrue();

        AdministrationPage administrationPage = groupsPage.clickAdministrationMenu();
        softly.assertThat(administrationPage.hasReports()).isTrue();

        ReportsPage reportsPage = administrationPage.clickReports();
        UploadReportFilesPage uploadReportFilesPage = reportsPage.clickUploadReportFiles();

        // WebElement file_input = driver.findElement(By.id("id_of_button"));
        // file_input.sendKeys("C:\\Selenium\\ImageUpload_FF.exe");

        uploadReportFilesPage.clickUpload();

        softly.assertAll();
    }

    @Test
    public void ct2_enviarModeloDeRelatorioSemArquivo() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("admin");
        loginPage.setPassword("admin");

        GroupsPage groupsPage = loginPage.submit();
        softly.assertThat(groupsPage.hasAccessTo("Administration")).isTrue();

        AdministrationPage administrationPage = groupsPage.clickAdministrationMenu();
        softly.assertThat(administrationPage.hasReports()).isTrue();

        ReportsPage reportsPage = administrationPage.clickReports();
        UploadReportFilesPage uploadReportFilesPage = reportsPage.clickUploadReportFiles();

        uploadReportFilesPage.clickUpload();
        softly.assertThat(driver.getPageSource().contains("exists but is a directory"))

        softly.assertAll();
    }

    @Test
    public void ct3_enviarModeloDeRelatorioInvalido() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("admin");
        loginPage.setPassword("admin");

        GroupsPage groupsPage = loginPage.submit();
        softly.assertThat(groupsPage.hasAccessTo("Administration")).isTrue();

        AdministrationPage administrationPage = groupsPage.clickAdministrationMenu();
        softly.assertThat(administrationPage.hasReports()).isTrue();

        ReportsPage reportsPage = administrationPage.clickReports();
        UploadReportFilesPage uploadReportFilesPage = reportsPage.clickUploadReportFiles();

        // WebElement file_input = driver.findElement(By.id("id_of_button"));
        // file_input.sendKeys("C:\\Selenium\\ImageUpload_FF.exe");

        uploadReportFilesPage.clickUpload();
         softly.assertThat(driver.getPageSource().contains("FileNotFoundException"));

        softly.assertAll();
    }
}
