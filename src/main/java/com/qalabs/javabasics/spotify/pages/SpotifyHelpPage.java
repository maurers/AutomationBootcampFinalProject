package com.qalabs.javabasics.spotify.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

public class SpotifyHelpPage extends BasePage {

    private WebDriverWait wait;

    public final static String BASE_URL = "";

    public SpotifyHelpPage(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
    }

    // ToDO: Implement this method
    public SpotifyHomePage clickOnSpotifyIcon() {
        return null;
    }
}
