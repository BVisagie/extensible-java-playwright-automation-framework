package org.example.websitesundertest.wikipedia.pageobjects;

import lombok.RequiredArgsConstructor;
import org.example.actions.PlaywrightActions;
import org.example.configurations.UiConfig;

@RequiredArgsConstructor
public class MainLandingPage {
    public static final String EN_LANGUAGE_TITLE = "English — Wikipedia — The Free Encyclopedia";
    private final PlaywrightActions playwrightActions;
    private final UiConfig uiConfig;

    public boolean checkIfUserIsOnLandingPage() {
        return playwrightActions.getPageSession().url().equals(uiConfig.getCurrentWebSiteUnderTest().getBaseUrl());
    }

    public void navigateToWikiByLanguage(String targetLanguage) {

        switch (targetLanguage.toUpperCase()) {
            case "ENGLISH" -> {
                playwrightActions.getPageSession().getByTitle(EN_LANGUAGE_TITLE).click();
            }
            case "日本語", "РУССКИЙ", "FRANÇAIS", "DEUTSCH", "ESPAÑOL", "ITALIANO" -> {
            }
            default -> {
            }
        }
    }
}
