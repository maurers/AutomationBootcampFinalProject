package com.qalabs.seleniumbasics.spotify.e2e;

import com.qalabs.javabasics.spotify.pages.SpotifyHomePage;
import com.qalabs.javabasics.spotify.pages.SpotifySignUpPage;
import com.qalabs.seleniumbasics.spotify.BaseTest;
import com.qalabs.seleniumbasics.spotify.utilities.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Log;

import java.util.List;

public class SignUpPageTest extends BaseTest{
    private String spotifyUrl;

    @BeforeMethod
    public void initSetup(){
        PropertyReader propertyReader= new PropertyReader();
        spotifyUrl= propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        driver.navigate().to(spotifyUrl);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(driver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        spotifyHomePage.goToSignUpPage();
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(driver);
        Assert.assertTrue( spotifySignUpPage.isLoaded(), "Página de Login no se cargo correctamente" );
    }

    @Test(alwaysRun = true,description = "TC_US4_001/ Llenar el formulario para crear cuenta nueva")
    public void spotifySignNoGender(){
        PropertyReader propertyReader = new PropertyReader();
        String email=propertyReader.getProperty("credentials.properties", "TC_US_04_001_EMAIL");
        String pass=propertyReader.getProperty("credentials.properties", "TC_US_04_001_PASSWORD");
        String name=propertyReader.getProperty("credentials.properties","TC_US_04_001_NAME");
        String day=propertyReader.getProperty("credentials.properties", "TC_US_04_001_DAY");
        String month=propertyReader.getProperty("credentials.properties","TC_US_04_001_MONTH");
        String year=propertyReader.getProperty("credentials.properties", "TC_US_04_001_YEAR");


        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(driver);
        spotifySignUpPage.fillSpotifySignUpForm(email,pass,name,month,day,year,"");
        spotifySignUpPage.clickOnRegistrateButton();
        List<String> listOfErrors = spotifySignUpPage.getAllSpotifySignUpFormErrorMessages();

        Assert.assertTrue(listOfErrors.get(0).contains("Por favor indica tu género."), listOfErrors.get(0) + "Mensaje de Error Diferente al de minimo de años");
    }

    @Test(description = "TC_US4_002 / Formulario de Crear cuenta nueva no se envia por dia de nacimiento invalido")
    public void spotifySignUpWrongBirthDate(){
        PropertyReader propertyReader = new PropertyReader();
        String email=propertyReader.getProperty("credentials.properties", "TC_US_04_002_EMAIL");
        String pass=propertyReader.getProperty("credentials.properties", "TC_US_04_002_PASSWORD");
        String name=propertyReader.getProperty("credentials.properties","TC_US_04_002_NAME");
        String day=propertyReader.getProperty("credentials.properties", "TC_US_04_002_DAY");
        String month=propertyReader.getProperty("credentials.properties","TC_US_04_002_MONTH");
        String year=propertyReader.getProperty("credentials.properties", "TC_US_04_002_YEAR");
        String gender=propertyReader.getProperty("credentials.properties","TC_US_04_002_GENDER");

        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(driver);
        spotifySignUpPage.fillSpotifySignUpForm(email,pass,name,month,day,year,gender);
        spotifySignUpPage.clickOnRegistrateButton();
        List<String> listOfErrors = spotifySignUpPage.getAllSpotifySignUpFormErrorMessages();
        Assert.assertTrue(listOfErrors.get(0).contains("Por favor introduce un día válido del mes."), "Mensaje de Error Diferente al de minimo de años");
    }
}