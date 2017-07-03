package org.efs.openreports.test.actions.admin;

import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.collections.map.HashedMap;
import org.efs.openreports.ORStatics;
import org.efs.openreports.actions.admin.ReportUploadAction;
import org.efs.openreports.objects.*;
import org.efs.openreports.providers.*;
import org.efs.openreports.test.fixture.*;
import org.efs.openreports.util.LocalStrings;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportUploadActionTest {

    @Test
    public void ct1_upload() throws ProviderException {
    	List<ReportTemplate> reportTs = null;

        ReportProvider reportProvider = mock(ReportProvider.class);
        when(reportProvider.getReportTemplates()).thenReturn(reportTs);


        ReportUploadAction action = new ReportUploadAction();
        action.setCommand("upload");
        action.setReportProvider(reportProvider);
        File reportFile = new File("src/test/resources/valid_file.jasper");
		action.setReportFile(reportFile);
        String reportFileFileName="valid_file.jasper";
		action.setReportFileFileName(reportFileFileName);
        String revision="test";
		action.setRevision(revision);
 

        String result = action.execute();

        assertThat(result).isEqualTo(INPUT);

        assertThat(action.getReportTemplates()).isEqualTo(reportTs);
       
    }

   
}