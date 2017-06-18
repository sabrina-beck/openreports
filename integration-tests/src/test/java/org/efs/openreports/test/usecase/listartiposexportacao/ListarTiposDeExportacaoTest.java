package org.efs.openreports.test.usecase.listartiposexportacao;

import org.efs.openreports.test.BaseIntegrationTest;
import org.efs.openreports.test.pages.*;
import org.junit.Test;

public class ListarTiposDeExportacaoTest extends BaseIntegrationTest {

    @Test
    public void ct1_visualizarListaTiposDeExportacao() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("admin");
        loginPage.setPassword("admin");

        GroupsPage groupsPage = loginPage.submit();
        softly.assertThat(groupsPage.hasAccessTo("BirtReports")).isTrue();

        ReportsPage reportsPage = groupsPage.clickGroup("BirtReports");
        softly.assertThat(reportsPage.hasReport("Customer List (BIRT)")).isTrue();

        ReportPage reportPage = reportsPage.clickReport("Customer List (BIRT)");

        softly.assertThat(reportPage.hasExportTypeList()).isTrue();

        softly.assertAll();
    }


    @Test
    public void ct2_RelatorioNaoCadastrado() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("user");
        loginPage.setPassword("user");

        GroupsPage groupsPage = loginPage.submit();
        softly.assertThat(groupsPage.hasAccessTo("JasperReports")).isTrue();

        groupsPage.clickGroup("JasperReports");

        ReportPage reportPage = new ReportPage(BASE_URL, driver);
        reportPage.open(200);

        softly.assertThat(reportPage.hasNotFoundReportMessage()).isTrue();

        softly.assertAll();
    }

    @Test
    public void ct3_RelatorioNaoAutorizadoParaOUsuario() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("user");
        loginPage.setPassword("user");

        GroupsPage groupsPage = loginPage.submit();
        softly.assertThat(groupsPage.hasAccessTo("JasperReports")).isTrue();

        groupsPage.clickGroup("JasperReports");

        ReportPage reportPage = new ReportPage(BASE_URL, driver);
        reportPage.open(59);

        softly.assertThat(reportPage.hasNotAuthorizedToSeeReportMessage()).isTrue();

        softly.assertAll();
    }

    @Test
    public void ct4_GrupoDeRelatoriosNaoCadastrado() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("user");
        loginPage.setPassword("user");

        GroupsPage groupsPage = loginPage.submit();
        groupsPage.open(100);

        softly.assertThat(groupsPage.hasNotFoundGroupMessage()).isTrue();

        softly.assertAll();
    }

    @Test
    public void ct5_GrupoDeRelatoriosNaoAutorizadoParaOUsuario() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("user");
        loginPage.setPassword("user");

        GroupsPage groupsPage = loginPage.submit();
        groupsPage.open(7);

        softly.assertThat(groupsPage.hasNotAuthorizedToSeeGroupMessage()).isTrue();

        softly.assertAll();
    }
}
