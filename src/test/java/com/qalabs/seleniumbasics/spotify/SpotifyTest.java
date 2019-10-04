package com.qalabs.seleniumbasics.spotify;

import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;

public class SpotifyTest {

    private String spotifyUrl;
    //Invalid credentials login

    @BeforeTest
    public void initSeptup(){
        PropertyReader propertyReader = new PropertyReader();
        spotifyUrl = propertyReader.getProperty("credentials.properties", "TEST_URL");
    }

    @Test(alwaysRun = true, priority = 2)
    public void invalidLoggerInSpotify(){
        String name;
        String pass;

        PropertyReader propertyReader = new PropertyReader();
        name=propertyReader.getProperty("credentials.properties", "USERNAME");
        pass=propertyReader.getProperty("credentials.properties", "PASSWORD");

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(driver);
        sporifyLoginPage.spotifyLoginPage(name,pass);

        Assert.assertEquals( driver.getSpotifyLogin(), SpotifyLoginPage);
        Assert.assertTrue( false, "bad_credentials" );
    }
    @Test(alwaysRun = true, priority = 0)
    public void registerInSpotify(){
        String name;
        String email;
        String confirmEmail;
        String pass = "";
        String day = "";
        String month="";
        String year="";
        String gender="";


        PropertyReader propertyReader = new PropertyReader();
        name=propertyReader.getProperty("credentials.properties", "USER_NAME");
        email=propertyReader.getProperty("credentials.properties", "EMAIL");
        confirmEmail=propertyReader.getProperty("credentials.proeprties", "EMAIL");
        day=propertyReader.getProperty("credentials.properties", "DAY");
        month=propertyReader.getProperty("credentials.properties","MONTH");
        year=propertyReader.getProperty("credentials.properties", "YEAR_VALID");
        gender=propertyReader.getPorperty("credentials.properties","GENDER");

        SpotifyRegisterPage spotifyRegisterPage = new SpotifyRegisterPage(driver);
        SpotifyRegsiterPage.spotifyRegisterPage(name,email,confirmEmail,pass,day,month,year,gender);

        Assert.assertTrue( false, "Se solicita validacion captcha" );


    }
}
