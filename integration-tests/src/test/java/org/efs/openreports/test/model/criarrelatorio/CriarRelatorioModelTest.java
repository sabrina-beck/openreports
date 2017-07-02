package org.efs.openreports.test.model.criarrelatorio;

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

public class CriarRelatorioModelTest extends BaseIntegrationTest {

    private final static Path MODEL_PATH = Paths.get("src/test/resources/org/efs/openreports/test/model/CriarRelatorio.graphml");

    @Test
    public void runSmokeTest() {
        new TestBuilder()
                .setModel(MODEL_PATH)
                .setContext(new CriarRelatorioImpl(driver, softly))
                .setPathGenerator(new AStarPath(new ReachedVertex("v_s10_exibe_todos_os_relatorios")))
                .setStart("e_init")
                .execute();
        softly.assertAll();
    }

    @Test
    public void runFunctionalTest() {
        new TestBuilder()
                .setModel(MODEL_PATH)
                .setContext(new CriarRelatorioImpl(driver, softly))
                .setPathGenerator(new RandomPath(new EdgeCoverage(100)))
                .setStart("e_init")
                .execute();
        softly.assertAll();
    }

    @Test
    public void runStabilityTest() {
        new TestBuilder()
                .setModel(MODEL_PATH)
                .setContext(new CriarRelatorioImpl(driver, softly))
                .setPathGenerator(new RandomPath(new TimeDuration(120, TimeUnit.SECONDS)))
                .setStart("e_init")
                .execute();
        softly.assertAll();
    }

}
