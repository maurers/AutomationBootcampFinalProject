package com.qalabs.seleniumbasics.spotify;
import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpotifyTest extends BaseTest {

    @Test(description = "TC_US1_002 / Ir a la pagina principal de Spotify con URL incorrecta")
    public void goToHomePageFail() {
        PropertyReader propertyReader = new PropertyReader();
        String page = propertyReader.getProperty("credentials.properties", "SPOTIFY_PAGE_FAIL");

        this.myDriver.navigate().to(page);

        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, page, "Cargo una pagina diferente");
    }

    @Test(description = "TC_US3_001 / Llenar campos de registros de obtén Spotify gratis se envían vacíos")
    public void freeSpotifyEmptyFields() {
        PropertyReader propertyReader = new PropertyReader();
        String homePage = propertyReader.getProperty("credentials.properties", "SPOTIFY_PAGE");
        String signUpPage = propertyReader.getProperty("credentials.properties", "SPOTIFY_SIGNUP_PAGE");

        this.myDriver.navigate().to(homePage);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, homePage, "Cargo una pagina diferente");

        spotifyHomePage.goToGetSpotify();
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(this.myDriver);
        Assert.assertTrue(spotifySignUpPage.isLoaded(), "Pagina registro no se cargo correctamente");
        spotifySignUpPage.spotifySignUpForm("", "", "", "", "", "", "");
        Assert.assertFalse(signUpPage.startsWith(spotifySignUpPage.BASE_URL));
        
    }




}
