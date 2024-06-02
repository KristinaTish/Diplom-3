package testpac;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pojopac.MainPage;

public class ConstructorTest {
    //Раздел «Конструктор»
    //Проверь, что работают переходы к разделам:
    //«Булки»,
    //«Соусы»,
    //«Начинки».

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    public void switchToSaucesTest(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        page1.openSite();
        page1.clickSauces();

        //метод проверки скролла
    }

    @Test
    public void switchToBunsTest(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        page1.openSite();
        page1.clickFilling();
        page1.clickBuns();

        //метод проверки скролла

    }

    @Test
    public void switchToFillingsTest(){
        WebDriver driver = driverFactory.getDriver();
        MainPage page1 = new MainPage(driver);
        page1.openSite();
        page1.clickFilling();
        //метод проверки скролла
    }
}
