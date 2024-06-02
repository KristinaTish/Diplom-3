package pojopac;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;



public class ProfilePage {
    WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForProfileLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                 .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/main/div/nav/p")));
    }

    public void clickConstructorButton(){
        driver.findElement(By.xpath(".//*[@id='root']/div/header/nav/ul/li[1]/a/p")).click();
    }

    public void clickLogo(){
        driver.findElement(By.xpath(".//*[@id='root']/div/header/nav/div/a")).click();
    }

    public void logout(){
        driver.findElement(By.xpath(".//button[text()='Выход']")).click();
    }
}
