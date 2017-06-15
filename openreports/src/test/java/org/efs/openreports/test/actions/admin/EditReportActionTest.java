package org.efs.openreports.test.actions.admin;

import org.efs.openreports.actions.admin.EditReportAction;
import org.efs.openreports.objects.*;
import org.efs.openreports.providers.*;
import org.efs.openreports.test.fixture.*;
import org.efs.openreports.util.LocalStrings;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditReportActionTest {

    @Test
    public void ct1_chargeEditionData_shouldRecoveryDataFromRequestedReport() throws ProviderException {
        Report report = ReportFixture.aReport();

        ReportProvider reportProvider = mock(ReportProvider.class);
        when(reportProvider.getReport(report.getId())).thenReturn(report);

        TagProvider tagProvider = mock(TagProvider.class);
        String tags = "MY TAG";
        when(tagProvider.getTagsForObject(report.getId(), Report.class, ORTag.TAG_TYPE_UI))
                .thenReturn(tags);

        EditReportAction action = new EditReportAction();
        action.setCommand("edit");
        action.setId(report.getId());
        action.setReportProvider(reportProvider);
        action.setSubmitOk(null);
        action.setSubmitValidate(null);
        action.setSubmitDuplicate(null);
        action.setTagProvider(tagProvider);

        String result = action.execute();

        assertThat(result).isEqualTo(INPUT);

        assertThat(action.getReport()).isEqualTo(report);
        assertThat(action.getName()).isEqualTo(report.getName());
        assertThat(action.getDescription()).isEqualTo(report.getDescription());
        assertThat(action.getFile()).isEqualTo(report.getFile());
        assertThat(action.getQuery()).isEqualTo(report.getQuery());
        assertThat(action.getId()).isEqualTo(report.getId());
        assertThat(action.isPdfExportEnabled()).isEqualTo(report.isPdfExportEnabled());
        assertThat(action.isCsvExportEnabled()).isEqualTo(report.isCsvExportEnabled());
        assertThat(action.isHtmlExportEnabled()).isEqualTo(report.isHtmlExportEnabled());
        assertThat(action.isXlsExportEnabled()).isEqualTo(report.isXlsExportEnabled());
        assertThat(action.isRtfExportEnabled()).isEqualTo(report.isRtfExportEnabled());
        assertThat(action.isTextExportEnabled()).isEqualTo(report.isTextExportEnabled());
        assertThat(action.isExcelExportEnabled()).isEqualTo(report.isExcelExportEnabled());
        assertThat(action.isImageExportEnabled()).isEqualTo(report.isImageExportEnabled());
        assertThat(action.isVirtual()).isEqualTo(report.isVirtualizationEnabled());
        assertThat(action.isHidden()).isEqualTo(report.isHidden());
        assertThat(action.getDataSourceId()).isEqualTo(report.getDataSource().getId().intValue());
        assertThat(action.getReportChartId()).isEqualTo(report.getReportChart().getId().intValue());
        assertThat(action.getTags()).isEqualTo(tags);
    }

    @Test
    public void ct2_createNewReportWithoutName_shouldReturnError() {
        EditReportAction action = new EditReportAction();
        action.setCommand("add");
        action.setName(null);
        action.setSubmitOk("ok");
        action.setSubmitValidate("ok");
        action.setSubmitDuplicate("ok");

        String result = action.execute();

        assertThat(result).isEqualTo(INPUT);
        assertThat(action.getActionErrors()).hasSize(1)
                .contains(LocalStrings.ERROR_REPORT_INVALID);
    }

    @Test
    public void ct3_editReportWithSubmitValidateWithoutQuery_shouldReturnError() throws ProviderException {
        Report report = ReportFixture.aReport();
        String originalName = report.getName();

        ReportProvider reportProvider = mock(ReportProvider.class);
        when(reportProvider.getReport(report.getId())).thenReturn(report);

        ReportDataSource newReportDataSource = ReportDataSourceFixture.aDataSource();
        DataSourceProvider dataSourceProvider = mock(DataSourceProvider.class);
        when(dataSourceProvider.getDataSource(newReportDataSource.getId()))
                .thenReturn(newReportDataSource);

        ReportChart newReportChart = ReportChartFixture.aReportChart(report);
        ChartProvider chartProvider = mock(ChartProvider.class);
        when(chartProvider.getReportChart(newReportChart.getId()))
                .thenReturn(newReportChart);

        EditReportAction action = new EditReportAction();

        action.setReportProvider(reportProvider);
        action.setDataSourceProvider(dataSourceProvider);
        action.setChartProvider(chartProvider);

        action.setId(report.getId());
        action.setCommand("edit");
        action.setSubmitOk("ok");
        action.setSubmitValidate("ok");
        action.setSubmitDuplicate("ok");
        action.setName(originalName);
        action.setQuery(null);
        action.setCsvExportEnabled(false);
        action.setHtmlExportEnabled(false);
        action.setPdfExportEnabled(false);
        action.setXlsExportEnabled(false);
        action.setRtfExportEnabled(false);
        action.setTextExportEnabled(false);
        action.setExcelExportEnabled(false);
        action.setDataSourceId(newReportDataSource.getId());
        action.setReportChartId(newReportChart.getId());
        action.setDescription("new description");
        action.setFile("valid.jasper");

        String result = action.execute();

        assertThat(result).isEqualTo(INPUT);

        assertThat(action.getReport().getParameters()).isEqualTo(report.getParameters());
        assertThat(action.getReport().isDisplayInline()).isEqualTo(report.isDisplayInline());

        assertThat(action.getReport().getName()).isEqualTo("Copy of " + originalName);
        assertThat(action.getReport().getDescription()).isEqualTo(action.getDescription());
        assertThat(action.getReport().getFile()).isEqualTo(action.getFile());
        assertThat(action.getReport().getQuery()).isEqualTo(action.getQuery());
        assertThat(action.getReport().isCsvExportEnabled())
                .isEqualTo(action.isCsvExportEnabled());
        assertThat(action.getReport().isHtmlExportEnabled())
                .isEqualTo(action.isHtmlExportEnabled());
        assertThat(action.getReport().isPdfExportEnabled()).isTrue();
        assertThat(action.getReport().isXlsExportEnabled())
                .isEqualTo(action.isXlsExportEnabled());
        assertThat(action.getReport().isRtfExportEnabled())
                .isEqualTo(action.isRtfExportEnabled());
        assertThat(action.getReport().isTextExportEnabled())
                .isEqualTo(action.isTextExportEnabled());
        assertThat(action.getReport().isExcelExportEnabled())
                .isEqualTo(action.isExcelExportEnabled());
        assertThat(action.getReport().isImageExportEnabled())
                .isEqualTo(action.isImageExportEnabled());
        assertThat(action.getReport().isVirtualizationEnabled())
                .isEqualTo(action.isVirtual());
        assertThat(action.getReport().isHidden())
                .isEqualTo(action.isHidden());
        assertThat(action.getReport().getDataSource()).isEqualTo(newReportDataSource);
        assertThat(action.getReport().getReportChart()).isEqualTo(newReportChart);

    }

    @Test
    public void ct4_withSubmitValidate_shouldValidate() throws ProviderException {
        Report report = ReportFixture.aReport();
        String newName = "New name";

        ReportProvider reportProvider = mock(ReportProvider.class);
        when(reportProvider.getReport(report.getId())).thenReturn(report);

        ReportParameter reportParameter = ReportParameterFixture.aReportParameter();
        ParameterProvider parameterProvider = mock(ParameterProvider.class);
        when(parameterProvider.getReportParameter(anyString()))
                .thenReturn(reportParameter);

        EditReportAction action = new EditReportAction();

        action.setReportProvider(reportProvider);
        action.setParameterProvider(parameterProvider);

        action.setId(report.getId());
        action.setCommand("edit");
        action.setSubmitOk("ok");
        action.setSubmitValidate("ok");
        action.setSubmitDuplicate(null);
        action.setName(newName);
        action.setQuery("SELECT * FROM people WHERE name = $P;");
        action.setCsvExportEnabled(true);
        action.setHtmlExportEnabled(false);
        action.setPdfExportEnabled(false);
        action.setXlsExportEnabled(false);
        action.setRtfExportEnabled(false);
        action.setTextExportEnabled(false);
        action.setExcelExportEnabled(false);
        action.setDataSourceId(-1);
        action.setReportChartId(-1);
        action.setDescription("new description");
        action.setFile("valid.jasper");

        String result = action.execute();

        assertThat(result).isEqualTo(INPUT);

        assertThat(action.getReport().getParameters()).isEqualTo(report.getParameters());
        assertThat(action.getReport().isDisplayInline()).isEqualTo(report.isDisplayInline());

        assertThat(action.getReport().getName()).isEqualTo(newName);
        assertThat(action.getReport().getDescription()).isEqualTo(action.getDescription());
        assertThat(action.getReport().getFile()).isEqualTo(action.getFile());
        assertThat(action.getReport().getQuery()).isEqualTo(action.getQuery());
        assertThat(action.getReport().isCsvExportEnabled())
                .isEqualTo(action.isCsvExportEnabled());
        assertThat(action.getReport().isHtmlExportEnabled())
                .isEqualTo(action.isHtmlExportEnabled());
        assertThat(action.getReport().isPdfExportEnabled())
                .isEqualTo(action.isPdfExportEnabled());
        assertThat(action.getReport().isXlsExportEnabled())
                .isEqualTo(action.isXlsExportEnabled());
        assertThat(action.getReport().isRtfExportEnabled())
                .isEqualTo(action.isRtfExportEnabled());
        assertThat(action.getReport().isTextExportEnabled())
                .isEqualTo(action.isTextExportEnabled());
        assertThat(action.getReport().isExcelExportEnabled())
                .isEqualTo(action.isExcelExportEnabled());
        assertThat(action.getReport().isImageExportEnabled())
                .isEqualTo(action.isImageExportEnabled());
        assertThat(action.getReport().isVirtualizationEnabled())
                .isEqualTo(action.isVirtual());
        assertThat(action.getReport().isHidden())
                .isEqualTo(action.isHidden());
        assertThat(action.getReport().getDataSource()).isNull();
        assertThat(action.getReport().getReportChart()).isNull();

    }

    @Test
    @Ignore
    public void ct5_createNewReportWithDuplicate_shouldCreateSuccessfully() throws ProviderException {
        String newName = "New name";

        Report report = ReportFixture.aReport();
        List<ReportParameterMap> reportParameterMaps = Arrays.asList(
                ReportParameterMapFixture.aParameterMapFixture(report, 0));
        report.setParameters(reportParameterMaps);

        ReportProvider reportProvider = mock(ReportProvider.class);
        when(reportProvider.insertReport(report)).thenReturn(report);

        TagProvider tagProvider = mock(TagProvider.class);

        EditReportAction action = new EditReportAction();

        action.setReportProvider(reportProvider);
        action.setTagProvider(tagProvider);

        action.setId(report.getId());
        action.setCommand("add");

        action.setSubmitOk("ok");
        action.setSubmitValidate(null);
        action.setSubmitDuplicate("ok");

        action.setName(newName);

        action.setQuery("SELECT * FROM people WHERE name = $P;");
        action.setCsvExportEnabled(true);
        action.setHtmlExportEnabled(false);
        action.setPdfExportEnabled(true);
        action.setXlsExportEnabled(false);
        action.setRtfExportEnabled(false);
        action.setTextExportEnabled(false);
        action.setExcelExportEnabled(false);
        action.setDataSourceId(-1);
        action.setReportChartId(-1);
        action.setDescription("new description");
        action.setFile("valid.jasper");

        String result = action.execute();

        assertThat(result).isEqualTo(SUCCESS);

        assertThat(action.getReport().getParameters()).isEqualTo(report.getParameters());
        assertThat(action.getReport().isDisplayInline()).isEqualTo(report.isDisplayInline());

        assertThat(action.getReport().getName()).isEqualTo(newName);
        assertThat(action.getReport().getDescription()).isEqualTo(action.getDescription());
        assertThat(action.getReport().getFile()).isEqualTo(action.getFile());
        assertThat(action.getReport().getQuery()).isEqualTo(action.getQuery());
        assertThat(action.getReport().isCsvExportEnabled())
                .isEqualTo(action.isCsvExportEnabled());
        assertThat(action.getReport().isHtmlExportEnabled())
                .isEqualTo(action.isHtmlExportEnabled());
        assertThat(action.getReport().isPdfExportEnabled())
                .isEqualTo(action.isPdfExportEnabled());
        assertThat(action.getReport().isXlsExportEnabled())
                .isEqualTo(action.isXlsExportEnabled());
        assertThat(action.getReport().isRtfExportEnabled())
                .isEqualTo(action.isRtfExportEnabled());
        assertThat(action.getReport().isTextExportEnabled())
                .isEqualTo(action.isTextExportEnabled());
        assertThat(action.getReport().isExcelExportEnabled())
                .isEqualTo(action.isExcelExportEnabled());
        assertThat(action.getReport().isImageExportEnabled())
                .isEqualTo(action.isImageExportEnabled());
        assertThat(action.getReport().isVirtualizationEnabled())
                .isEqualTo(action.isVirtual());
        assertThat(action.getReport().isHidden())
                .isEqualTo(action.isHidden());
        assertThat(action.getReport().getDataSource()).isNull();
        assertThat(action.getReport().getReportChart()).isNull();

    }

    @Test
    public void ct6_createNewReportWithoutDuplicate_shouldCreateSuccessfully() throws ProviderException {
        String newName = "New name";

        Report report = ReportFixture.aReport();

        ReportProvider reportProvider = mock(ReportProvider.class);
        when(reportProvider.insertReport(any())).thenReturn(report);

        TagProvider tagProvider = mock(TagProvider.class);

        EditReportAction action = new EditReportAction();

        action.setReportProvider(reportProvider);
        action.setTagProvider(tagProvider);

        action.setId(report.getId());
        action.setCommand("add");

        action.setSubmitOk("ok");
        action.setSubmitValidate(null);
        action.setSubmitDuplicate(null);

        action.setName(newName);

        action.setQuery("SELECT * FROM people WHERE name = $P;");
        action.setCsvExportEnabled(true);
        action.setHtmlExportEnabled(false);
        action.setPdfExportEnabled(true);
        action.setXlsExportEnabled(false);
        action.setRtfExportEnabled(false);
        action.setTextExportEnabled(false);
        action.setExcelExportEnabled(false);
        action.setDataSourceId(-1);
        action.setReportChartId(-1);
        action.setDescription("new description");
        action.setFile("valid.jasper");

        String result = action.execute();

        assertThat(result).isEqualTo(SUCCESS);

        assertThat(action.getReport()).isEqualTo(report);
    }

    @Test
    public void ct7_editReport_shouldEditSuccessfully() throws ProviderException {
        Report report = ReportFixture.aReport();
        String newName = "New name";

        ReportProvider reportProvider = mock(ReportProvider.class);
        when(reportProvider.getReport(report.getId())).thenReturn(report);

        TagProvider tagProvider = mock(TagProvider.class);

        EditReportAction action = new EditReportAction();

        action.setReportProvider(reportProvider);
        action.setTagProvider(tagProvider);

        action.setId(report.getId());
        action.setCommand("edit");

        action.setSubmitOk("ok");
        action.setSubmitValidate(null);
        action.setSubmitDuplicate(null);

        action.setName(newName);

        action.setQuery("SELECT * FROM people WHERE name = $P;");
        action.setCsvExportEnabled(true);
        action.setHtmlExportEnabled(false);
        action.setPdfExportEnabled(true);
        action.setXlsExportEnabled(false);
        action.setRtfExportEnabled(false);
        action.setTextExportEnabled(false);
        action.setExcelExportEnabled(false);
        action.setDataSourceId(-1);
        action.setReportChartId(-1);
        action.setDescription("new description");
        action.setFile("valid.jasper");

        String result = action.execute();

        assertThat(result).isEqualTo(SUCCESS);

        assertThat(action.getReport().getParameters()).isEqualTo(report.getParameters());
        assertThat(action.getReport().isDisplayInline()).isEqualTo(report.isDisplayInline());

        assertThat(action.getReport().getName()).isEqualTo(newName);
        assertThat(action.getReport().getDescription()).isEqualTo(action.getDescription());
        assertThat(action.getReport().getFile()).isEqualTo(action.getFile());
        assertThat(action.getReport().getQuery()).isEqualTo(action.getQuery());
        assertThat(action.getReport().isCsvExportEnabled())
                .isEqualTo(action.isCsvExportEnabled());
        assertThat(action.getReport().isHtmlExportEnabled())
                .isEqualTo(action.isHtmlExportEnabled());
        assertThat(action.getReport().isPdfExportEnabled())
                .isEqualTo(action.isPdfExportEnabled());
        assertThat(action.getReport().isXlsExportEnabled())
                .isEqualTo(action.isXlsExportEnabled());
        assertThat(action.getReport().isRtfExportEnabled())
                .isEqualTo(action.isRtfExportEnabled());
        assertThat(action.getReport().isTextExportEnabled())
                .isEqualTo(action.isTextExportEnabled());
        assertThat(action.getReport().isExcelExportEnabled())
                .isEqualTo(action.isExcelExportEnabled());
        assertThat(action.getReport().isImageExportEnabled())
                .isEqualTo(action.isImageExportEnabled());
        assertThat(action.getReport().isVirtualizationEnabled())
                .isEqualTo(action.isVirtual());
        assertThat(action.getReport().isHidden())
                .isEqualTo(action.isHidden());
        assertThat(action.getReport().getDataSource()).isNull();
        assertThat(action.getReport().getReportChart()).isNull();
    }

    @Test
    public void ct8_withSomeErrorDuringTheAction_shouldReturnError() {

        EditReportAction action = new EditReportAction();
        action.setCommand("edit");

        String result = action.execute();

        assertThat(result).isEqualTo(INPUT);

    }

    @Test
    public void ct9_chargeReportWithoutDataSourceAndChartToEdit_shouldRecoveryDataFromRequestedReport() throws ProviderException {
        Report report = ReportFixture.aReport();
        report.setDataSource(null);
        report.setReportChart(null);

        ReportProvider reportProvider = mock(ReportProvider.class);
        when(reportProvider.getReport(report.getId())).thenReturn(report);

        TagProvider tagProvider = mock(TagProvider.class);
        String tags = "MY TAG";
        when(tagProvider.getTagsForObject(report.getId(), Report.class, ORTag.TAG_TYPE_UI))
                .thenReturn(tags);

        EditReportAction action = new EditReportAction();

        action.setReportProvider(reportProvider);
        action.setTagProvider(tagProvider);

        action.setId(report.getId());
        action.setCommand("edit");

        action.setSubmitOk(null);
        action.setSubmitValidate(null);
        action.setSubmitDuplicate(null);

        String result = action.execute();

        assertThat(result).isEqualTo(INPUT);

        assertThat(action.getReport()).isEqualTo(report);
        assertThat(action.getName()).isEqualTo(report.getName());
        assertThat(action.getDescription()).isEqualTo(report.getDescription());
        assertThat(action.getFile()).isEqualTo(report.getFile());
        assertThat(action.getQuery()).isEqualTo(report.getQuery());
        assertThat(action.getId()).isEqualTo(report.getId());
        assertThat(action.isPdfExportEnabled()).isEqualTo(report.isPdfExportEnabled());
        assertThat(action.isCsvExportEnabled()).isEqualTo(report.isCsvExportEnabled());
        assertThat(action.isHtmlExportEnabled()).isEqualTo(report.isHtmlExportEnabled());
        assertThat(action.isXlsExportEnabled()).isEqualTo(report.isXlsExportEnabled());
        assertThat(action.isRtfExportEnabled()).isEqualTo(report.isRtfExportEnabled());
        assertThat(action.isTextExportEnabled()).isEqualTo(report.isTextExportEnabled());
        assertThat(action.isExcelExportEnabled()).isEqualTo(report.isExcelExportEnabled());
        assertThat(action.isImageExportEnabled()).isEqualTo(report.isImageExportEnabled());
        assertThat(action.isVirtual()).isEqualTo(report.isVirtualizationEnabled());
        assertThat(action.isHidden()).isEqualTo(report.isHidden());
        assertThat(action.getDataSourceId()).isEqualTo(0);
        assertThat(action.getReportChartId()).isEqualTo(0);
        assertThat(action.getTags()).isEqualTo(tags);
    }

    @Test
    public void ct10_submitValidateWithoutParameters_shouldValidate() throws ProviderException {
        Report report = ReportFixture.aReport();
        String newName = "New name";

        ReportProvider reportProvider = mock(ReportProvider.class);
        when(reportProvider.getReport(report.getId())).thenReturn(report);

        ReportParameter reportParameter = ReportParameterFixture.aReportParameter();
        ParameterProvider parameterProvider = mock(ParameterProvider.class);
        when(parameterProvider.getReportParameter(anyString()))
                .thenReturn(reportParameter);

        EditReportAction action = new EditReportAction();

        action.setReportProvider(reportProvider);
        action.setParameterProvider(parameterProvider);

        action.setId(report.getId());
        action.setCommand("edit");
        action.setSubmitOk("ok");
        action.setSubmitValidate("ok");
        action.setSubmitDuplicate(null);
        action.setName(newName);
        action.setQuery("SELECT * FROM people;");
        action.setCsvExportEnabled(true);
        action.setHtmlExportEnabled(false);
        action.setPdfExportEnabled(false);
        action.setXlsExportEnabled(false);
        action.setRtfExportEnabled(false);
        action.setTextExportEnabled(false);
        action.setExcelExportEnabled(false);
        action.setDataSourceId(-1);
        action.setReportChartId(-1);
        action.setDescription("new description");
        action.setFile("valid.jasper");

        String result = action.execute();

        assertThat(result).isEqualTo(INPUT);

        assertThat(action.getReport().getParameters()).isEqualTo(report.getParameters());
        assertThat(action.getReport().isDisplayInline()).isEqualTo(report.isDisplayInline());

        assertThat(action.getReport().getName()).isEqualTo(newName);
        assertThat(action.getReport().getDescription()).isEqualTo(action.getDescription());
        assertThat(action.getReport().getFile()).isEqualTo(action.getFile());
        assertThat(action.getReport().getQuery()).isEqualTo(action.getQuery());
        assertThat(action.getReport().isCsvExportEnabled())
                .isEqualTo(action.isCsvExportEnabled());
        assertThat(action.getReport().isHtmlExportEnabled())
                .isEqualTo(action.isHtmlExportEnabled());
        assertThat(action.getReport().isPdfExportEnabled())
                .isEqualTo(action.isPdfExportEnabled());
        assertThat(action.getReport().isXlsExportEnabled())
                .isEqualTo(action.isXlsExportEnabled());
        assertThat(action.getReport().isRtfExportEnabled())
                .isEqualTo(action.isRtfExportEnabled());
        assertThat(action.getReport().isTextExportEnabled())
                .isEqualTo(action.isTextExportEnabled());
        assertThat(action.getReport().isExcelExportEnabled())
                .isEqualTo(action.isExcelExportEnabled());
        assertThat(action.getReport().isImageExportEnabled())
                .isEqualTo(action.isImageExportEnabled());
        assertThat(action.getReport().isVirtualizationEnabled())
                .isEqualTo(action.isVirtual());
        assertThat(action.getReport().isHidden())
                .isEqualTo(action.isHidden());
        assertThat(action.getReport().getDataSource()).isNull();
        assertThat(action.getReport().getReportChart()).isNull();

    }
}
