package org.efs.openreports.test.model.visualizarrelatorio;

import org.efs.openreports.test.BaseIntegrationTest;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.java.test.TestBuilder;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class VisualizarRelatorioTest extends BaseIntegrationTest {

    private final static Path MODEL_PATH = Paths.get("src/test/resources/org/efs/openreports/test/model/TODO.graphml");

    @Test
    public void runSmokeTest() {
        new TestBuilder()
                .setModel(MODEL_PATH)
                .setContext(new VisualizarRelatorioImpl(driver, softly))
                .setPathGenerator(new AStarPath(new ReachedVertex("v_s12_mensagem_de_arquivo_enviado_com_sucesso")))
                .setStart("e_init")
                .execute();
        softly.assertAll();
    }

    @Test
    public void runFunctionalTest() {
        new TestBuilder()
                .setModel(MODEL_PATH)
                .setContext(new VisualizarRelatorioImpl(driver, softly))
                .setPathGenerator(new RandomPath(new EdgeCoverage(100)))
                .setStart("e_init")
                .execute();
        softly.assertAll();
    }

    @Test
    public void runStabilityTest() {
        new TestBuilder()
                .setModel(MODEL_PATH)
                .setContext(new VisualizarRelatorioImpl(driver, softly))
                .setPathGenerator(new RandomPath(new TimeDuration(120, TimeUnit.SECONDS)))
                .setStart("e_init")
                .execute();
        softly.assertAll();
    }
}
