package pojopac;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage(){
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }

    public void waitForLoginPageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//main/div/h2[contains(text(),'Вход')]")));
    }

    public void clickRegisterOnBottom() {
        WebElement element = driver.findElement(By.xpath(".//div/div/p/a[contains(text(),'Зарегистрироваться')]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();

    }

    public void login(String email, String password){
        driver.findElement(By.xpath(".//*[@id='root']/div/main/div/form/fieldset[1]/div/div/input")).sendKeys(email);
        driver.findElement(By.xpath(".//*[@id='root']/div/main/div/form/fieldset[2]/div/div/input")).sendKeys(password);
        driver.findElement(By.xpath(".//button[text()='Войти']")).click();
    }


    public void waitForSuccessfullLogin(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='root']/div/main/section[1]/h1")));
    }

    public void clickForgotPassword(){
        driver.findElement(By.linkText("Восстановить пароль")).click();
    }

}
