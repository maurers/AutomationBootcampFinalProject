package com.qalabs.seleniumbasics.spotify;
import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SpotifyTest extends BaseTest {
  
    private String spotifyUrl;
  
    @BeforeTest
    public void initSetup(){
        PropertyReader propertyReader= new PropertyReader();
        spotifyUrl= propertyReader.getProperty("test.properties", "TEST_URL");
    }
  
    @Test(description = "TC_US1_001 / Ingresar a la página de Spotify y poder visualizar el contenido de la pantalla de inicio")
    public void goToHomePage() {
        PropertyReader propertyReader = new PropertyReader();
        String page = propertyReader.getProperty("credentials.properties", "SPOTIFY_PAGE");

        myDriver.navigate().to(page);

        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, page, "Cargo una pagina diferente");
    }
    
    @Test(description = "TC_US1_002 / Ir a la pagina principal de Spotify con URL incorrecta")
    public void goToHomePageFail() {
        PropertyReader propertyReader = new PropertyReader();
        String page = propertyReader.getProperty("credentials.properties", "URL_INVALID");

        this.myDriver.navigate().to(page);

        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertNotEquals(spotifyHomePage.BASE_URL, page, "Las paginas son iguales, cuando deberian ser diferente");
    }
  
    @Test(description = "TC_US_02_001/Acceder al formulario de Inicio de Sesión de Spotify", alwaysRun = true, priority = 2)
    public void invalidLoggerInSpotify(){
        String name;
        String pass;
        String pageLogin;
        myDriver.navigate().to(spotifyUrl);

        PropertyReader propertyReader = new PropertyReader();
        name=propertyReader.getProperty("credentials.properties", "USERNAME");
        pass=propertyReader.getProperty("credentials.properties", "PASSWORD");
        pageLogin=propertyReader.getProperty("credentials.properties","URL_LOGIN_PAGE");

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(myDriver);
        spotifyLoginPage.spotifyLoginPageForm(name,pass);

        Assert.assertTrue( SpotifyLoginPage.isLoaded(), "The page is not loaded" );
        Assert.assertFalse(pageLogin.startsWith( myDriver.getCurrentUrl ));

        Assert.assertEquals( myDriver.getSpotifyLogin(), SpotifyLoginPage);
        Assert.assertTrue( false, "bad_credentials" );
    }
    
    @Test(alwaysRun = true,description = "TC_US2_003/Acceder al formulario de Inicio de Sesión de Spotify")
    public void validLoggerInSpotify(){
        String email;
        String pass;
        String pageLogin;
        myDriver.navigate().to(spotifyUrl);
        PropertyReader propertyReader = new PropertyReader();
        email=propertyReader.getProperty("credentials.properties", "EMAIL_ADDRESS");
        pass=propertyReader.getProperty("credentials.properties", "PASSWORD");
        pageLogin=propertyReader.getProperty("credentials.properties", "URL_LOGIN_PAGE");
        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(myDriver);
        sporifyLoginPage.spotifyLoginPageFrom(email,pass);
        Assert.assertTrue(sporifyLoginPage.isLoaded(), "The page is not loaded");
        Assert.assertFalse(page.startsWith(myDriver.getCurrentUrl()));
    }
  
  @Test(description = "TC_US2_003 / Acceder al formulario de Inicio de Sesión de Spotify")
    public void loggerInSpotify() {
        String name;
        String pass;
      
        String spotifySignUpUrl= propertyReader.getProperty("credentials.properties", "SPOTIFY_SIGNUP_URL");
      
        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(this.myDriver);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this,myDriver);

        myDriver.navigate().to(spotifyUrl);
        //Assert.assertTrue(facebookLoginPage.isLoaded(), "Google results page is not loaded");
        Assert.assertEquals(myDriver.getCurrentUrl(), SpotifyHomePage.BASE_URL);


        com.qalabs.seleniumbasics.utils.PropertyReader propertyReader= new PropertyReader();
        name= propertyReader.getProperty("credentials.properties", "TC_US2_003_USSER_NAME");
        pass=propertyReader.getProperty("credentials.properties", "TC_US2_003_PASSWORD");

        //myDriver.navigate().to(spotifyLoginUrl);
        SpotifyHomePage.goToLoginPage();
        Assert.assertEquals(myDriver.getCurrentUrl(), SpotifyLoginPage.LOGIN_URL);

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(myDriver);
        SpotifyLoginPage.spotifyLoginForm(name,pass);
        Assert.assertTrue(myDriver.getCurrentUrl().startsWith(spotifyLoginUrl),"No se realizo el login");
    }
  
    @Test(description = "TC_US2_004/Acceder al formulario de Inicio de Sesión de Spotify ")
    public void LoginSpotifyCheck(){
        //this.myDriver.navigate().to(spotifyUrl);
        PropertyReader propertyReader = new PropertyReader();
        String pageLoginUrl = propertyReader.getProperty("credentials.properties","URL_LOGIN_PAGE");

        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        SpotifyLoginPage spotifyLoginPage = spotifyHomePage.goToLoginPage();

        //checks if spotifyLoginPage has finish loading with the isLoaded method and its BASE_URL
        Assert.assertTrue(spotifyLoginPage.isLoaded(), "Página de login no se cargo correctamente");
        //Checks if the URL of mydrive is the same as the one in credentials
        Assert.assertEquals(pageLoginUrl,myDriver.getCurrentUrl());
    }

    @Test(description = "TC_US2_005 / Acceder al formulario de Inicio de Sesión e ingresar sus credenciales de manera manual, utilizando un username, sin emplear algún método de autenticación de terceros")
    public void loginAtSpotify(){

        PropertyReader propertyReader = new PropertyReader();
        String homePage = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        String loginPage = propertyReader.getProperty("credentials.properties", "URL_LOGIN");

        myDriver.navigate().to(homePage);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, homePage, "Cargo una pagina diferente");

        spotifyHomePage.goToLoginPage();


        String user = propertyReader.getProperty("credentials.properties", "USERNAME");
        String pass = propertyReader.getProperty("credentials.properties", "PASSWORD");

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(myDriver);
        Assert.assertTrue(spotifyLoginPage.isLoaded(), "Página de login no se cargo correctamente");
        spotifyLoginPage.spotifyLoginForm(user,pass);
        Assert.assertFalse( spotifyLoginPage.isLoaded(), "No inició sesión, sigue en la página de login");
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
  
    @Test(description = "TC_US3_002/Campos de registro de obtén spotify gratis se envían llenos y sin validar captcha", alwaysRun = true, priority = 0)
    public void spotifySignUp(){
        String email;
        String pass;
        String name;
        String day;
        String month;
        String year;
        String gender;
        String pageSingUp;


        myDriver.navigate().to(spotifyUrl);

        PropertyReader propertyReader = new PropertyReader();
        email=propertyReader.getProperty("credentials.properties", "EMAIL");
        pass=propertyReader.getProperty("credentials.properties", "PASSWORD");
        name=propertyReader.getProperty("credentials.properties","NAME");
        day=propertyReader.getProperty("credentials.properties", "DAY");
        month=propertyReader.getProperty("credentials.properties","MONTH");
        year=propertyReader.getProperty("credentials.properties", "YEAR_VALID");
        gender=propertyReader.getPorperty("credentials.properties","GENDER");
        pageSingUp=propertyReader.getProperty("credentials.properties", "URL_SIGNUP_PAGE");

        SpotifySingUpPage spotifySingUpPage = new SpotifySingUpPage(driver);
        spotifySingUpForm.signUpAccount(email,pass,name,day,month,year,gender);

        Assert.assertTrue( SpotifySingUp.isLoaded(), "The page is not loaded" );
        Assert.assertFalse(pageSingUp.startsWith( myDriver.getCurrentUrl ));
        Assert.assertFalse( true, "Se solicita validacion captcha" );
    }
  
    @Test(alwaysRun = true,description = "TC_US4_001/ Llenar el formulario para crear cuenta nueva")
        public void invalidSignUpInInSpotify() {
        String email;
        String pass;
        String name;
        String day ;
        String month;
        String year;
        String pageSignUp;

        myDriver.navigate().to(spotifyUrl);
        PropertyReader propertyReader = new PropertyReader();
        email=propertyReader.getProperty("credentials.properties", "EMAIL");
        pass=propertyReader.getProperty("credentials.properties", "PASSWORD");
        name=propertyReader.getProperty("credentials.properties","NAME");
        day=propertyReader.getProperty("credentials.properties", "DAY");
        month=propertyReader.getProperty("credentials.properties","MONTH");
        year=propertyReader.getProperty("credentials.properties", "YEAR_VALID");
        pageSignUp=propertyReader.getProperty("credentials.properties", "URL_SIGNUP_PAGE");

        SpotifySignUpPage spotifySingUpPage = new SpotifySingUpPage(myDriver);
        spotifySingUpForm.signUpAccount(email,pass,name,day,month,year);
        Assert.assertTrue( SpotifySignUpPage.isLoaded(), "The page is not loaded");
        Assert.assertFalse(pageSignUp.startsWith(myDriver.getCurrentUrl()));

    }
  
   @Test(description = "TC_US4_002 / Formulario de Crear cuenta nueva no se envia por dia de nacimiento invalido")
    public void loggerInSpotifySignUp() {
        String name= null;
        String pass = null;
        String email=null;
        String day= null;
        String year= null;
        String month= null;
        String gender= null;
        
        String spotifyLoginUrl = propertyReader.getProperty("credentials.properties", "SPOTIFY_LOGIN_URL");
      
        PropertyReader propertyReader = new PropertyReader();
        name= propertyReader.getProperty("credentials.properties", "TC_US4_002_USSER_NAME");
        pass=propertyReader.getProperty("credentials.properties", "TC_US4_002_PASSWORD");
        email=propertyReader.getProperty("credentials.properties", "TC_US4_002_EMAIL");
        day=propertyReader.getProperty("credentials.properties", "TC_US4_002_DAY");
        month=propertyReader.getProperty("credentials.properties", "TC_US4_002_MONTH");
        year=propertyReader.getProperty("credentials.properties", "TC_US4_002_YEAR");
        gender=propertyReader.getProperty("credentials.properties", "TC_US4_002_GENDER");
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(this.myDriver);

        myDriver.navigate().to(spotifyUrl);
        //Assert.assertTrue(facebookLoginPage.isLoaded(), "Google results page is not loaded");
        Assert.assertEquals(myDriver.getCurrentUrl(), SpotifyHomePage.BASE_URL);

        SpotifyHomePage.goToSignUpPage();
        //Assert.assertTrue(facebookLoginPage.isLoaded(), "Google results page is not loaded");
        Assert.assertEquals(myDriver.getCurrentUrl(), SpotifySignUpPage.SPOTIFY_SIGNUP_URL);



        spotifySignUpPage.spotifySignUpform(name,pass,email,day,month,year,gender);
        Assert.assertNotEquals(myDriver.getCurrentUrl(),SpotifySignUpPage.SPOTIFY_SIGNUP_URL);
    }

    @Test(description = "TC_US5_001 / Ir a la pagina de soporte tecnico atraves del link de Ayuda en el Header")
    // Mi duda es... si el link a support esta en el header..
    // no seria mejor hacer las pruebas directo al header y evitar duplicar codigo pra probar desde cada URL
    public void HelpPageLoadingCheck() {
        PropertyReader propertyReader = new PropertyReader();
        String helpPageUrl = propertyReader.getProperty("credentials.properties", "URL_HELP");
        String homePageUrl = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");

        //instantiate  a SpotifyHomePage with myDriver
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página de ayuda no se cargo correctamente");


        //Ininstantiate SpotifyHelpPage with the result of the spotifyHomePage.goToHelpPage method
        SpotifyHelpPage spotifyHelpPage = spotifyHomePage.goToHelpPage();

        //checks if spotifyHelpPage has finish loading with the isLoaded method and its BASE_URL
        Assert.assertTrue(spotifyHelpPage.isLoaded(), "Página de ayuda no se cargo correctamente");
        //Checks if the URL of mydrive is the same as the one in credentials
        Assert.assertTrue(myDriver.getCurrentUrl().startsWith(helpPageUrl));
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
