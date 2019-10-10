package com.qalabs.javabasics.spotify.components.home;

import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import com.qalabs.javabasics.spotify.pages.SpotifyHelpPage;
import com.qalabs.javabasics.spotify.pages.SpotifyLoginPage;
import com.qalabs.javabasics.spotify.pages.SpotifySignUpPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomeHeaderComponent extends SpotifyComponent {

    // Attributes

    @FindBy(how = How.LINK_TEXT, using = "Inicio de sesión")
    private WebElement loginButton;

    @FindBy(how = How.LINK_TEXT, using = "Regístrate")
    private WebElement signUpButton;

    @FindBy(how = How.LINK_TEXT, using = "Ayuda")
    private WebElement helpButton;

    // Constructor

    public HomeHeaderComponent(WebDriver driver) {
        super(driver);

        this.driver = driver;
    }

    // Getters

    public WebElement getLoginButtonElement() {
        return this.loginButton;
    }

    public WebElement getSignUpButtonElement() {
        return this.signUpButton;
    }

    public WebElement getHelpButtonElement() {
        return this.helpButton;
    }

    // Actions

    public SpotifyLoginPage clickOnLoginButton() {
        this.loginButton.click();

        return new SpotifyLoginPage(this.driver);
    }

    public SpotifySignUpPage clickOnSignUpButton() {
        this.signUpButton.click();

        return new SpotifySignUpPage(this.driver);
    }

    public SpotifyHelpPage clickOnHelpButton() {
        this.helpButton.click();

        return new SpotifyHelpPage(this.driver);
    }
}