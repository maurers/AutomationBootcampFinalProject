package com.qalabs.javabasics.spotify.pages;

import com.qalabs.javabasics.spotify.components.login.LoginComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SpotifyLoginPage extends BasePage {
    //Attributes

    private WebDriverWait wait;
    private LoginComponent loginComponent;
    public final static String BASE_URL = "https://accounts.spotify.com/es/login/";

    // Elements

    @FindBy(how = How.CLASS_NAME, using = "spotify-logo")
    private WebElement spotifyLogoElement;

    //Constructor

    public SpotifyLoginPage(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
        this.loginComponent = new LoginComponent(driver);
    }

    //Actions

    public SpotifyLoginPage spotifyLoginForm (String user, String pass) {
        return this.loginComponent.loginAccount(user,pass);
    }

    // ToDO: Implement this method
    public SpotifyHomePage clickOnSpotifyIcon() {
        this.spotifyLogoElement.click();
        return new SpotifyHomePage(this.driver);
    }

    public void clickOnLoginButton() { this.loginComponent.clickOnLoginButton(); }

    public List<String> validateSpotifyLoginForm(String email, String pass) {
        return this.loginComponent.validateSpotifyLoginForm(email,pass);
    }

    @Override
    public boolean isLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginComponent.getLoginButton()));
            return true;
        } catch (RuntimeException exception) {
            return false;
        }
    }
}

