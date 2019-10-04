package com.qalabs.seleniumbasics.spotify;
import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpotifyTest extends BaseTest {

    @Test(description = "TC_US1_002 / Ir a la pagina principal de Spotify con URL incorrecta")
    public void goToHomePageFail() {
        PropertyReader propertyReader = new PropertyReader();
        String page = propertyReader.getProperty("credentials.properties", "URL_INVALID");

        this.myDriver.navigate().to(page);

        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertNotEquals(spotifyHomePage.BASE_URL, page, "Las paginas son iguales, cuando deberian ser diferente");
    }

    @Test(description = "TC_US3_001 / Llenar campos de registros de obtén Spotify gratis se envían vacíos")
    public void freeSpotifyEmptyFields() {
        PropertyReader propertyReader = new PropertyReader();
        String homePage = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        String signUpPage = propertyReader.getProperty("credentials.properties", "URL_SIGNUP_PAGE");

        this.myDriver.navigate().to(homePage);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, homePage, "Cargo una pagina diferente");

        spotifyHomePage.goToGetSpotify();
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(this.myDriver);
        Assert.assertTrue(spotifySignUpPage.isLoaded(), "Pagina registro no se cargo correctamente");
        spotifySignUpPage.spotifySignUpForm("", "", "", "", "", "", "");
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página no se cargo correctamente");
        Assert.assertTrue(spotifySignUpPage.BASE_URL.startsWith(signUpPage), "Logro registrarse, cuando no deberia");

    }

    @Test(description = "TC_US6_001 / Probar icono de spotify que redireccione a home page desde home page")
    public void goToHomePage() {
        PropertyReader propertyReader = new PropertyReader();
        String homePage = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");

        this.myDriver.navigate().to(homePage);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, homePage, "Cargo una pagina diferente");

        spotifyHomePage.clickOnSpotifyIcon();
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, homePage, "Cargo una pagina diferente");
    }

    @Test(description = "TC_US6_002 / Probar icono de spotify que redireccione a home page desde help page")
    public void goToHomePage() {
        PropertyReader propertyReader = new PropertyReader();
        String homePage = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        String helpPage = propertyReader.getProperty("credentials.properties", "URL_HELP");

        this.myDriver.navigate().to(homePage);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, homePage, "Cargo una pagina diferente");

        spotifyHomePage.goToHelpPage();
        SpotifyHelpPage spotifyHelpPage = new SpotifyHelpPage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página ayuda no se cargo correctamente");
        Assert.assertTrue(spotifyHelpPage.BASE_URL.startsWith(helpPage), "Cargo una pagina diferente a Ayuda");

        spotifyHomePage.clickOnSpotifyIcon();
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertTrue(spotifyHomePage.BASE_URL.startsWith(homePage), "Cargo una pagina diferente a la principal");
    }

}
