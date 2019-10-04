package com.qalabs.seleniumbasics.spotify;

import com.qalabs.javabasics.spotify.page.SpotifySignUpPage;
import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SpotifyTest  extends BaseTest {
    private String spotifyUrl;
    private String spotifySignUpUrl;

    @BeforeTest
    public void initSetup(){
        com.qalabs.seleniumbasics.utils.PropertyReader propertyReader= new com.qalabs.seleniumbasics.utils.PropertyReader();
        spotifyUrl= propertyReader.getProperty("credentials.properties", "SPOTIFY_URL");
        spotifyUrl= propertyReader.getProperty("credentials.properties", "SPOTIFY_SIGNUP_URL");
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
    @Test(alwaysRun = true, priority = 2)
    public void validateLoginPage() {

        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(this.myDriver);

        myDriver.navigate().to(spotifySignUpUrl);
        //Assert.assertTrue(facebookLoginPage.isLoaded(), "Google results page is not loaded");
        Assert.assertEquals(myDriver.getCurrentUrl(), SpotifySignUpPage.SPOTIFY_SIGNUP_URL);
    }
    @Test(alwaysRun = true, priority = 3)
    public void loggerInSpotifySignUp() {
        String name= null;
        String pass = null;
        String email=null;
        String day= null;
        String year= null;
        String month= null;
        String gender= null;
        com.qalabs.seleniumbasics.utils.PropertyReader propertyReader= new PropertyReader();
        name= propertyReader.getProperty("credentials.properties", "USSER_NAME");
        email=propertyReader.getProperty("credentials.properties", "EMAIL");
        day=propertyReader.getProperty("credentials.properties", "DAY");
        month=propertyReader.getProperty("credentials.properties", "MONTH");
        year=propertyReader.getProperty("credentials.properties", "YEAR");
        gender=propertyReader.getProperty("credentials.properties", "GENDER");


        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(myDriver);
        spotifySignUpPage.spotifySignUpform(name,pass,email,day,month,year,gender);
    }


}
