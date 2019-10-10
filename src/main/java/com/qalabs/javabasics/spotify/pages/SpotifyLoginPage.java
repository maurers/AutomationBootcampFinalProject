package com.qalabs.javabasics.spotify.pages;

import com.qalabs.javabasics.spotify.components.login.LoginComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpotifyLoginPage extends BasePage {

    public static final String LOGIN_URL = ;
    private WebDriverWait wait;
    private LoginComponent loginComponent;

    public final static String BASE_URL = "";

    public SpotifyLoginPage(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
        this.loginComponent = new LoginComponent(driver);
    }

    public static void spotifyLoginForm(String name, String pass) {
    }

    // ToDO: Implement this method
    public SpotifyHomePage clickOnSpotifyIcon() {
        return null;
    }
}
