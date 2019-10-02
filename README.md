# Proyecto Final Bootcamp 2019

### Requerimientos Proyecto Final Bootcamp 2019

Página Web: https://www.spotify.com/mx
Fecha: **30 de Septiembre 2019**

#### Use Cases
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y visualizar la pantalla de inicio.
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y despues iniciar sesion en “Inicio de Sesion” y despues ingresar mis credenciales.
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y dar click en “Obten Spotify Gratis” para ir a la pagina de registro.
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y despues registrarme en “Registrate” y después llenar el formulario para crear una nueva cuenta.
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y despues recibir ayuda en “Ayuda” y después, eso te llevara a la página de Soporte Técnico.
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y regresar a la pagina principal dando click en icono de Spotify.

#### Entregables
- Documento de Test Plan.
- Repositorio con el framework de automatización y pruebas automatizadas.
- Documento con nombre de autores y aporte al proyecto.

[TOCM]

## Diagrama de clases y documentación
![](imageURL)
El diagrama de clases para nuestro framework está dividido en dos categorías, la primera son las clases enfocadas a las páginas y la segunda son los componentes que se derivan de la abstracción de cada una de las páginas.

## Clases de Páginas
### Page
La clase **Page** es una interface que contiene lo siguiente:
**Métodos:**
- open() = Este método se define posteriormente en las clases que implementen la interface y su finalidad es abrir la página deseada.
- isLoaded() = Este método se define posteriormente en las clases que implementen la interface con la finalidad de retornar un boolean si la página se ha cargado correctamente o no.
- close() = Este método se define posteriormente en las clases que implementen la interface con la finalidad de cerrar la ventana del navegador en la que se está enfocando el driver y posteriormente cerrar todas las ventanas del navegador y terminar la sesión de manera segura.

### BasePage
La clase **BasePage** contiene lo siguiente:
**Atributos:**
- driver: Genera una instancia del WebDriver
**Metodos:**
- isLoaded() = Implementa el método isLoaded que retorna un boolean si la página ha cargado correctamente.
- BasePage(driver:WebDriver) = Constructor que define el WebDriver el cual posteriormente será inicializado con el PageFactory.

### SpotityHomePage
**Atributos:**
- logoHomeLinkComponent: instancia de la clase LogoHomeLinkComponent, que nos ayuda a acceder a los atributos y método de la misma.
- homeHeaderComponent: instancia de la clase HomeHeaderComponent la cual nos ayuda a acceder a los atributos y métodos de la misma.
- homeBodyComponent: instancia de la clase HomeBodyComponent la cual nos ayuda a acceder a los atributos y métodos de la misma.
- wait: es un atributo de tipo WebdriverWait el cual será utilizado para agregar un explicit wait en el método isLoaded.
- BASE_URL: atributo de tipo String la cual contiene la URL de la página que estamos probando en este caso seria "https://www.spotify.com/mx/", nos ayudara a probar que el URL coincida con la misma que se encuentra en nuestro archivo test. properties.

**Métodos**
- SpotifyHomePage(driver:WebDriver) = método constructor de la clase el cual recibe como parámetro un elemento de tipo WebDriver
- isLoaded(): el cual retorna un valor tipo booleano para determinar si la página Spotify esta desplegada o no.
- goToHelpPage: método para ir a la ayuda de la página, retorna un elemento SpotifyHelpPage.
- goToSignUpPage: método para crear una cuenta en Spotify, retorna un elemento SpotifySingUpPage.
- goToGetPage: método para obtener Spotify, retorna un elemento SpotifySingUpPage.
- goToLoginPage: método para iniciar sección en Spotify, retorna un elemento SpotifyLoginPage.
- goToHomePage: método para regresar a la página principal de Spotify, retorna un elemento de tipo SpotifyHomePage.

### SpotifyLoginPage
**Atributos:**
- logoHomeLinkComponent: instancia de la clase LogoHomeLinkComponent, que nos ayuda a acceder a los atributos y método de la misma.
- LoginConponent: instancia de la clase LoginComponent, que nos ayuda para acceder a los atributos y métodos de la misma clase.
- wait: es un atributo de tipo WebdriverWait el cual será utilizado para agregar un explicit wait en el método isLoaded.
- BASE_URL: atributo de tipo String la cual contiene la URL de la página que estamos probando en este caso seria "https://www.spotify.com/mx/", nos ayudara a probar que el URL coincida con la misma que se encuentra en nuestro archivo test. properties.

**Métodos:**
- SpotifyLoginPage(driver:WebDriver) =  método constructor de la clase el cual recibe como parámetro un elemento de tipo webdriver
- isLoaded() = el cual retorna un valor tipo booleano para determinar si la página Spotify esta desplegada o no.
- spotifyLoginFrom: método para hacer Log In una cuenta en Spotify, retorna un elemento de tipo SpotityLoginPage
- goToHomePage: método para regresar a la página principal de Spotify, retorna un elemento de tipo SpotifyHomePage.

