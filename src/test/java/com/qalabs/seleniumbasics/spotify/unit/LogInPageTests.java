package com.qalabs.seleniumbasics.spotify.unit;

import com.github.javafaker.Faker;
import com.qalabs.javabasics.spotify.pages.SpotifyLoginPage;
import com.qalabs.seleniumbasics.spotify.BaseTest;
import com.qalabs.seleniumbasics.spotify.utilities.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Log;

import java.util.ArrayList;
import java.util.List;

public class LogInPageTests extends BaseTest {

    private Faker faker = new Faker();

    private List<String> fakeData = new ArrayList<String>() {{
        add(Utils.generateEmail("gmail.com", 4));
        add("Blanca Moreno");
        add(faker.internet().password());
    }};

    @Test
    public void FillFormWithUserValidData_ReturnsListOfErrors() {
        SpotifyLoginPage spotifyLogInPage = new SpotifyLoginPage(this.driver);

        this.driver.navigate().to(spotifyLogInPage.BASE_URL);

        spotifyLogInPage.fillSpotifyLogInForm(fakeData.get(1), fakeData.get(2));
        spotifyLogInPage.clickOnLoginButton();

        List<String> listOfErrors = spotifyLogInPage.getAllSpotifyLogInFormErrorMessages();
        Assert.assertTrue(listOfErrors.size() > 0);
    }

    @Test
    public void FillFormWithEmailValidData_ReturnsListOfErrors() {
        SpotifyLoginPage spotifyLogInPage = new SpotifyLoginPage(this.driver);

        this.driver.navigate().to(spotifyLogInPage.BASE_URL);

        spotifyLogInPage.fillSpotifyLogInForm(fakeData.get(0), fakeData.get(2));
        spotifyLogInPage.clickOnLoginButton();
        spotifyLogInPage.isLoaded();

        List<String> listOfErrors = spotifyLogInPage.getAllSpotifyLogInFormErrorMessages();
        Assert.assertTrue(listOfErrors.size() > 0);
    }

    @Test
    public void SendEmptyFieldValues_ReturnsListOfErrors() {
        SpotifyLoginPage spotifyLogInPage = new SpotifyLoginPage(this.driver);

        this.driver.navigate().to(spotifyLogInPage.BASE_URL);

        spotifyLogInPage.fillSpotifyLogInForm("","");
        spotifyLogInPage.clickOnLoginButton();
        spotifyLogInPage.isLoaded();

        List<String> listOfErrors = spotifyLogInPage.getAllSpotifyLogInFormErrorMessages();
        Assert.assertTrue(listOfErrors.size() > 0);
    }
}

