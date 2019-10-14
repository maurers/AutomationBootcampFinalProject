package com.qalabs.seleniumbasics.spotify;

import com.qalabs.javabasics.spotify.pages.SpotifyOverviewUserAccountPage;
import com.qalabs.javabasics.spotify.pages.SpotifyLoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTests extends BaseTest {

    private String spotifyURL;

    @BeforeTest
    public void initSetup() {
        spotifyURL = "https://accounts.spotify.com/es/login/";
    }

    @Test
    public void validateLoginPage() {
        this.driver.navigate().to(spotifyURL);

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(this.driver);
        Assert.assertTrue(spotifyLoginPage.isLoaded());
        spotifyLoginPage.spotifyLoginForm("JesusD84", "xxxx");
        spotifyLoginPage.clickOnLoginButton();

        List<String> listOfErrors = spotifyLoginPage.validateSpotifyLoginForm("JesusD84", "xxx");
        Assert.assertTrue(listOfErrors == null);

        SpotifyOverviewUserAccountPage spotifyOverviewUserAccountPage = new SpotifyOverviewUserAccountPage(this.driver);
        Assert.assertTrue(spotifyOverviewUserAccountPage.isLoaded());
    }

    @Test
    public void validateLoginPageFail() {
        this.driver.navigate().to(spotifyURL);

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(this.driver);
        Assert.assertTrue(spotifyLoginPage.isLoaded());
        spotifyLoginPage.spotifyLoginForm("", "");
        spotifyLoginPage.clickOnLoginButton();

        List<String> listOfErrors = spotifyLoginPage.validateSpotifyLoginForm("", "");
        Assert.assertTrue(!listOfErrors.isEmpty());

        SpotifyOverviewUserAccountPage spotifyOverviewUserAccountPage = new SpotifyOverviewUserAccountPage(this.driver);
        Assert.assertFalse(spotifyOverviewUserAccountPage.isLoaded());
    }
}
