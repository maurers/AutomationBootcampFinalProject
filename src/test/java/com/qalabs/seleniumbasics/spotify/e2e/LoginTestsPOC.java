package com.qalabs.seleniumbasics.spotify.e2e;

import com.qalabs.javabasics.spotify.pages.SpotifyOverviewUserAccountPage;
import com.qalabs.javabasics.spotify.pages.SpotifyLoginPage;
import com.qalabs.seleniumbasics.spotify.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTestsPOC extends BaseTest {

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
        spotifyLoginPage.fillSpotifyLogInForm("your@awesome.email.com", "YouR4weSomePassW0rd");
        spotifyLoginPage.clickOnLoginButton();

        List<String> listOfErrors = spotifyLoginPage.getAllSpotifyLogInFormErrorMessages();
        Assert.assertTrue(listOfErrors.isEmpty());

        SpotifyOverviewUserAccountPage spotifyOverviewUserAccountPage = new SpotifyOverviewUserAccountPage(this.driver);
        Assert.assertTrue(spotifyOverviewUserAccountPage.isLoaded());

        // ToDO: Close session after login to avoid the other tests fails
    }

    @Test
    public void validateLoginPageFail() {
        this.driver.navigate().to(spotifyURL);

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(this.driver);
        Assert.assertTrue(spotifyLoginPage.isLoaded());
        spotifyLoginPage.fillSpotifyLogInForm("", "");
        spotifyLoginPage.clickOnLoginButton();

        List<String> listOfErrors = spotifyLoginPage.getAllSpotifyLogInFormErrorMessages();
        Assert.assertFalse(listOfErrors.isEmpty());

        SpotifyOverviewUserAccountPage spotifyOverviewUserAccountPage = new SpotifyOverviewUserAccountPage(this.driver);
        Assert.assertFalse(spotifyOverviewUserAccountPage.isLoaded());
    }
}
