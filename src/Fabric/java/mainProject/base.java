package mainProject;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class base {

    WebDriver driver;
    Properties prop;

    public WebDriver initialize_Driver() throws IOException {
        prop = new Properties();
        FileInputStream file = new FileInputStream("/home/ahmad/Fabric/Subscriptions/src/main/resources/data.properties");
        prop.load(file);
        String browserName = prop.getProperty("browser");

        if (Objects.equals(browserName, "chrome")){
            System.setProperty("webdriver.chrome.driver", "/home/ahmad/Downloads/Selenium/chromedriver/chromedriver");
            driver = new ChromeDriver();
        }
        else if (Objects.equals(browserName, "firefox")){
            System.setProperty("webdriver.gecko.driver","/home/ahmad/Downloads/Selenium/drivers/geckodriver");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

        return driver;
    }
}
