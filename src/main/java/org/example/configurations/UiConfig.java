package org.example.configurations;

import lombok.Getter;
import lombok.Setter;
import org.example.configurations.enums.BrowserTypes;
import org.example.configurations.models.WebsiteUnderTest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "org.example.ui-automation")
@Setter
@Getter
public class UiConfig {
    private BrowserTypes browser;
    private String screenshotDirectory;
    private Boolean headlessBrowser;
    private Map<String, WebsiteUnderTest> websitesUnderTest;
    private WebsiteUnderTest currentWebSiteUnderTest;
    private Viewport viewport;
    private String testIdLocatorFormat;

    public void setCurrentWebsiteUnderTest(String websiteUnderTest) {
        for (var entry : websitesUnderTest.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(websiteUnderTest)) {
                currentWebSiteUnderTest = entry.getValue();
            }
        }
    }

    public Integer getWidth() {
        return viewport.getWidth();
    }

    public Integer getHeight() {
        return viewport.getHeight();
    }

    @Getter
    @Setter
    static class Viewport {
        private Integer width;
        private Integer height;
    }
}
