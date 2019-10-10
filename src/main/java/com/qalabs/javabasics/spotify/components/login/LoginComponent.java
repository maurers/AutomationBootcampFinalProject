package com.qalabs.javabasics.spotify.components.login;

import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import com.qalabs.javabasics.spotify.pages.SpotifyLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginComponent extends SpotifyComponent {

    //Attributes

    @FindBy(how = How.ID, using = "login-username")
    private WebElement userInput;

    @FindBy(how = How.ID, using = "login-password")
    private WebElement passInput;

    @FindBy(how = How.ID, using = "login-button")
    private WebElement loginButton;

    //Constructor

    public LoginComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //Getters

    public WebElement getUserInput () { return this.userInput; }
    public WebElement getPassInput () { return this.passInput; }
    public WebElement getLoginButton () { return this.loginButton; }

    //Actions

    public SpotifyLoginPage loginAccount(String user, String pass) {
        userInput.clear();
        passInput.clear();
        userInput.sendKeys(user);
        passInput.sendKeys(pass);
        loginButton.click();
        return new SpotifyLoginPage(this.driver);
    }
}
