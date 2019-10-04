package com.qalabs.seleniumbasics.spotify;

import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SpotifyTest  extends BaseTest {
    private String spotifyUrl;

    @BeforeTest
    public void initSetup(){
        com.qalabs.seleniumbasics.utils.PropertyReader propertyReader= new com.qalabs.seleniumbasics.utils.PropertyReader();
        spotifyUrl= propertyReader.getProperty("credentials.properties", "SPOTIFY_URL");
    }
    @Test(alwaysRun = true, priority = 0)
    public void validateLoginPage() {

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(this.myDriver);

        myDriver.navigate().to(spotifyUrl);
        //Assert.assertTrue(facebookLoginPage.isLoaded(), "Google results page is not loaded");
        Assert.assertEquals(myDriver.getCurrentUrl(), SpotifyLoginPage.BASE_URL);
    }
    @Test(alwaysRun = true, priority = 1)
    public void loggerInSpotify() {
        String name;
        String pass;

        com.qalabs.seleniumbasics.utils.PropertyReader propertyReader= new PropertyReader();
        name= propertyReader.getProperty("credentials.properties", "USSER_NAME");
        pass=propertyReader.getProperty("credentials.properties", "PASSWORD");

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(myDriver);
        SpotifyLoginPage.spotifyLoginForm(name,pass);
    }



}
