package mainProject;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.loginPage;

import java.io.IOException;

public class loginCases extends base {

    loginPage log;

    public loginCases() throws IOException {
        driver = initialize_Driver();
    }

    @Test
    @BeforeClass
    public void login(){
        driver.get("https://sandbox.copilot.fabric.inc");
        log = new loginPage(driver);
        driver.switchTo().frame(log.getFrame());
        log.get_accountID().sendKeys("5827714929");
        log.get_email().sendKeys("ahsan@fabric.inc");
        log.get_password().sendKeys("merafabric1");
        log.get_button().click();
        log.get_SMT().click();
    }
}
