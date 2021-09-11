package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage {

    public WebDriver driver;
    By frame = By.xpath("//div[@id='root']/div/div/div/iframe");
    By accountID = By.id("accountId");
    By email = By.id("email");
    By password = By.id("password");
    By login_button = By.xpath("//button[text()='Log in']");
    By SMT = By.xpath("//a[@title='SMT']");

    public loginPage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement getFrame(){
        return driver.findElement(frame);
    }

    public WebElement get_accountID(){
        return driver.findElement(accountID);
    }

    public WebElement get_email(){
        return driver.findElement(email);
    }

    public WebElement get_password(){
        return driver.findElement(password);
    }

    public WebElement get_SMT(){
        return driver.findElement(SMT);
    }

    public WebElement get_button(){
        return driver.findElement(login_button);
    }

}
