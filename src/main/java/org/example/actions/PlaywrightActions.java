package org.example.actions;

import com.microsoft.playwright.*;
import lombok.Getter;
import org.example.configurations.UiConfig;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

@Component
public class PlaywrightActions {
    private static final double SLOW_MO_AMOUNT = 50;
    private static final String PATTERN_FORMAT = "yyyyMMdd-HHmmss";
    private final UiConfig uiConfig;
    private boolean headlessBrowser;
    private Playwright playwrightSession;
    private Browser browserSession;
    private BrowserContext browserContext;
    /**
     * the active pageSession
     */
    @Getter
    private Page pageSession;

    public PlaywrightActions(UiConfig uiConfig) {
        this.uiConfig = uiConfig;
        this.playwrightSession = null;
        this.browserSession = null;
        this.browserContext = null;
    }

    private static String createFilename(String scenarioName, String scenarioStatus) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.systemDefault());
        var date = formatter.format(Instant.now());
        return String.format("%s-%s-%s", date, scenarioStatus, scenarioName.replace(" ", "-"));
    }

    /**
     * This method is used to reset UiActions, so that a new test can be started. It removes the browserContext and
     * stops the playwright-, browser- and pageSessions.
     */
    public void stopSession() {
        playwrightSession = null;
        browserSession = null;
        browserContext = null;
        pageSession = null;
    }

    /**
     * This method returns true if a session was created and true if the sessions are set to null
     */
    public boolean isSessionRunning() {
        return pageSession != null;
    }

    /**
     * Starts the required sessions for playwright and spins up the configured browser
     */
    public void startNewSession() {

        switch (uiConfig.getBrowser()) {
            case CHROME -> startPlayWrightSessionWithBrowser(() -> playwrightSession.chromium());
            case FIREFOX -> startPlayWrightSessionWithBrowser(() -> playwrightSession.firefox());
            case WEBKIT -> startPlayWrightSessionWithBrowser(() -> playwrightSession.webkit());
            default -> throw new IllegalArgumentException("Browser type not configured in UiActions");
        }

        setHeadlessBrowserMode();
    }

    private void startPlayWrightSessionWithBrowser(Supplier<BrowserType> browserTypeSupplier) {
        startNewPlaywrightSession();
        startBrowserSession(browserTypeSupplier);
        createNewIncognitoBrowserTab();
    }

    /**
     * Navigates to the given url on the browser Throws an IllegalStateException if the pageSession was not started
     */
    public void navigateToWebsiteUnderTest() {
        if (pageSession == null) {
            throw new IllegalStateException("UI session did not start-up correctly");
        }
        if (uiConfig.getCurrentWebSiteUnderTest() == null) {
            throw new IllegalStateException("UI session did not start-up correctly");
        }
        pageSession.navigate(uiConfig.getCurrentWebSiteUnderTest().getBaseUrl());
    }

    private void startNewPlaywrightSession() {
        playwrightSession = Playwright.create();
        // https://playwright.dev/java/docs/next/locators#locate-by-test-id
        playwrightSession.selectors().setTestIdAttribute(uiConfig.getTestIdLocatorFormat());
    }

    private void startBrowserSession(final Supplier<BrowserType> browserTypeSupplier) {

        // By default, Playwright is also headless
        if (headlessBrowser) {
            browserSession = browserTypeSupplier.get().launch();
        } else {
            browserSession = browserTypeSupplier.get()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(SLOW_MO_AMOUNT));
        }
    }

    // https://playwright.dev/java/docs/api/class-browser#browser-new-context
    private void createNewIncognitoBrowserTab() {
        // Create a new incognito browser context.
        browserContext = browserSession.newContext(new Browser.NewContextOptions()
                .setViewportSize(uiConfig.getWidth(), uiConfig.getHeight()));
        // Create a new page in a pristine context.
        pageSession = browserContext.newPage();
    }

    /**
     * Takes a screenshot of the browser and stores this under the configured directory
     *
     * @param scenarioName   name of the scenario that was run
     * @param scenarioStatus the status of the scenario
     */
    public void takeScreenshot(String scenarioName, String scenarioStatus) {
        try {
            String filename = createFilename(scenarioName, scenarioStatus);
            var path = Paths.get(String.format("%s/%s.png", uiConfig.getScreenshotDirectory(), filename));

            pageSession.screenshot(new Page.ScreenshotOptions().setPath(path));
        } catch (Exception e) {
            Logger.error("Unable to take screenshot. Exception: " + e);
        }
    }

    private void setHeadlessBrowserMode() {
        headlessBrowser = uiConfig.getHeadlessBrowser();
    }
}
