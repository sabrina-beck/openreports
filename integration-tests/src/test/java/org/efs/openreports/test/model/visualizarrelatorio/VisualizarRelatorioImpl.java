package org.efs.openreports.test.model.visualizarrelatorio;

import org.assertj.core.api.SoftAssertions;
import org.efs.openreports.test.pages.*;
import org.efs.openreports.test.pages.elements.ReportFile;
import org.efs.openreports.test.pages.subpage.MenuSubPage;
import org.graphwalker.core.machine.ExecutionContext;
import org.openqa.selenium.WebDriver;

public class VisualizarRelatorioImpl extends ExecutionContext {

    private final WebDriver driver;
    private final SoftAssertions softly;

    public VisualizarRelatorioImpl(WebDriver driver, SoftAssertions softly) {
        this.driver = driver;
        this.softly = softly;
    }
}
