package com.qalabs.javabasics.spotify.pages;

import com.qalabs.javabasics.spotify.components.home.HomeBodyComponent;
import com.qalabs.javabasics.spotify.components.home.HomeHeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpotifyHomePage extends BasePage {

    private WebDriverWait wait;
    private HomeHeaderComponent homeHeaderComponent;
    private HomeBodyComponent homeBodyComponent;

    public final static String BASE_URL = "";

    public SpotifyHomePage(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
        this.homeHeaderComponent = new HomeHeaderComponent(driver);
        this.homeBodyComponent = new HomeBodyComponent(driver);
    }

    // ToDO: Implement this method
    public SpotifyHomePage clickOnSpotifyIcon() {
        return null;
    }
}
