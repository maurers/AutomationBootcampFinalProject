package com.qalabs.javabasics.spotify.components.signup;

import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

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

    private String allErrorMessagesXPathLocator = "//label[contains(@class, 'has-error')]";

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

    public List<String> getAllSpotifySignUpFormErrorMessages() {
        List<String> errors = new ArrayList<String>();
        List<WebElement> allErrorMessagesElementsList = getAllErrorMessagesElements();

        if(getAllErrorMessagesElements().size() > 0) {
            for (WebElement item : getAllErrorMessagesElements()) {
                if(item.getText().length() > 0) {
                    errors.add(item.getText());
                }
            }
        }

        return errors;
    }

    // Setting dynamic elements

    private List<WebElement> getAllErrorMessagesElements() {
        List<WebElement> list = new ArrayList<WebElement>();

        try {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(allErrorMessagesXPathLocator), 0));
            return driver.findElements(By.xpath(allErrorMessagesXPathLocator));
        } catch (Exception ex) {
            return list;
        }
    }
}
