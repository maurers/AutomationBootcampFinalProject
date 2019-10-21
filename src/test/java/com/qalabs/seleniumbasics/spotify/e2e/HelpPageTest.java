package com.qalabs.seleniumbasics.spotify.e2e;

import com.qalabs.javabasics.spotify.pages.SpotifyHelpPage;
import com.qalabs.javabasics.spotify.pages.SpotifyHomePage;
import com.qalabs.seleniumbasics.spotify.BaseTest;
import com.qalabs.seleniumbasics.spotify.utilities.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HelpPageTest extends BaseTest {

    private String spotifyUrl, helpUrl;
    private PropertyReader propertyReader = new PropertyReader();
    private SpotifyHomePage spotifyHomePage;
    private SpotifyHelpPage spotifyHelpPage;

    @BeforeMethod
    public void initSetup() {
        spotifyUrl = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        helpUrl = propertyReader.getProperty("credentials.properties", "URL_HELP");

        driver.navigate().to(spotifyUrl);
        spotifyHomePage = new SpotifyHomePage(driver);

        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(driver.getCurrentUrl(), spotifyHomePage.BASE_URL, "Cargó una página diferente al de home");
    }
    @Test(description ="TC_US5_001 / Ir a la pagina de soporte tecnico atraves del link de Ayuda en el Header")
    public void goHelpPageToHomePage() {
        spotifyHomePage.goToHelPage();
        spotifyHelpPage = new SpotifyHelpPage(driver);

        Assert.assertTrue(spotifyHelpPage.isLoaded(), "Página de ayuda no se cargo correctamente");
        Assert.assertTrue(driver.getCurrentUrl().contains(spotifyHelpPage.BASE_URL), "Cargó una página diferente al de ayuda");

    }

}
