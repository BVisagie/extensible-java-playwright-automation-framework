package org.example.websitesundertest.wikipedia.pageobjects;

import lombok.RequiredArgsConstructor;
import org.example.actions.PlaywrightActions;

@RequiredArgsConstructor
public class LanguageSpecificLandingPage {
    public static final String EN_WIKIPEDIA = "https://en.wikipedia.org/wiki/Main_Page";
    private final PlaywrightActions playwrightActions;

    public boolean checkIfUserIsOnLandingPage(String targetLanguage) {

        switch (targetLanguage.toUpperCase()) {
            case "ENGLISH" -> {
                return playwrightActions.getPageSession().url().equals(EN_WIKIPEDIA);
            }
            case "日本語", "РУССКИЙ", "FRANÇAIS", "DEUTSCH", "ESPAÑOL", "ITALIANO" -> {
                return false;
            }
            default -> {
                return false;
            }
        }
    }
}
