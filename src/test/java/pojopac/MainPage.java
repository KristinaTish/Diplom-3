package pojopac;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;


public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openSite() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    public void waitForMainPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[contains(@class, 'AppHeader')]/p[contains(text(), 'Личный Кабинет')]")));
    }

    public void waitForToken() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("a[class^='BurgerIngredient']"),7));
    }

    public void clickPersonalAccount() {
        driver.findElement(By.xpath(".//a[contains(@class, 'AppHeader')]/p[contains(text(), 'Личный Кабинет')]")).click();

    }

    public void clickLoginButton() {
        driver.findElement(By.xpath(".//button[text()='Войти в аккаунт']")).click();
    }

    public WebElement clickBuns() {
        WebElement element = driver.findElement(By.xpath(".//*[@id='root']/div/main/section[1]/div[1]/div[1]"));
        element.click();
        return element;
    }

    public WebElement clickSauces() {
        WebElement element = driver.findElement(By.xpath(".//*[@id='root']/div/main/section[1]/div[1]/div[2]"));
        element.click();
        return element;
    }


    public WebElement clickFilling() {
        WebElement element = driver.findElement(By.xpath(".//*[@id='root']/div/main/section[1]/div[1]/div[3]"));
        element.click();
        return element;
    }

    public void verifyScroll(WebElement element) {
        assertTrue(element.getAttribute("class").contains("current"));
    }
}
