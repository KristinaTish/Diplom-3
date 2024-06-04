package testpac;

import apimethods.UserApi;
import apimethods.UserReg;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pojopac.*;

public class LoginTest {

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    //вход по кнопке «Войти в аккаунт» на главной
    @Test
    @DisplayName("Log in through clicking 'Войти в аккаунт'-button on main page ")
    @Description("Try to login through login button on Password Recovery page, starting from main page ")
    public void loginUsingLoginButton(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        UserReg user = UserReg.someUserData();
        // С помощью Api создаем юзера и забираем токен
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        // логинимся через кнопку "Войти в аккаунт"
        page1.openSite();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
    }

    //вход через кнопку «Личный кабинет»,
    @Test
    @DisplayName("Log in through Personal Account button on main page")
    @Description("Try to login through Personal Account button on top of the main page")
    public void loginUsingPersonalAccountButton(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        // С помощью Api создаем юзера и забираем токен
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        // логинимся через кнопку "Личный Кабинет"
        page1.openSite();
        page1.clickPersonalAccount();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
    }

   // вход через кнопку в форме регистрации,
    @Test
    @DisplayName("Log in on Register Page full itinerary")
    @Description("Try to login through login button on Registration page, starting from main page ")
    public void loginOnRegisterPage(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        // С помощью Api создаем юзера и забираем токен
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        // переход к страничке с логином
        page1.openSite();
        page1.waitForMainPageLoad();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        // переход на страничку регистрации
        logPage.clickRegisterOnBottom();
        regPage.waitForRegistrationPageLoad();
        // логин
        regPage.clickLogin();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
    }


    @Test
    @DisplayName("Test login button on Register page through direct link")
    @Description("Try to login through login button on Registration page, using direct link: https://stellarburgers.nomoreparties.site/register/ ")
    public void loginOnRegisterPage2(){
        WebDriver driver = driverFactory.getDriver();
        LoginPage logPage = new LoginPage(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        // С помощью Api создаем юзера и забираем токен
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        // переход к страничке с регистрацией по прямой ссылке
        regPage.openRegistrationPage();
        regPage.waitForRegistrationPageLoad();
        // логин
        regPage.clickLogin();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
    }

    //вход через кнопку в форме восстановления пароля
    @Test
    @DisplayName("Log in on Password Recovery Page")
    @Description("Try to login through login button on Password Recovery page, starting from main page ")
    public void loginThroughPasswordRecovery(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        PasswordRecoverPage recover = new PasswordRecoverPage(driver);
        // С помощью Api создаем юзера и забираем токен
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        //открываем страничку логина
        page1.openSite();
        page1.waitForMainPageLoad();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        // переход на страничку с восстановлением пароля;
        logPage.clickForgotPassword();
        recover.login();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
    }

    @Test
    @DisplayName("Log in on Password Recovery Page using direct link")
    @Description("Try to login through login button on Password Recovery page, using direct link: https://stellarburgers.nomoreparties.site/forgot-password")
    public void loginThroughPasswordRecovery2(){
        WebDriver driver = driverFactory.getDriver();
        LoginPage logPage = new LoginPage(driver);
        PasswordRecoverPage recover = new PasswordRecoverPage(driver);

        // С помощью Api создаем юзера и забираем токен
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);

        // переход на страничку с восстановлением пароля по прямой ссылке;
        recover.openRecoveryPage();
        recover.login();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
    }


}
