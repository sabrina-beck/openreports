package org.efs.openreports.test.actions.admin;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import mondrian.tui.MockHttpServletResponse;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.base.JRBasePrintPage;
import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.ServletActionContext;
import org.displaytag.filter.SimpleServletOutputStream;
import org.efs.openreports.ORStatics;
import org.efs.openreports.actions.ReportViewerAction;
import org.efs.openreports.objects.Report;
import org.efs.openreports.test.fixture.ReportFixture;
import org.junit.Test;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class ReportViewerActionTest {

    @Test
    public void ct1() {
        Report report = ReportFixture.aReport();
        JasperPrint jasperPrint = new JasperPrint();
        jasperPrint.addPage(new JRBasePrintPage());

        HashedMap session = new HashedMap();
        session.put(ORStatics.REPORT, report);
        session.put(ORStatics.JASPERPRINT, jasperPrint);
        ActionContext.getContext().setSession(session);

        ReportViewerAction action = new ReportViewerAction();
        action.setSubmitType("submit");

        String result = action.execute();

        assertThat(result).isEqualTo(Action.SUCCESS);
        assertThat(action.getReport()).isEqualTo(report);
    }

    @Test
    public void ct2() {
        Report report = ReportFixture.aReport();
        HashedMap session = new HashedMap();
        session.put(ORStatics.REPORT, report);
        ActionContext.getContext().setSession(session);

        ReportViewerAction action = new ReportViewerAction();
        action.setSubmitType("submit");

        String result = action.execute();

        assertThat(result).isEqualTo(Action.NONE);
        assertThat(action.getReport()).isEqualTo(report);
        assertThat(action.getPageCount()).isEqualTo(0);
    }


    @Test
    public void ct3() {
        Report report = ReportFixture.aReport();
        JasperPrint jasperPrint = new JasperPrint();

        HashedMap session = new HashedMap();
        session.put(ORStatics.REPORT, report);
        session.put(ORStatics.JASPERPRINT, jasperPrint);
        ActionContext.getContext().setSession(session);

        ReportViewerAction action = new ReportViewerAction();
        action.setSubmitType("image");

        String result = action.execute();

        assertThat(result).isEqualTo(Action.NONE);
        assertThat(action.getReport()).isEqualTo(report);
        assertThat(action.getActionErrors()).hasSize(1);
    }

    @Test
    public void ct4() {
        Report report = ReportFixture.aReport();
        JasperPrint jasperPrint = new JasperPrint();
        jasperPrint.addPage(new JRBasePrintPage());

        HashedMap session = new HashedMap();
        session.put(ORStatics.REPORT, report);
        session.put(ORStatics.JASPERPRINT, jasperPrint);
        ActionContext.getContext().setSession(session);

        ReportViewerAction action = new ReportViewerAction();
        action.setSubmitType("image");

        ServletActionContext.setResponse(new MockHttpServletResponse());

        String result = action.execute();

        assertThat(result).isEqualTo(Action.NONE);
        assertThat(action.getReport()).isEqualTo(report);
        assertThat(ServletActionContext.getResponse().containsHeader("Content-Length")).isTrue();
    }

    @Test
    public void ct5() throws IOException{
        Report report = ReportFixture.aReport();
        JasperPrint jasperPrint = new JasperPrint();
        jasperPrint.addPage(new JRBasePrintPage());

        HashedMap session = new HashedMap();
        session.put(ORStatics.REPORT, report);
        session.put(ORStatics.JASPERPRINT, jasperPrint);
        ActionContext.getContext().setSession(session);

        ServletOutputStream outputStream = spy(new SimpleServletOutputStream());
        doThrow(new IOException()).when(outputStream).write(any());

        MockHttpServletResponse response = mock(MockHttpServletResponse.class);
        when(response.getOutputStream()).thenReturn(outputStream);
        ServletActionContext.setResponse(response);

        ReportViewerAction action = new ReportViewerAction();
        action.setSubmitType("image");

        String result = action.execute();

        assertThat(result).isEqualTo(Action.NONE);
        assertThat(ServletActionContext.getResponse().containsHeader("Content-Length")).isFalse();
    }
}
