package testpac;


import apimethods.UserLogin;
import apimethods.UserReg;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pojopac.LoginPage;
import pojopac.MainPage;
import pojopac.RegistrationPage;

@RunWith(Parameterized.class)
public class ParameterizedInconvenientPasswordTest {
    private String password;

    public ParameterizedInconvenientPasswordTest(String password) {
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getPasswordParams(){
       return new Object[][] {
               {"qwert"}, //5 simbols
               {"qwer"},  // 4 simb
               {""}, //0 simb тут падает тест, не выводит ошибку
       };
    }

    @Rule
    public DriverFactory driverFactory = new DriverFactory();
// Проверить ошибку для некорректного пароля. Минимальный пароль — шесть символов.
    @Test
    public void inconvenientPasswordTest(){
        WebDriver driver = driverFactory.getDriver();
        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.openRegistrationPage();
        regPage.waitForRegistrationPageLoad();
        regPage.fillInRegistrationForm("Kira","qwerty1@mail.cloud", password);
        regPage.checkInconvenientPasswordError();
    }
}
