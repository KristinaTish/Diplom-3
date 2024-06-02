package testpac;

import apimethods.UserApi;
import apimethods.UserReg;
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
    public void openAccountProfile(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        ProfilePage profile = new ProfilePage(driver);
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        page1.openSite();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
        page1.clickPersonalAccount();
        profile.waitForProfileLoad();
    }
    //Проверь переход по клику из профиля на «Конструктор»
    @Test
    public void clickConstructorButtonTest(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        ProfilePage profile = new ProfilePage(driver);
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        page1.openSite();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
        page1.clickPersonalAccount();
        profile.waitForProfileLoad();
        profile.clickConstructorButton();
        page1.waitForMainPageLoad();
    }

//Проверь переход по клику из профиля на логотип Stellar Burgers.
    @Test
    public void clickOnLogoTest(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        ProfilePage profile = new ProfilePage(driver);
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        page1.openSite();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
        page1.clickPersonalAccount();
        profile.waitForProfileLoad();
        profile.clickLogo();
        page1.waitForMainPageLoad();
    }

    // Проверь выход по кнопке «Выйти» в личном кабинете.
    @Test
    public void logoutTest() {
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        ProfilePage profile = new ProfilePage(driver);
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        page1.openSite();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
        page1.clickPersonalAccount();
        profile.waitForProfileLoad();
        profile.logout();
        logPage.waitForLoginPageLoad();

    }
}
