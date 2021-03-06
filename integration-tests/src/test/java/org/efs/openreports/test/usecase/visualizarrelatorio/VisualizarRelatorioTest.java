package org.efs.openreports.test.usecase.visualizarrelatorio;

import org.efs.openreports.ReportConstants;
import org.efs.openreports.test.BaseIntegrationTest;
import org.efs.openreports.test.pages.*;
import org.junit.Test;

public class VisualizarRelatorioTest extends BaseIntegrationTest {

    @Test
    public void ct1_visualizarRelatorioNaJanelaCorrenteEmPDF() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("admin");
        loginPage.setPassword("admin");

        GroupsPage groupsPage = loginPage.submit();
        softly.assertThat(groupsPage.hasAccessTo("BirtReports")).isTrue();

        ReportsPage reportsPage = groupsPage.clickGroup("BirtReports");
        softly.assertThat(reportsPage.hasReport("Customer List (BIRT)")).isTrue();

        ReportPage reportPage = reportsPage.clickReport("Customer List (BIRT)");
        boolean couldSelectExportType = reportPage.forceSelectExportType(ReportConstants.ExportType.PDF);
        softly.assertThat(couldSelectExportType).isTrue();

        ExportedReportPage exportedReportPage = reportPage.clickRun();
        softly.assertThat(exportedReportPage.isPdfLoaded()).isTrue();
        softly.assertThat(exportedReportPage.isInNewWindow()).isFalse();

        softly.assertAll();
    }

    @Test
    public void ct2_visualizarRelatorioEmNovaJanelaEmHTML() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("admin");
        loginPage.setPassword("admin");

        GroupsPage groupsPage = loginPage.submit();
        softly.assertThat(groupsPage.hasAccessTo("BirtReports")).isTrue();

        ReportsPage reportsPage = groupsPage.clickGroup("BirtReports");
        softly.assertThat(reportsPage.hasReport("Customer List (BIRT)")).isTrue();

        ReportPage reportPage = reportsPage.clickReport("Customer List (BIRT)");
        boolean couldSelectExportType = reportPage.forceSelectExportType(ReportConstants.ExportType.HTML);
        softly.assertThat(couldSelectExportType).isTrue();

        ExportedReportPage exportedReportPage = reportPage.clickRunInNewWindow();
        softly.assertThat(exportedReportPage.isHtmlLoaded()).isTrue();
        softly.assertThat(exportedReportPage.isInNewWindow()).isTrue();

        softly.assertAll();
    }

    @Test
    public void ct3_tipoDeExportacaoInvalido() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("admin");
        loginPage.setPassword("admin");

        GroupsPage groupsPage = loginPage.submit();
        softly.assertThat(groupsPage.hasAccessTo("BirtReports")).isTrue();

        ReportsPage reportsPage = groupsPage.clickGroup("BirtReports");
        softly.assertThat(reportsPage.hasReport("Customer List (BIRT)")).isTrue();

        ReportPage reportPage = reportsPage.clickReport("Customer List (BIRT)");
        reportPage.forceSelectExportType(10);

        reportPage.clickRun();
        softly.assertThat(reportPage.hasInvalidExportTypeMessage()).isTrue();

        softly.assertAll();
    }

    @Test
    public void ct4_tipoDeExportacaoIndisponivelParaORelatorioEscolhido() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("admin");
        loginPage.setPassword("admin");

        GroupsPage groupsPage = loginPage.submit();
        softly.assertThat(groupsPage.hasAccessTo("BirtReports")).isTrue();

        ReportsPage reportsPage = groupsPage.clickGroup("BirtReports");
        softly.assertThat(reportsPage.hasReport("Customer List (BIRT)")).isTrue();

        ReportPage reportPage = reportsPage.clickReport("Customer List (BIRT)");
        reportPage.forceSelectExportType(ReportConstants.ExportType.CSV.getCode());

        reportPage.clickRun();
        softly.assertThat(reportPage.hasInvalidExportTypeMessage()).isTrue();

        softly.assertAll();
    }

    @Test
    public void ct5_RelatorioNaoCadastrado() {
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
    public void ct6_RelatorioNaoAutorizadoParaOUsuario() {
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
    public void ct7_GrupoDeRelatoriosNaoCadastrado() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("user");
        loginPage.setPassword("user");

        GroupsPage groupsPage = loginPage.submit();
        groupsPage.open(100);

        softly.assertThat(groupsPage.hasNotFoundGroupMessage()).isTrue();

        softly.assertAll();
    }

    @Test
    public void ct8_GrupoDeRelatoriosNaoAutorizadoParaOUsuario() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);
        loginPage.setUsername("user");
        loginPage.setPassword("user");

        GroupsPage groupsPage = loginPage.submit();
        groupsPage.open(7);

        softly.assertThat(groupsPage.hasNotAuthorizedToSeeGroupMessage()).isTrue();

        softly.assertAll();
    }
}
