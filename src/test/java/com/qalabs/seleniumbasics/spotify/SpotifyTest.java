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

    @Test(description = "TC_US_02_001/Acceder al formulario de Inicio de Sesión de Spotify", alwaysRun = true, priority = 2)
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
    @Test(description = "TC_US3_002/Campos de registro de obtén spotify gratis se envían llenos y sin validar captcha", alwaysRun = true, priority = 0)
    public void spotifySignUpPage(){
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

        SpotifySignUpPage spotifySingUpPage = new SpotifySingUpPage(driver);
        SpotifySingUpPage.spotifySignUpPage(name,email,confirmEmail,pass,day,month,year,gender);

        Assert.assertFalse( true, "Se solicita validacion captcha" );


    }
}
