package com.qalabs.javabasics.spotify.components.login;

import com.google.common.base.Strings;
import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import com.qalabs.javabasics.spotify.pages.SpotifyLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class LoginComponent extends SpotifyComponent {

    //Attributes
    private WebDriverWait wait;

    @FindBy(how = How.ID, using = "login-username")
    private WebElement userInput;

    @FindBy(how = How.ID, using = "login-password")
    private WebElement passInput;

    @FindBy(how = How.ID, using = "login-button")
    private WebElement loginButton;

    private String allErrorMessagesXPathLocator = "label[class*='ng-binding ng-scope']";
    private String yellowErrorMessageXPathLocator = "p > span[class *= 'ng-binding ng-scope']";

    //Constructor

    public LoginComponent(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 5);
        this.driver = driver;
    }

    //Getters

    public WebElement getUserInput () { return this.userInput; }
    public WebElement getPassInput () { return this.passInput; }
    public WebElement getLoginButton () { return this.loginButton; }

    //Actions

    public void fillSpotifyLogInForm(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOf(userInput));
        userInput.clear();
        userInput.sendKeys(user);

        wait.until(ExpectedConditions.visibilityOf(passInput));
        passInput.clear();
        passInput.sendKeys(pass);
    }

    public void clickOnLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
    }

    public List<String> getAllSpotifyLogInFormErrorMessages() {
        List<String> errors = new ArrayList<String>();
        List<WebElement> errorMessagesListElements = getAllErrorMessagesElements();
        List<WebElement> yellowErrorMessagesListElements = getYellowErrorMessagesElement();

        if(errorMessagesListElements.size() > 0) {
            for (WebElement item : errorMessagesListElements) {
                if(item.getText().length() > 0) {
                    errors.add(item.getText());
                }
            }
        }

        if(yellowErrorMessagesListElements.size() > 0){
            for (WebElement item : yellowErrorMessagesListElements) {
                if(item.getText().length() > 0) {
                    errors.add(item.getText());
                }
            }
        }

        return errors;
    }

    //Setting dynamic elements
    private List<WebElement> getAllErrorMessagesElements() {
        List<WebElement> list = new ArrayList<WebElement>();

        try {
            return wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(allErrorMessagesXPathLocator))));
        } catch (Exception ex) {
            return list;
        }
    }

    private List<WebElement> getYellowErrorMessagesElement() {
        List<WebElement> list = new ArrayList<WebElement>();

        try {
            return wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(yellowErrorMessageXPathLocator))));
        } catch(Exception ex) {
            return list;
        }
    }
}
