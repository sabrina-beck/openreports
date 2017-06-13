package org.efs.openreports.test.enviarmodelo;

import org.assertj.core.api.SoftAssertions;
import org.efs.openreports.test.model.EnviarModeloDeRelatorio;
import org.efs.openreports.test.pages.*;
import org.efs.openreports.test.pages.elements.ReportFile;
import org.efs.openreports.test.pages.subpage.MenuPage;
import org.graphwalker.core.machine.ExecutionContext;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.efs.openreports.test.BaseIntegrationTest.BASE_URL;

public class EnviarModeloDeRelatorioImpl extends ExecutionContext implements EnviarModeloDeRelatorio {

    private final WebDriver driver;
    private final SoftAssertions softly;

    public EnviarModeloDeRelatorioImpl(WebDriver driver, SoftAssertions softly) {

        this.driver = driver;
        this.softly = softly;
    }

    @Override
    public void e_exibe_janela_de_escolha_de_arquivo() {
        // nada a fazer
    }

    @Override
    public void e_usuario_pressiona_botao_de_upload() {
        UploadReportFilesPage uploadReportFilesPage = new UploadReportFilesPage(driver);
        uploadReportFilesPage.clickUpload();
    }

    @Override
    public void v_s4_janela_de_escolha_de_arquivo_aberta() {
        // nao tem como assertar isso
    }

    @Override
    public void v_s6_arquivo_invalido_escolhido() {
        UploadReportFilesPage uploadReportFilesPage = new UploadReportFilesPage(driver);
        softly.assertThat(uploadReportFilesPage.getChoosedFileName()
                .endsWith("invalid_file.ico")).isTrue();
    }

    @Override
    public void e_exibe_mensagem_de_arquivo_invalido() {
        // nada a fazer
    }

    @Override
    public void v_s7_arquivo_enviado() {
        // a verificacao disso eh no estado s9
    }

    @Override
    public void v_s3_botao_de_escolha_de_arquivo_foi_pressionado() {
        //nao temos como assertar isso
    }

    @Override
    public void e_usuario_escolhe_arquivo_valido() {
        File file = Paths.get("src/test/resources/valid_file.jasper").toFile();
        UploadReportFilesPage uploadReportFilesPage = new UploadReportFilesPage(driver);
        uploadReportFilesPage.chooseFile(file);
    }

    @Override
    public void
    e_init() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.setUsername("admin");
        loginPage.setPassword("admin");
        loginPage.submit();
    }

    @Override
    public void v_s2_formulario_de_upload_aberto() {
        UploadReportFilesPage uploadReportFilesPage = new UploadReportFilesPage(driver);
        softly.assertThat(uploadReportFilesPage.isOpen()).isTrue();
    }

    @Override
    public void e_usuario_pressiona_botao_de_escolha_de_arquivo() {
        // nao tem como implementar a escolha de arquivo em passos assim
    }

    @Override
    public void e_pede_arquivo_de_modelo_de_relatorio() {
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.clickUploadReportFiles();
    }

    @Override
    public void v_s8_arquivo_salvo_no_servidor() {
        // a verificacao disso eh no s9
    }

    @Override
    public void v_s10_mensagem_de_arquivo_invalido() {
        UploadReportFilesPage uploadReportFilesPage = new UploadReportFilesPage(driver);

        softly.assertThat(uploadReportFilesPage.hasInvalidFileErrorMessage()).isTrue();
    }

    @Override
    public void e_usuario_escolhe_arquivo_invalido() {
        File file = Paths.get("src/test/resources/invalid_file.ico").toFile();
        UploadReportFilesPage uploadReportFilesPage = new UploadReportFilesPage(driver);
        uploadReportFilesPage.chooseFile(file);
    }

    @Override
    public void e_exibe_novo_arquivo_na_listagem() {
        // automatico
    }

    @Override
    public void v_s9_arquivo_exibido_na_listagem() {
        UploadReportFilesPage uploadReportFilesPage = new UploadReportFilesPage(driver);
        List<ReportFile> newReportFiles = uploadReportFilesPage.listReportFiles()
                .stream()
                .filter(reportFile -> reportFile.getReportFileName().equals("valid_file.jasper"))
                .collect(Collectors.toList());

        softly.assertThat(newReportFiles).hasSize(1);

        ReportFile reportFile = newReportFiles.get(0);
        softly.assertThat(reportFile.getReportFileName()).isEqualTo("valid_file.jasper");
        softly.assertThat(reportFile.getDownloadLink().getAttribute("href"))
                .isEqualTo(BASE_URL + "/reportUpload.action?command=download&revision=valid_file.jasper");
    }

    @Override
    public void v_s5_arquivo_valido_escolhido() {
        UploadReportFilesPage uploadReportFilesPage = new UploadReportFilesPage(driver);
        softly.assertThat(uploadReportFilesPage.getChoosedFileName()
                .endsWith("valid_file.jasper")).isTrue();
    }

    @Override
    public void v_s11_mensagem_de_arquivo_obrigatorio() {
        UploadReportFilesPage uploadReportFilesPage = new UploadReportFilesPage(driver);

        softly.assertThat(uploadReportFilesPage.hasRequiredFileErrorMessage()).isTrue();
    }

    @Override
    public void v_s1_usuario_logado_e_com_permissao() {
        GroupsPage groupsPage = new GroupsPage(driver);

        MenuPage menu = groupsPage.getMenu();

        softly.assertThat(menu.isPresent()).isTrue();
        softly.assertThat(menu.hasAccessToAdministrationPanel()).isTrue();

        AdministrationPage administrationPage = menu.clickAdministration();
        softly.assertThat(administrationPage.hasAccessToReports()).isTrue();

        ReportsPage reportsPage = administrationPage.clickReports();
        softly.assertThat(reportsPage.hasAccessToUploadReportFiles()).isTrue();
    }

    @Override
    public void e_faz_upload_do_arquivo() {
        // automatico
    }

    @Override
    public void e_pede_arquivo_de_modelo_de_relatorio_novamente() {
        // automatico
    }

    @Override
    public void e_exibe_mensagem_arquivo_enviado_com_sucesso() {
        // automatico
    }

    @Override
    public void v_s12_mensagem_de_arquivo_enviado_com_sucesso() {
        UploadReportFilesPage uploadReportFilesPage = new UploadReportFilesPage(driver);
        softly.assertThat(uploadReportFilesPage.hasSuccessMessage()).isTrue();
    }

}
