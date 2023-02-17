package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.RequiredArgsConstructor;
import org.example.actions.PlaywrightActions;

@RequiredArgsConstructor
public class ScenarioHooks {
    private final PlaywrightActions playwrightActions;

    private static boolean isUiTest(Scenario scenario) {
        return scenario.getSourceTagNames().contains("@UI-Test");
    }

    @Before
    public void beforeScenario(Scenario scenario) {

        if (isUiTest(scenario)) {
            playwrightActions.startNewSession();
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (isUiTest(scenario) && playwrightActions.isSessionRunning()) {
            if (scenario.isFailed()) {
                playwrightActions.takeScreenshot(scenario.getName(), scenario.getStatus().toString());
            }
            playwrightActions.stopSession();
        }

        clearState();
    }

    private void clearState() {
        // nothing to do yet...
    }

}
