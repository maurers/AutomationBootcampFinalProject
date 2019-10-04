package com.qalabs.seleniumbasics.spotify;

import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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

        spotifyLoginForm spotifyLoginForm = new SpotifyLoginPage(driver);
        spotifyLoginForm.spotifyLoginForm(name,pass);

        Assert.assertEquals( driver.getSpotifyLogin(), SpotifyLoginPage);
        Assert.assertTrue( false, "bad_credentials" );
    }
    @Test(description = "TC_US3_002/Campos de registro de obtén spotify gratis se envían llenos y sin validar captcha", alwaysRun = true, priority = 0)
    public void spotifySignUp(){
        String email;
        String confirmEmail;
        String pass = "";
        String name;
        String day = "";
        String month="";
        String year="";
        String gender="";


        PropertyReader propertyReader = new PropertyReader();
        email=propertyReader.getProperty("credentials.properties", "EMAIL");
        confirmEmail=propertyReader.getProperty("credentials.properties", "EMAIL");
        pass=propertyReader.getProperty("credentials.properties", "PASSWORD");
        name=propertyReader.getProperty("credentials.properties","NAME");
        day=propertyReader.getProperty("credentials.properties", "DAY");
        month=propertyReader.getProperty("credentials.properties","MONTH");
        year=propertyReader.getProperty("credentials.properties", "YEAR_VALID");
        gender=propertyReader.getPorperty("credentials.properties","GENDER");

        spotifySignUpForm spotifySingUpForm = new SpotifySingUpPage(driver);
        spotifySingUpForm.spotifySignUpForm(email,confirmEmail,pass,name,day,month,year,gender);

        Assert.assertFalse( true, "Se solicita validacion captcha" );


    }
}
