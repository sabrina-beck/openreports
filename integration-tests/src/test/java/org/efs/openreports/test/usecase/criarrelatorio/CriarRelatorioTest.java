package org.efs.openreports.test.usecase.listartiposexportacao;

import org.efs.openreports.test.BaseIntegrationTest;
import org.efs.openreports.test.pages.*;
import org.junit.Test;

public class CriarRelatorioTest extends BaseIntegrationTest {

    @Test
    public void ct1_relatorioCriado() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("user");
        loginPage.setPassword("user");

        ReportCreate reportCreate = new ReportCreate(BASE_URL, driver);
        reportCreate.setName("NomeRelatorio");
        reportCreate.clickSave();
        softly.assertThat(reportCreate.hasSaveOk()).isTrue();

        softly.assertAll();
    }


    @Test
    public void ct2_NomeVazioRelatorio() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("user");
        loginPage.setPassword("user");

        ReportCreate reportCreate = new ReportCreate(BASE_URL, driver);
        reportCreate.setName("");
        reportCreate.clickSave();
        softly.assertThat(reportCreate.hasEmptyName()).isTrue();

        softly.assertAll();
    }

    @Test
    public void ct3_NomeDuplicadoRelatorio() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("user");
        loginPage.setPassword("user");

        ReportCreate reportCreate = new ReportCreate(BASE_URL, driver);
        reportCreate.setName("NomeRelatorio");
        reportCreate.clickSave();
        softly.assertThat(reportCreate.hasDuplicateName()).isTrue();

        softly.assertAll();
    }

    @Test
    public void ct4_NomeInvalidoRelatorio() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("user");
        loginPage.setPassword("user");

        ReportCreate reportCreate = new ReportCreate(BASE_URL, driver);
        reportCreate.setName("&'Nome'&");
        reportCreate.clickSave();
        softly.assertThat(reportCreate.hasInvalidName()).isTrue();

        softly.assertAll();
    }

   
}
