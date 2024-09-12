package testpac;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import pojopac.MainPage;

public class ConstructorTest {

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    private WebDriver driver;
    private MainPage page1;

    @Before
    public void setup(){
        driver = driverFactory.getDriver();
        page1 = new MainPage(driver);
        page1.openSite();
    }

    //переход к разделу «Соусы»
    @Test
    @DisplayName("Switch to Sauces")
    @Description("Here we check that we switch to sauces when we click sauces on Constructor panel")
    public void switchToSaucesTest(){
        WebElement element = page1.clickSauces();
        page1.verifyScroll(element);
    }

    //переход к разделу «Булки»
    @Test
    @DisplayName("Switch to Buns")
    @Description("Here we check that we switch to buns when we click buns on Constructor panel")
    public void switchToBunsTest(){
        page1.clickFilling();
        WebElement element = page1.clickBuns();
        page1.verifyScroll(element);

    }

    //переход к разделу «Начинка»
    @Test
    @DisplayName("Switch to Fillings")
    @Description("Here we check that we switch to fillings when we click fillings on Constructor panel")
    public void switchToFillingsTest(){
        WebElement element = page1.clickFilling();
        page1.verifyScroll(element);

    }
}
