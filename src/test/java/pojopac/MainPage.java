package pojopac;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void openSite(){
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    public void waitForMainPageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[contains(@class, 'AppHeader')]/p[contains(text(), 'Личный Кабинет')]")));
    }

    public void clickPersonalAccount(){
        driver.findElement(By.xpath(".//a[contains(@class, 'AppHeader')]/p[contains(text(), 'Личный Кабинет')]")).click();

    }

    public void clickLoginButton(){
        driver.findElement(By.xpath(".//button[text()='Войти в аккаунт']")).click();
    }

    public void clickBuns(){
        driver.findElement(By.xpath(".//*[@id='root']/div/main/section[1]/div[1]/div[1]")).click();

    }
    public void verifyScrollToBuns(){

    }

    public void clickSauces(){
        driver.findElement(By.xpath(".//*[@id='root']/div/main/section[1]/div[1]/div[2]")).click();
    }

    public void verifyScrollToSauces(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h2[text()='Соусы']")));

        WebElement element = driver.findElement(By.xpath(".//h2[text()='Соусы']"));
        //надо придумать как тут сделать все


    }

    public void clickFilling(){
        driver.findElement(By.xpath(".//*[@id='root']/div/main/section[1]/div[1]/div[3]")).click();
    }

    public void verifyScrollToFillings(){

    }

    public void extractToken(){

    }
}
