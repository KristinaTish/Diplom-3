package testpac;

import apimethods.UserApi;
import apimethods.UserReg;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pojopac.LoginPage;
import pojopac.MainPage;
import pojopac.ProfilePage;

public class AccountTest {

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    // Проверь переход по клику на «Личный кабинет».
    @Test
    @DisplayName("Open Account Profile test")
    @Description("Here we check that we switch to profile when we click PersonalAccount-button while being logged in")
    public void openAccountProfile() {
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        ProfilePage profile = new ProfilePage(driver);
        UserReg user = UserReg.someUserData();
        // API - создание пользователя и получение токена
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        // логин
        page1.openSite();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
        // переход в профиль
        page1.clickPersonalAccount();
        profile.waitForProfileLoad();
    }

    //Проверь переход по клику из профиля на «Конструктор»
    @Test
    @DisplayName("Switch to main page by clicking Constructor test")
    @Description("Here we check that we switch to main page when we click on Constructor from profile")
    public void clickConstructorButtonTest() {
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        ProfilePage profile = new ProfilePage(driver);
        // API - создание пользователя и получение токена
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        // логин
        page1.openSite();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
        // переход в профиль
        page1.clickPersonalAccount();
        profile.waitForProfileLoad();
        // кликаем на конструктор
        profile.clickConstructorButton();
        page1.waitForMainPageLoad();
    }

    //Проверь переход по клику из профиля на логотип Stellar Burgers.
    @Test
    @DisplayName("Click on logo from Profile")
    @Description("Here we check that we switch to main page when we click logo")
    public void clickOnLogoTest() {
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        ProfilePage profile = new ProfilePage(driver);
        // API - создание пользователя и получение токена
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        // логин
        page1.openSite();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
        // переход в профиль
        page1.clickPersonalAccount();
        profile.waitForProfileLoad();
        // клик на лого
        profile.clickLogo();
        page1.waitForMainPageLoad();
    }

    // Проверь выход по кнопке «Выйти» в личном кабинете.
    @Test
    @DisplayName("Log out test")
    @Description("Here we check that we log out when push logout-button on profile page")
    public void logoutTest() {
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        ProfilePage profile = new ProfilePage(driver);
        // API - создание пользователя и получение токена
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        // логин
        page1.openSite();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
        // переход в профиль
        page1.clickPersonalAccount();
        profile.waitForProfileLoad();
        // выход
        profile.logout();
        logPage.waitForLoginPageLoad();

    }
}
