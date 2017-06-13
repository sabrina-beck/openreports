package org.efs.openreports.test.actions.admin;

import org.efs.openreports.actions.admin.EditReportAction;
import org.efs.openreports.objects.ORTag;
import org.efs.openreports.objects.Report;
import org.efs.openreports.providers.ProviderException;
import org.efs.openreports.providers.ReportProvider;
import org.efs.openreports.providers.TagProvider;
import org.efs.openreports.test.fixture.ReportFixture;
import org.efs.openreports.util.LocalStrings;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditReportActionTest {

    @Test
    public void ct1_instruction_cover() throws ProviderException {
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

        assertThat(result).isEqualTo("input");
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
    public void ct2_instruction_cover() {
        EditReportAction action = new EditReportAction();
        action.setCommand("add");
        action.setSubmitOk("ok");
        action.setSubmitValidate("ok");
        action.setSubmitDuplicate("ok");

        String result = action.execute();

        assertThat(result).isEqualTo("input");
        assertThat(action.getActionErrors()).hasSize(1)
                .contains(LocalStrings.ERROR_REPORT_INVALID);
    }

    @Test
    public void ct3_instruction_cover() {

    }

}
