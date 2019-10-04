package com.qalabs.javabasics.spotify.pages;

import com.qalabs.javabasics.spotify.components.signup.SignUpComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpotifySignUpPage extends BasePage {

    private WebDriverWait wait;
    private SignUpComponent signUpComponent;

    public final static String BASE_URL = "";

    public SpotifySignUpPage(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
        this.signUpComponent = new SignUpComponent(driver);
    }

    // ToDO: Implement this method
    public SpotifyHomePage clickOnSpotifyIcon() {
        return null;
    }
}