### Class SpotifySignUpPage
Esta clase es publica y  extendiende BasePage implements SingUpComponent
**Atributos:**
- logoHomeLinkComponent: este es el link que redirecciona a la pagina principal de la plataforma
- singUpComponent: este es el componente que genera el formulario de registro del usuario a la pagina.
- wait: se utiliza para poder vizualizar como se van ejecutando las acciones.
- BASE_URL: String: 
*Static final*:  esta clase no admite cambios.
**Constuctor:**
- SingUpComponent() = Genera un Nuevo marco de registro para usuarios de Spotify. Y le pasa como referencia este objeto a SpotifyLoginPage.
**Metodos:**
- void: spotifyLoginPage(driver: WebDriver) = Este método obtiene o escucha que se ha generado un Nuevo registro de usuario. La idea de esta clase es que el usuario al estar registrado ya puede iniciar sesión. Y el usuario ingresa a la plataforma con sus credenciales.
- booblean: isLoad() = Este método se utiliza para identificar que la página se ha cargado.
- protected: spotifySingUpForm(): SpotifySignUpPage = En este metodo se generan los campos a ser llenados por el usuario con sus credenciales.
- getToHomePage(): SpotyfyHomePage = Este metodo se creó para cuando el usuario inicia sesion ingresa a la página principal de la plataforma ya como un usuario registrado. 


#### SpotifyHelpPage
Esta clase es publica y  extendiende BasePage implements SingUpComponent
**Atributos:**
- logoHomeLinkComponent: este es el link que redirecciona a la página principal de la plataforma
- wait: se utiliza para dar un tiempo prudente para poder visualizar como se van ejecutando las acciones.
- BASE_URL: String: 
*Static final*: esta clase no admite cambios.
**Constuctor:**
- SingUpComponent() = Genera un Nuevo marco de registro para usuarios de Spotify. Y le pasa como referencia este objeto a SpotifyLoginPage.
**Metodos:**
- void: spotifyHelpPage(driver: WebDriver) = Este método es para crear el link que te redirecciona la página de ayuda de la plataforma.
- booblean: isLoad() = Este método se utiliza para identificar que la página se ha cargado.
- getToHomePage(): SpotyfyHomePage = Este método se creó para cuando el usuario inicia sesion ingresa a la página principal de la plataforma ya como un usuario registrado. 


#### SpotifyComponent
En esta clase se administran todos los componentes.
**Atributos:**
- driver: WebDriver
**Constuctor:**
- SpotifyComponent() = Instancia el Webdriver

#### HomeHeaderComponent
Esta clase es pública y es la que da acceso a la página principal de la plataforma Spotify.
**Atributos:**
- loginButton: este es el botón para que redirecciona al usuario a la página de incio de sesión.
- singUpButton: este es el botón que redirige a la pagina de registro de un usuario nuevo.
- helpButton: es el botón que muestra la ayuda a los usuarios.
**Constuctor:**
- HomeHeaderComponent() = En este apartado se presenta la página principal de la plataforma con todos sus atributos.
**Métodos:**
- ClickLoginButton() = Este método para iniciar sesión.
- ClickSignUpButton() = Este método crear nuevo usuario.
- ClickHelpButton() = Este método para obtener ayuda de la plataforma.

#### LoginComponent
Esta clase es protected y es la que da acceso a la sesión del usuario con las credenciales correspondientes.
**Atributos:**
- emailInput: este es el campo para ingresar la dirección de usuario.
- passInput: este campo es para ingresar la contraseña del usuario
- loginButton: es el botón que genera el inicio de sesión  con las credenciales ingresadas por el usuario.
**Constuctor:**
- LoginAccount() = Esta es la cuenta del usuario con las credenciales correspondientes.
**Metodos:**
- void: LoginComponent() = Este método para iniciar sesión.

#### HomeBodyComponent
Esta clase es publica y es la que da acceso a la composición de la página de la plataforma.
**Atributos:**
- getSpotifyButton:* navega a la pagina de registro de la plataforma.
**Constuctor:**
- HomeBodyComponent() = En este apartado se presenta el cuerpo página principal de la plataforma con todos sus atributos.
**Métodos:**
- clickOnGetSpotifyButton() = Este método te redirecciona a SpotifySignUpPage.

#### SignUpComponent
Esta clase es protected y es la que genera el formulario de registro.
**Atributos:**
- emailInput: este es el campo para ingresar la dirección de email del usuario.
- confirmPassInput:* este campo es para reingresar la contraseña de email del usuario.
- passInput: este campo es para ingresar la contraseña del usuario
- nameInput: este campo es para ingresar el nombre del usuario
- monthDropdown: este es un elemento que despliega los nombres de los meses a elegir por el usuario para su fecha de nacimiento.
- monthSelect: este es un Select, para generar la acción de elegir el mes correspondiente.
- dayInput: campo para ingresar el día correspondiente a la fecha de nacimiento del usuario.
- yearInput: campo para ingresar el año correspondiente a la fecha de nacimiento del usuario.
- genderRadioButtons: este es un botón para que el usuario elija el género (Hombre, Mujer, No binario).
- shareCheckBox: este elemento es para dar permiso de que la plataforma de spotify pueda compartir con sus proveedores los datos del usuario con fines de marketing.
- signUpButton: es el botón que genera el registro con las credenciales ingresadas por el usuario.
**Constuctor:**
- SingUpAccount() = En este se ha generado la cuenta creada del usuario con las credenciales correspondientes.
**Metodos:**
-  void: LoginComponent() = Este método obtiene que se ha registrado un nuevo usuario. Y es donde el usuario ingresa sus credenciales para iniciar sesión.
