package testpac;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pojopac.LoginPage;
import pojopac.MainPage;
import pojopac.RegistrationPage;

@RunWith(Parameterized.class)
public class InconvenientPasswordRegistrationTest {

    private String name = "Kira";

    private String email = "qwerty1@mail.cloud";

    private final String password;

    public InconvenientPasswordRegistrationTest(String password) {
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getPasswordParams(){
       return new Object[][] {
               {"qwert"}, //5 simbols
               {"qwer"},  // 4 simb
               {"q"},
              {""}, //0 simb тут падает тест, не выводит ошибку
       };
    }

    @Rule
    public DriverFactory driverFactory = new DriverFactory();


    @Test
    @DisplayName("Register using wrong password: {password} through Register Page direct link")
    @Description("Try to register with wrong password using direct link: https://stellarburgers.nomoreparties.site/register/ ")
    public void inconvenientPasswordTest(){
        WebDriver driver = driverFactory.getDriver();
        RegistrationPage regPage = new RegistrationPage(driver);
        // тут по прямой ссылке пытаемся перейти к страничке регистрации, но она не открывается, поэтому тест падают
        regPage.openRegistrationPage();
        regPage.waitForRegistrationPageLoad();
        regPage.fillInRegistrationForm(name,email, password);
        regPage.checkInconvenientPasswordError();
    }

    @Test
    @DisplayName("Register using wrong password: : {password} full itinerary")
    @Description("Try to register with wrong password starting from main page: https://stellarburgers.nomoreparties.site/)")
    public void inconvenientPasswordTest2(){
        WebDriver driver = driverFactory.getDriver();
        MainPage main = new MainPage(driver);
        LoginPage login = new LoginPage(driver);
        RegistrationPage regPage = new RegistrationPage(driver);
        // а тут от главной странички переходим по кнопкам к регистрации
        main.openSite();
        main.waitForMainPageLoad();
        main.clickLoginButton();
        login.waitForLoginPageLoad();
        // переход к страничке регистрации
        login.clickRegisterOnBottom();
        regPage.waitForRegistrationPageLoad();

        regPage.fillInRegistrationForm(name,email, password);
        regPage.checkInconvenientPasswordError();
    }
}
