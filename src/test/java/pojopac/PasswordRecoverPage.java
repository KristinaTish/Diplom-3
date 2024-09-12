package pojopac;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoverPage {
    WebDriver driver;

    public PasswordRecoverPage(WebDriver driver) {
        this.driver = driver;
    }

    public  void  openRecoveryPage(){
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    public void login() {
      driver.findElement(By.linkText("Войти")).click();
    }

}
