package com.qalabs.javabasics.spotify.components.login;

import com.google.common.base.Strings;
import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import com.qalabs.javabasics.spotify.pages.SpotifyLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class LoginComponent extends SpotifyComponent {

    //Attributes

    @FindBy(how = How.ID, using = "login-username")
    private WebElement userInput;

    @FindBy(how = How.ID, using = "login-password")
    private WebElement passInput;

    @FindBy(how = How.ID, using = "login-button")
    private WebElement loginButton;

    private String emailErrorMessageXPathLocator = "//label[@class='control-label-validation ng-binding ng-scope' and @for='login-username']";
    private String passwordErrorMessageXPathLocator = "//label[@class='control-label-validation ng-binding ng-scope' and @for='login-password']";
    private String userAndPassErrorMessageXPathLocator = "//span[@class='ng-binding ng-scope']";

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

        return new SpotifyLoginPage(this.driver);
    }

    public void clickOnLoginButton() { loginButton.click(); }

    public List<String> validateSpotifyLoginForm(String email, String pass) {
        List<String> errors = null;

        if(Strings.isNullOrEmpty(email) && Strings.isNullOrEmpty(pass)) {
            errors = new ArrayList<String>() {{
                add(getEmailErrorMessageElement().getText());
                add(getPasswordErrorMessageElement().getText());
                add(getUserAndPassErrorMessageXPathLocator().getText());
            }};
        }

        return errors;
    }

    //Setting dynamic elements

    private WebElement getEmailErrorMessageElement() {
        return driver.findElement(By.xpath(emailErrorMessageXPathLocator));
    }

    private WebElement getPasswordErrorMessageElement() {
        return driver.findElement(By.xpath(passwordErrorMessageXPathLocator));
    }

    private WebElement getUserAndPassErrorMessageXPathLocator() {
        return driver.findElement(By.xpath(userAndPassErrorMessageXPathLocator));
    }

}
