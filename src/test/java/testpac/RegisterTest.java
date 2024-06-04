package testpac;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pojopac.LoginPage;
import pojopac.MainPage;
import pojopac.RegistrationPage;


@RunWith(Parameterized.class)
public class RegisterTest {

    private final String name;
    private final String email;

    private final String password;

//Проверить успешную регистрацию.
    @Rule
    public DriverFactory driverFactory = new DriverFactory();


    public RegisterTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] regData() {
        return new Object[][]{
                {"Kira", "qwerty@mail.cloud", "qwerty123"},
                {"Полина", "qwerty2q@mail.ru", "qwerty"}, // проверим минимальный пароль 6 симв
                {"Полина", "qwerty2q@mail.ru", "qwerty1"}, //пароль 7 символов
                {"Яна", "москва@москва.рф", "qwerty123"}, // почта кириллицей

        };
    }

    @Test
    @DisplayName("Register using direct link")
    @Description("Try to register using direct link on registration page: https://stellarburgers.nomoreparties.site/register/ ")
    public void testSuccessfulRegistration1() {
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        RegistrationPage regPage = new RegistrationPage(driver);

// используем прямую ссылку на страницу регистрации, зачастую она не работает
        regPage.openRegistrationPage();
        regPage.waitForRegistrationPageLoad();
        regPage.fillInRegistrationForm(name, email, password);

        logPage.waitForLoginPageLoad();
        logPage.login(email, password);
        page1.waitForMainPageLoad();

// извлекаем токен из localStorage
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String accessToken = (String) jsExecutor.executeScript("return localStorage.getItem('accessToken');");
        System.out.println("Access Token: " + accessToken);
        driverFactory.setAuthToken(accessToken); // передаем токен в after, где происходит удаление пользователя
    }


    @Test
    @DisplayName("Full registration itinerary")
    @Description("Try to register starting itinerary from main page ")
    public void testSuccessfulRegistration() {
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        LoginPage logPage = new LoginPage(driver);
        RegistrationPage regPage = new RegistrationPage(driver);

        page1.openSite();
        page1.waitForMainPageLoad();
        page1.clickPersonalAccount();
        logPage.waitForLoginPageLoad();
        logPage.clickRegisterOnBottom();
        regPage.waitForRegistrationPageLoad();
        regPage.fillInRegistrationForm(name, email, password);

        logPage.waitForLoginPageLoad();
        logPage.login(email, password);
        page1.waitForMainPageLoad();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String accessToken = (String) jsExecutor.executeScript("return localStorage.getItem('accessToken');");
        System.out.println("Access Token: " + accessToken); // на всякий оставлю, чтобы в случае проблемы проверить правильность токена
        driverFactory.setAuthToken(accessToken);
    }
}
