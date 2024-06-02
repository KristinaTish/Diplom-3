package testpac;

import apimethods.UserApi;
import apimethods.UserReg;
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
    public void loginUsingLoginButton(){
        //надо будет подумать, делать ли тут регистрацию в этом тесте. По идее надо, чтобы тесты были независимы
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        page1.openSite();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
    }
//вход через кнопку «Личный кабинет»,
    @Test
    public void loginUsingPersonalAccountButton(){
        //надо будет подумать, делать ли тут регистрацию в этом тесте. По идее надо, чтобы тесты были независимы
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        page1.openSite();
        page1.clickPersonalAccount();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
    }

   // вход через кнопку в форме регистрации,
    @Test
    public void loginOnRegisterPage(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        page1.openSite();
        page1.waitForMainPageLoad();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.clickRegisterOnBottom();
        regPage.waitForRegistrationPageLoad();
        regPage.clickLogin();
        logPage.waitForLoginPageLoad();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();
    }

    //вход через кнопку в форме восстановления пароля
    @Test
    public void loginThroughPasswordRecovery(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        PasswordRecoverPage recover = new PasswordRecoverPage(driver);
        UserReg user = UserReg.someUserData();
        Response response = UserApi.createUser(user);
        String accessToken = UserApi.getToken(response);
        driverFactory.setAuthToken(accessToken);
        page1.openSite();
        page1.waitForMainPageLoad();
        page1.clickLoginButton();
        logPage.waitForLoginPageLoad();
        logPage.clickForgotPassword();
        recover.login();
        logPage.login(user.getEmail(), user.getPassword());
        logPage.waitForSuccessfullLogin();

    }


}
