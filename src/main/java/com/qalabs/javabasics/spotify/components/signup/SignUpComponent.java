package com.qalabs.javabasics.spotify.components.signup;

import com.google.common.base.Strings;
import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpComponent extends SpotifyComponent {

    // Attributes

    private WebDriverWait wait;

    @FindBy(how = How.ID, using = "register-email")
    private WebElement emailInput;

    @FindBy(how = How.ID, using = "register-confirm-email")
    private WebElement confirmEmailInput;

    @FindBy(how = How.ID, using = "register-password")
    private WebElement passInput;

    @FindBy(how = How.ID, using = "register-displayname")
    private WebElement nameInput;

    @FindBy(how = How.ID, using = "register-dob-month")
    private WebElement monthDropdown;

    private Select monthDropdownElement;
    private List<WebElement> monthOptions;

    @FindBy(how = How.ID, using = "register-dob-day")
    private WebElement dayInput;

    @FindBy(how = How.ID, using = "register-dob-year")
    private WebElement yearInput;

    @FindBy(how = How.XPATH, using = "//input[@type = 'radio']")
    private List<WebElement> genderRadioButtons;

    @FindBy(how = How.ID, using = "register-thirdparty")
    private WebElement shareCheckbox;

    @FindBy(how = How.ID, using = "register-button-email-submit")
    private WebElement signUpButton;

    private String emailErrorMessageXPathLocator = "//label[@for = 'register-email' and @class = 'has-error']";
    private String confirmEmailErrorMessageXPathLocator = "//label[@for = 'register-confirm-email' and @class = 'has-error']";
    private String passwordErrorMessageXPathLocator = "//label[@for = 'register-password' and @class = 'has-error']";
    private String nameErrorMessageXPathLocator = "//label[@for = 'register-displayname' and @class = 'has-error']";
    private String dayErrorMessageXPathLocator = "//label[@for = 'register-dob-day' and @class = 'has-error']";
    private String monthErrorMessageXPathLocator = "//label[@for = 'register-dob-month' and @class = 'has-error']";
    private String yearErrorMessageXPathLocator = "//label[@for = 'register-dob-year' and @class = 'has-error']";
    private String genderErrorMessageXPathLocator = "//label[@for = 'signup_form[gender]' and @class = 'has-error']";
    private String captchaErrorMessageXPathLocator = "//label[@for = 'captcha-hidden' and @class = 'has-error']";

    // Constructor

    public SignUpComponent(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
        this.driver = driver;
    }

    // Getters

    public WebElement getEmailInputElement() {
        return this.emailInput;
    }

    public WebElement getNameInputElement() {
        return this.nameInput;
    }

    // Actions

    public void fillSpotifySignUpForm(String email, String pass, String name, String month, String day, String year, String gender) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(confirmEmailInput));
        confirmEmailInput.clear();
        confirmEmailInput.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(passInput));
        passInput.clear();
        passInput.sendKeys(pass);

        wait.until(ExpectedConditions.visibilityOf(nameInput));
        nameInput.clear();
        nameInput.sendKeys(name);

        wait.until(ExpectedConditions.visibilityOf(dayInput));
        dayInput.clear();
        dayInput.sendKeys(day);

        setMonth(month);

        wait.until(ExpectedConditions.visibilityOf(yearInput));
        yearInput.clear();
        yearInput.sendKeys(year);

        setGender(gender);

        wait.until(ExpectedConditions.visibilityOf(shareCheckbox));
        shareCheckbox.click();
    }

    private void setMonth(String month) {
        wait.until(ExpectedConditions.visibilityOf(monthDropdown));
        monthDropdownElement = new Select(monthDropdown);
        monthOptions = monthDropdownElement.getOptions();
        for (WebElement item : monthOptions) {
            if(item.getText().toLowerCase().equals(month.toLowerCase())) {
                monthDropdownElement.selectByVisibleText(month.toLowerCase());
            }
        }
    }

    private void setGender(String gender) {
        wait.until(ExpectedConditions.visibilityOfAllElements(genderRadioButtons));
        for (WebElement item : genderRadioButtons) {
            if(item.getAttribute("value").equals(gender.toLowerCase())) {
                item.click();
            }
        }
    }

    public void clickOnRegistrateButton() {
        wait.until(ExpectedConditions.visibilityOf(signUpButton));
        signUpButton.click();
    }

    public List<String> getSpotifySignUpEmptyFormErrors() {
        List<String> errors = null;

        wait.until(ExpectedConditions.visibilityOf(getEmailErrorMessageElement()));
        if(getEmailErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getEmailErrorMessageElement().getText());
            }};
        }

        wait.until(ExpectedConditions.visibilityOf(getConfirmEmailErrorMessageElement()));
        if(getConfirmEmailErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getConfirmEmailErrorMessageElement().getText());
            }};
        }

        wait.until(ExpectedConditions.visibilityOf(getPasswordErrorMessageElement()));
        if(getPasswordErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getPasswordErrorMessageElement().getText());
            }};
        }

        wait.until(ExpectedConditions.visibilityOf(getNameErrorMessageElement()));
        if(getNameErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getNameErrorMessageElement().getText());
            }};
        }

        wait.until(ExpectedConditions.visibilityOf(getDayErrorMessageElement()));
        if(getDayErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getDayErrorMessageElement().getText());
            }};
        }

        wait.until(ExpectedConditions.visibilityOf(getMonthErrorMessageElement()));
        if(getMonthErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getMonthErrorMessageElement().getText());
            }};
        }

        wait.until(ExpectedConditions.visibilityOf(getYearErrorMessageElement()));
        if(getYearErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getYearErrorMessageElement().getText());
            }};
        }

        wait.until(ExpectedConditions.visibilityOf(getGenderErrorMessageElement()));
        if(getGenderErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getGenderErrorMessageElement().getText());
            }};
        }

        wait.until(ExpectedConditions.visibilityOf(getCaptchaErrorMessageElement()));
        if(getCaptchaErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getCaptchaErrorMessageElement().getText());
            }};
        }

        return errors;
    }

    public List<String> getSpotifySignUpInvalidDayFormErrors() {
        List<String> errors = null;

        if(getEmailErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getEmailErrorMessageElement().getText());       // I added this line in case if email was already registered
            }};
        }

        if(getDayErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getDayErrorMessageElement().getText());
            }};
        }

        if(getCaptchaErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getCaptchaErrorMessageElement().getText());
            }};
        }

        return errors;
    }

    public List<String> getSpotifySignUpValidFormCaptchaError() {
        List<String> errors = null;

        if(getEmailErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getEmailErrorMessageElement().getText());       // I added this line in case if email was already registered
            }};
        }

        if(getCaptchaErrorMessageElement().getText().length() > 0) {
            errors = new ArrayList<String>() {{
                add(getCaptchaErrorMessageElement().getText());
            }};
        }

        return errors;
    }

    // Setting dynamic elements

    private WebElement getEmailErrorMessageElement() {
        return driver.findElement(By.xpath(emailErrorMessageXPathLocator));
    }

    private WebElement getConfirmEmailErrorMessageElement() {
        return driver.findElement(By.xpath(confirmEmailErrorMessageXPathLocator));
    }

    private WebElement getPasswordErrorMessageElement() {
        return driver.findElement(By.xpath(passwordErrorMessageXPathLocator));
    }

    private WebElement getNameErrorMessageElement() {
        return driver.findElement(By.xpath(nameErrorMessageXPathLocator));
    }

    private WebElement getDayErrorMessageElement() {
        return driver.findElement(By.xpath(dayErrorMessageXPathLocator));
    }

    private WebElement getMonthErrorMessageElement() {
        return driver.findElement(By.xpath(monthErrorMessageXPathLocator));
    }

    private WebElement getYearErrorMessageElement() {
        return driver.findElement(By.xpath(yearErrorMessageXPathLocator));
    }

    private WebElement getGenderErrorMessageElement() {
        return driver.findElement(By.xpath(genderErrorMessageXPathLocator));
    }

    private WebElement getCaptchaErrorMessageElement() {
        return driver.findElement(By.xpath(captchaErrorMessageXPathLocator));
    }
}
