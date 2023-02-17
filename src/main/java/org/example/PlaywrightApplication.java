package org.example;

import java.util.ArrayList;
import java.util.List;

public class PlaywrightApplication {
    public static void main(String[] args) {
        ArrayList<String> arguments = new ArrayList<>(
                List.of(
                        "-g", "org.example.playwright.websitesundertest.luma.steps",
                        "-g", "org.example.playwright.configurations",
                        "classpath:features"));
        io.cucumber.core.cli.Main.main(arguments.toArray(String[]::new));
    }
}