package com.qalabs.javabasics.spotify.components.home;

import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import com.qalabs.javabasics.spotify.pages.SpotifySignUpPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomeBodyComponent extends SpotifyComponent {

    // Attributes

    @FindBy(how = How.XPATH, using = "//a/text()[contains(translate(., 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLOMNOPQRSTUVWXYZ'), 'OBTÉN')]" )
    private WebElement getSpotifyButton;

    // Constructor

    public HomeBodyComponent(WebDriver driver) {
        super(driver);

        this.driver = driver;
    }

    // Getters

    public WebElement getGetSpotifyButton() {
        return this.getSpotifyButton;
    }

    // Actions

    public SpotifySignUpPage clickOnGetSpotifyButton() {
        this.getSpotifyButton.click();

        return new SpotifySignUpPage(this.driver);
    }
}
