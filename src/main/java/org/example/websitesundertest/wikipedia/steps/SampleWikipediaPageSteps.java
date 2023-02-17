package org.example.websitesundertest.wikipedia.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.actions.PlaywrightActions;
import org.example.configurations.UiConfig;
import org.example.websitesundertest.wikipedia.pageobjects.LanguageSpecificLandingPage;
import org.example.websitesundertest.wikipedia.pageobjects.MainLandingPage;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleWikipediaPageSteps {

    private final UiConfig uiConfig;
    private final PlaywrightActions playwrightActions;
    private final MainLandingPage mainLandingPage;
    private final LanguageSpecificLandingPage languageSpecificLandingPage;

    public SampleWikipediaPageSteps(UiConfig uiConfig, PlaywrightActions playwrightActions) {
        this.uiConfig = uiConfig;
        this.playwrightActions = playwrightActions;
        this.mainLandingPage = new MainLandingPage(playwrightActions, uiConfig);
        this.languageSpecificLandingPage = new LanguageSpecificLandingPage(playwrightActions);
    }

    @Given("a user is on the landing page of {string}")
    public void aUserIsOnTheLandingPageOf(final String websiteUnderTest) {
        uiConfig.setCurrentWebsiteUnderTest(websiteUnderTest);
        playwrightActions.navigateToWebsiteUnderTest();
        assertThat(mainLandingPage.checkIfUserIsOnLandingPage()).isTrue();
    }

    @When("the {string} language link is selected")
    public void theLanguageLinkIsSelected(final String languageToSelect) {
        mainLandingPage.navigateToWikiByLanguage(languageToSelect);
    }

    @Then("the user will be presented with the {string} language landing page of Wikipedia")
    public void theUserWillBePresentedWithTheLanguageLandingPage(final String languageToSelect) {
        assertThat(languageSpecificLandingPage.checkIfUserIsOnLandingPage(languageToSelect)).isTrue();
    }
}
