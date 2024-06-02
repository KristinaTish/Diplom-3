package testpac;

import apimethods.UserApi;

import org.junit.Rule;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pojopac.LoginPage;
import pojopac.MainPage;
import pojopac.RegistrationPage;

import java.time.Duration;


//@RunWith(Parameterized.class)
public class RegisterTest {

    private String name;
    private String email;

    private  String password;

//Проверить успешную регистрацию.
//Единственное, надо почту через рандом переписать и из теста убрать данные, надо соханить их в переменной
@Rule
public DriverFactory driverFactory = new DriverFactory();

// это если прямо полный флоу нужен от главной страницы захода до регистрации
@Test
public void testSuccessfulRegistration1(){
    WebDriver driver = driverFactory.getDriver();
    MainPage page1 = new MainPage(driver);
    LoginPage logPage = new LoginPage(driver);
    RegistrationPage regPage = new RegistrationPage(driver);
    name = "Kira";
    email = "qwerty@mail.cloud";
    password = "qwerty123";
    regPage.openRegistrationPage();
    regPage.waitForRegistrationPageLoad();
    regPage.fillInRegistrationForm(name,email, password);

    logPage.waitForLoginPageLoad();
    logPage.login(email, password);
    page1.waitForMainPageLoad();


    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    String accessToken = (String) jsExecutor.executeScript("return localStorage.getItem('accessToken');");
 //   System.out.println("Access Token: " + accessToken);
    driverFactory.setAuthToken(accessToken);
}


@Test
    public void testSuccessfulRegistration() {
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        name = "Kira";
        email = "qwerty@mail.cloud";
        password = "qwerty123";
        page1.openSite();
        page1.waitForMainPageLoad();
        page1.clickPersonalAccount();
        logPage.waitForLoginPageLoad();
        logPage.clickRegisterOnBottom();
        regPage.waitForRegistrationPageLoad();
        regPage.fillInRegistrationForm(name,email, password);

        logPage.waitForLoginPageLoad();
        logPage.login(email, password);
        page1.waitForMainPageLoad();


        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String accessToken = (String) jsExecutor.executeScript("return localStorage.getItem('accessToken');");
     //   System.out.println("Access Token: " + accessToken);
        driverFactory.setAuthToken(accessToken);

    }
}
