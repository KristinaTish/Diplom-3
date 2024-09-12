package testpac;

import apimethods.UserApi;
import io.restassured.RestAssured;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class DriverFactory extends ExternalResource {
    private WebDriver driver;

    private final static String STELLAR_URL = "https://stellarburgers.nomoreparties.site/";
    private String authToken;
    private String yandex = "yandex";
    private String chrome = "chrome";

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    @Override
    protected void before(){
        DriverFactory factory = new DriverFactory();
        //подставить сюда Яндекс или Хром в зависимости от выбранного браузера и запустить тесты
        driver = factory.getWebDriver(chrome);
        RestAssured.baseURI = STELLAR_URL;
    }

    @Override
    protected  void after(){
        if(authToken != null && !authToken.isEmpty()){
            UserApi.deleteUser(authToken);
        }
        driver.quit();
    }


public WebDriver getWebDriver(String browserName){
    browserName = browserName.toLowerCase();
    switch(browserName){
        case "chrome":
            return new ChromeDriver();
        case "yandex":
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
            return new ChromeDriver(options);

        default:
            throw new IllegalArgumentException("Browser '" + browserName + "' is not supported.");

    }
}
    public WebDriver getDriver(){
        return driver;
    }
}
