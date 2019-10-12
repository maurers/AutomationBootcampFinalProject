package com.qalabs.seleniumbasics.spotify.unit;

import com.github.javafaker.Faker;
import com.qalabs.javabasics.spotify.pages.SpotifySignUpPage;
import com.qalabs.seleniumbasics.spotify.BaseTest;
import com.qalabs.seleniumbasics.spotify.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Log;

import java.util.ArrayList;
import java.util.List;

public class SignUpPageTests extends BaseTest {

    Faker faker = new Faker();

    List<String> fakeData = new ArrayList<String>() {{
       add(Utils.generateEmail("gmail.com", 4));
       add(faker.internet().password());
       add("Blanca Moreno");
       add("Mayo");
       add("12");
       add("2000");
       add("female");
    }};

    @Test
    public void FillFormWithValidData_ReturnsListOfErrors() {
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(this.driver);

        this.driver.navigate().to(SpotifySignUpPage.BASE_URL);

        spotifySignUpPage.fillSpotifySignUpForm(
            fakeData.get(0),
            fakeData.get(1),
            fakeData.get(2),
            fakeData.get(3),
            fakeData.get(4),
            fakeData.get(5),
            fakeData.get(6)
        );
        spotifySignUpPage.clickOnRegistrateButton();

        List<String> listOfErrors = spotifySignUpPage.getSpotifySignUpValidFormCaptchaError();
        Log.info("Size of the list: " + listOfErrors.size());
        Assert.assertTrue(listOfErrors.size() > 0);
    }

    @Test
    public void FillFormWithValidDataAndInvalidDayValue_ReturnsListOfErrors() {
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(this.driver);

        this.driver.navigate().to(SpotifySignUpPage.BASE_URL);

        spotifySignUpPage.fillSpotifySignUpForm(
            fakeData.get(0),
            fakeData.get(1),
            fakeData.get(2),
            fakeData.get(3),
            "0",
            fakeData.get(5),
            fakeData.get(6)
        );
        spotifySignUpPage.clickOnRegistrateButton();

        List<String> listOfErrors = spotifySignUpPage.getSpotifySignUpInvalidDayFormErrors();
        Log.info("Size of the list: " + listOfErrors.size());
        Assert.assertTrue(listOfErrors.size() > 0);
    }

    @Test
    public void SendEmptyFieldValues_ReturnsListOfErrors() {
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(this.driver);

        this.driver.navigate().to(SpotifySignUpPage.BASE_URL);

        spotifySignUpPage.clickOnRegistrateButton();

        List<String> listOfErrors = spotifySignUpPage.getSpotifySignUpEmptyFormErrors();
        Log.info("Size of the list: " + listOfErrors.size());
        Assert.assertTrue(listOfErrors.size() > 0);
    }
}
