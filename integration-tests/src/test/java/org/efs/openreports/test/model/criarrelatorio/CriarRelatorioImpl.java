package org.efs.openreports.test.model.criarrelatorio;

import org.assertj.core.api.SoftAssertions;
import org.efs.openreports.test.model.CriarRelatorio;
import org.efs.openreports.test.pages.AddReportPage;
import org.efs.openreports.test.pages.AdministrationPage;
import org.efs.openreports.test.pages.LoginPage;
import org.efs.openreports.test.pages.ReportsPage;
import org.graphwalker.core.machine.ExecutionContext;
import org.openqa.selenium.WebDriver;

import static org.efs.openreports.test.BaseIntegrationTest.BASE_URL;

public class CriarRelatorioImpl extends ExecutionContext implements CriarRelatorio {

    private final WebDriver driver;
    private final SoftAssertions softly;

    private boolean validReport = false;
    private int reportIndex = 0;

    public CriarRelatorioImpl(WebDriver driver, SoftAssertions softly) {

        this.driver = driver;
        this.softly = softly;
    }

    @Override
    public void v_s6_exibe_campos_para_criar_novo_relatorio() {
        AddReportPage addReportPage = new AddReportPage(BASE_URL, driver);
        softly.assertThat(addReportPage.isOpen()).isTrue();
        softly.assertThat(addReportPage.hasReportForm()).isTrue();
    }

    @Override
    public void v_s1_usuario_logado() {
        AdministrationPage administrationPage = new AdministrationPage(BASE_URL, driver);
        administrationPage.open();
        softly.assertThat(administrationPage.hasAccessToReports()).isTrue();
    }

    @Override
    public void e_usuario_pressiona_botao_de_add_reports() {

    }

    @Override
    public void e_novo_relatorio_criado() {
        AddReportPage addReportPage = new AddReportPage(BASE_URL, driver);
        addReportPage.clickSave();
    }

    @Override
    public void v_s7_usuario_preenche_informacoes_relatorio() {
        if (validReport) {
            AddReportPage addReportPage = new AddReportPage(BASE_URL, driver);
            addReportPage.fillReportForm("Test Report" + reportIndex);
        }
    }

    @Override
    public void v_s2_sistema_exibe_opcoes_disponiveis() {
        AdministrationPage administrationPage = new AdministrationPage(BASE_URL, driver);
        softly.assertThat(administrationPage.hasAccessToReports()).isTrue();
    }

    @Override
    public void v_s4_listagem_de_relatorios_exibida() {
        ReportsPage reportsPage = new ReportsPage(BASE_URL, driver);
        softly.assertThat(reportsPage.hasReportList()).isTrue();
    }

    @Override
    public void v_s5_botao_add_reports_pressionado() {
        AddReportPage addReportPage = new AddReportPage(BASE_URL, driver);
        addReportPage.hasReportForm();
    }

    @Override
    public void e_usuario_pressiona_add_reports() {
        ReportsPage reportsPage = new ReportsPage(BASE_URL, driver);
        reportsPage.clickAddReport();
    }

    @Override
    public void e_exibe_mensagem_de_informacoes_invalidas() {
        AddReportPage addReportPage = new AddReportPage(BASE_URL, driver);
        addReportPage.clickSave();
    }

    @Override
    public void e_usuaario_preenche_informacoes_novamente() {
        validReport = !validReport;
    }

    @Override
    public void e_informacoes_validas() {

    }

    @Override
    public void v_s12_mensagem_de_informacoes_invalidas() {
        AddReportPage addReportPage = new AddReportPage(BASE_URL, driver);
        addReportPage.hasInvalidReportMessage();
    }

    @Override
    public void e_pressiona_botao_administration() {

    }

    @Override
    public void e_exibe_todos_os_relatorios() {

    }

    @Override
    public void e_exibe_janela_de_escolha_de_arquivo() {

    }

    @Override
    public void v_s11_informacoes_sao_invalidas() {
        softly.assertThat(validReport).isFalse();
    }

    @Override
    public void e_usuario_pressiona_botao_reports() {
        AdministrationPage administrationPage = new AdministrationPage(BASE_URL, driver);
        administrationPage.clickReports();
    }

    @Override
    public void e_init() {
        LoginPage loginPage = new LoginPage(BASE_URL, driver);

        loginPage.setUsername("admin");
        loginPage.setPassword("admin");
        loginPage.submit();
    }

    @Override
    public void v_s8_informacoes_sao_validas() {
        softly.assertThat(validReport).isTrue();
    }

    @Override
    public void v_s9_novo_relatorio_criado_com_sucesso() {
        ReportsPage reportsPage = new ReportsPage(BASE_URL, driver);
        softly.assertThat(reportsPage.isOpen()).isTrue();
    }

    @Override
    public void e_usuario_escolhe_arquivo_invalido() {

    }

    @Override
    public void e_cria_relatorio_novamente() {
        validReport = !validReport;
        AdministrationPage administrationPage = new AdministrationPage(BASE_URL, driver);
        administrationPage.open();
    }

    @Override
    public void v_s3_botao_reports_foi_pressionado() {
        ReportsPage reportsPage = new ReportsPage(BASE_URL, driver);
        softly.assertThat(reportsPage.hasReportList()).isTrue();
    }

    @Override
    public void v_s10_exibe_todos_os_relatorios() {
        ReportsPage reportsPage = new ReportsPage(BASE_URL, driver);
        softly.assertThat(reportsPage.hasReport("Test Report" + reportIndex++)).isTrue();
    }

    @Override
    public void e_faz_upload_do_arquivo() {

    }
}
