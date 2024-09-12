package pojopac;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegistrationPage {
    private WebDriver driver;
    private static final String REGISTER_URL = "https://stellarburgers.nomoreparties.site/register/";
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openRegistrationPage(){
        driver.get(REGISTER_URL);
    }


    public void waitForRegistrationPageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div/h2[contains(text(), 'Регистрация')]")));
    }

    public void fillInRegistrationForm(String name, String email, String password){
     driver.findElement(By.xpath(".//*[@id='root']/div/main/div/form/fieldset[1]/div/div/input")).sendKeys(name);
     driver.findElement(By.xpath(".//*[@id='root']/div/main/div/form/fieldset[2]/div/div/input")).sendKeys(email);
     driver.findElement(By.xpath(".//*[@id='root']/div/main/div/form/fieldset[3]/div/div/input")).sendKeys(password);
     driver.findElement(By.xpath(".//button[contains(text(),'Зарегистрироваться')]")).click();
    }


    public void checkInconvenientPasswordError(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p")));
        String expectedError = "Некорректный пароль";
        String errorText = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p")).getText();
        assertEquals("Текст ошибки не совпадает с ожидаемым! ", expectedError,errorText);
    }

    public void clickLogin(){
        driver.findElement(By.linkText("Войти")).click();    }
}
