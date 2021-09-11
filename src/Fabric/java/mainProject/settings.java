package mainProject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.loginPage;
import pageObjects.settingPage;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.Properties;

public class settings extends base {

    settingPage setting;
    loginPage log;
    Properties data;
    Actions action;
    SoftAssert softAssert;

    public settings() throws IOException {
        driver = initialize_Driver();
        softAssert = new SoftAssert();
        setting = new settingPage(driver);
        log = new loginPage(driver);
        action = new Actions(driver);
        data = prop;
    }

    @Test(groups = {"Webhooks","Notifications"}, priority = 0)
    public void login(){
        driver.get("https://" + data.getProperty("env")  + ".copilot.fabric.inc");
        driver.switchTo().frame(log.getFrame());
        log.get_accountID().sendKeys(data.getProperty("account"));
        log.get_email().sendKeys(data.getProperty("email"));
        log.get_password().sendKeys(data.getProperty("password"));
        log.get_button().click();
        log.get_SMT().click();
    }

    @Test(dependsOnMethods = {"login"}, priority = 1, groups = {"Webhooks","Notifications"})
    public void goto_Settings(){
        driver.switchTo().frame(setting.getFrame());
        setting.getSettings().click();
        softAssert.assertEquals(setting.getTitle().getText(),"Settings", "Settings title is not valid");
    }

    @Test(dependsOnMethods = {"goto_Settings"}, groups = {"Webhooks","Notifications"})
    public void validateSettingsMenu(){
        driver.switchTo().defaultContent();
        driver.switchTo().frame(setting.getFrame());
        List<WebElement> menu = setting.getSettingMenu();
        softAssert.assertEquals(menu.get(0).getText(), "Webhooks", "Webhook has not valid name in settings menu");
        softAssert.assertEquals(menu.get(1).getText(), "Notification & Dunning", "Notification & Dunning has not valid name in settings menu");
        softAssert.assertEquals(menu.get(2).getText(), "Custom attributes", "Custom Attributes has not valid name in settings menu");
    }

    @Test(dependsOnMethods = {"goto_Settings"}, groups = {"Webhooks"})
    public void validateWebhookLeftUI(){
        softAssert.assertTrue(setting.getSettingTitle().isDisplayed(), "Webhooks Settings title is not displayed");
        softAssert.assertEquals(setting.getSettingTitle().getText(), "Webhooks", "Webhook Settings has not the appropriate title");
        WebElement button = setting.getAddWebhookButton();
        softAssert.assertTrue(button.isDisplayed(), "Add webhook button is not displayed");
        softAssert.assertTrue(button.isEnabled(), "Add webhook button is not enabled");
        softAssert.assertEquals(button.getText(),"Add webhook", "Add Webhook Button has not the right text");
        softAssert.assertEquals(button.getCssValue("font-family"),"Gilroy-Medium", "Add Webhook Button Design Issue: font is not Gilroy-Medium");
        softAssert.assertEquals(button.getCssValue("font-size"),"13px", "Add Webhook Button Design Issue: font size is not 13px");
        softAssert.assertEquals(button.getCssValue("padding-top"),"8px", "Add Webhook Button Design Issue: padding-top is not 8px");
        softAssert.assertEquals(button.getCssValue("padding-bottom"),"8px", "Add Webhook Button Design Issue: padding-bottom is not 8px");
        softAssert.assertEquals(button.getCssValue("padding-left"),"14px", "Add Webhook Button Design Issue: padding-left is not 14px");
        softAssert.assertEquals(button.getCssValue("padding-right"),"14px", "Add Webhook Button Design Issue: padding-right is not 14px");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"goto_Settings"}, groups = {"Webhooks"})
    public void validateWebhookRightUI(){
        softAssert.assertTrue(driver.findElement(By.className("right")).isDisplayed(), "Webhook List section is not visible");
        List<WebElement> webhooks = setting.getWebhooks();
        softAssert.assertTrue(webhooks.size() > 0, "No Webhook found");
        if (webhooks.size() > 0){
            String webhookName = setting.getWebhookTitle().get(0).getText();
            String webhookLink = setting.getWebhookURL().get(0).getText();
            softAssert.assertFalse(setting.getWebhookView().get(0).isDisplayed(),"Webhook View Button is visible without hovering");
            action.moveToElement(setting.getWebhookView().get(0)).perform();
            softAssert.assertTrue(setting.getWebhookView().get(0).isDisplayed(),"Webhook View Button is not visible after hover");
            setting.getWebhookView().get(0).click();
            driver.switchTo().activeElement();
            softAssert.assertEquals(setting.getWebhookModalName().getText(),webhookName,"Selected Webhook Name is not Matched");
            softAssert.assertEquals(setting.getWebhookModalLink().getAttribute("value"),webhookLink,"Selected Webhook URL is not Matched");
            softAssert.assertTrue(setting.getWebhookDeleteButton().isDisplayed(),"Delete button is not visible");
            softAssert.assertTrue(setting.getWebhookSaveButton().isDisplayed(),"Save button is not visible");
            softAssert.assertTrue(setting.getWebhookCancelButton().isDisplayed(),"Cancel button is not visible");
        }
    }

    @Test(dependsOnMethods = {"validateWebhookRightUI"}, groups = {"Webhooks"})
    public void validateWebhooksEditFeature() throws InterruptedException {
        String webhookLink = setting.getWebhookURL().get(0).getText() + "123";
        setting.getWebhookModalLink().sendKeys(Keys.CONTROL + "A");
        setting.getWebhookModalLink().sendKeys(webhookLink);
        setting.getWebhookSaveButton().click();
        Thread.sleep(6000);
        driver.switchTo().activeElement();
        softAssert.assertEquals(setting.getWebhookURL().get(0).getText(),webhookLink,"Webhook link was not saved");
    }

    public boolean check(List<WebElement> names, String name){
        int i = names.size();
        boolean isExist = false;
        while (i > 0){
            if (names.get(i-1).getText().equals(name)){
                isExist = true;
            }
            i--;
        }
        return isExist;
    }

    @Test (dependsOnMethods = {"validateWebhooksEditFeature"}, groups = {"Webhooks"})
    public void validateWebhooksCancelFeature() throws InterruptedException {
        String webhookName = setting.getWebhookTitle().get(1).getText();
        action.moveToElement(setting.getWebhookView().get(1)).perform();
        setting.getWebhookView().get(1).click();
        driver.switchTo().activeElement();
        Thread.sleep(2500);
        setting.getWebhookCancelButton().click();
        driver.switchTo().activeElement();
        Thread.sleep(2500);
        softAssert.assertEquals(setting.getWebhookTitle().get(1).getText(), webhookName, "Cancel button has been deleted/rearranged the webhooks list");
    }

    @Test(dependsOnMethods = {"validateWebhooksCancelFeature"}, groups = {"Webhooks"})
    public void validateWebhooksAddFeature() throws InterruptedException {
        setting.getAddWebhookButton().click();
        driver.switchTo().activeElement();
        System.out.println("Click on Modal Name");
        setting.getWebhookModalName().click();
        Thread.sleep(2000);
        List<WebElement> list = setting.getWebhookNameList();
        int i = list.size();
        while (i > 0){
            if (list.get(i-1).getText().equals(prop.getProperty("webhookTitle"))){
                System.out.println(list.get(i-1).getText());
                list.get(i-1).click();
                setting.getWebhookModalLink().sendKeys(prop.getProperty("webhookURL"));
                setting.getWebhookSaveButton().click();
                i = 0;
            }
            i--;
        }
        driver.switchTo().activeElement();
        Thread.sleep(2000);
        softAssert.assertTrue(check(setting.getWebhookTitle(), prop.getProperty("webhookTitle")), "Webhook Event not created");
        softAssert.assertAll();
    }



    @Test(dependsOnMethods = {"validateWebhooksAddFeature"}, groups = {"Webhooks"})
    public void validateWebhooksDeleteFeature() throws InterruptedException {
        List<WebElement> list = setting.getWebhookTitle();
        int i = list.size();
        while (i > 0){
            if (list.get(i-1).getText().equals(prop.getProperty("webhookTitle"))){
                action.moveToElement(setting.getWebhookView().get(i-1)).perform();
                setting.getWebhookView().get(i-1).click();
                i = 0;
                driver.switchTo().activeElement();
                Thread.sleep(2000);
                setting.getWebhookDeleteButton().click();
                Thread.sleep(6000);
                driver.switchTo().activeElement();
            }
            i--;
        }
        softAssert.assertFalse(check(setting.getWebhookTitle(), prop.getProperty("webhookTitle")), "Webhook Event not deleted");
        softAssert.assertAll();
    }


    @Test(groups = {"Notifications"}, dependsOnMethods = {"goto_Settings"})
    public void goto_NotificationsAndVerify(){
        driver.switchTo().activeElement();
        setting.getSettingMenu().get(1).click();
        softAssert.assertTrue(setting.getNotificationTitle().isDisplayed(), "Notification Settings title is not displayed");
        softAssert.assertEquals(setting.getNotificationTitle().getText(),"Notification & Dunning","Title of Notification & Dunning is not matched ");
    }

    @Test(dependsOnMethods = {"goto_NotificationsAndVerify"}, groups = {"Notifications"})
    public void validateNotificationLeftUI(){
        WebElement details = setting.getNotificationDetail();
        softAssert.assertEquals(details.getCssValue("font-family"),"Gilroy-Medium", "Notification Detail Design Issue: font is not Gilroy-Medium");
        softAssert.assertEquals(details.getCssValue("font-size"),"13px", "Notification Detail Design Issue: font size is not 13px");
        softAssert.assertEquals(details.getCssValue("line-height"),"15px", "Notification Detail Design Issue: font size is not 15px");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"goto_NotificationsAndVerify"}, groups = {"Notifications"})
    public void validateNotificationRightUI(){
        softAssert.assertTrue(setting.getNotificationSettings().isDisplayed(), "Notifications settings are not displayed");
        softAssert.assertTrue(setting.getNotificationBeforeChargeTitle().isDisplayed(),"Notifications Before Charge is not available");
        softAssert.assertTrue(setting.getNotificationRetryTimeTitle().isDisplayed(),"Notifications Retry Time is not available");
        softAssert.assertTrue(setting.getNotificationRetryAttemptsTitle().isDisplayed(),"Notifications Retry Attempts is not available");
        softAssert.assertTrue(setting.getNotificationBeforeChargeValue().isDisplayed(),"Notifications Before Charge value is missing");
        softAssert.assertTrue(setting.getNotificationRetryTimeValue().isDisplayed(),"Notifications Retry Time value is missing");
        softAssert.assertTrue(setting.getNotificationRetryAttemptsValue().isDisplayed(),"Notifications Retry Attempts value is missing");
        softAssert.assertFalse(setting.getNotificationBeforeChargeEdit().isDisplayed(),"Notifications Before Charge Edit is not available");
        softAssert.assertFalse(setting.getNotificationRetryTimeEdit().isDisplayed(),"Notifications Retry Time Edit is not available");
        softAssert.assertFalse(setting.getNotificationRetryAttemptsEdit().isDisplayed(),"Notifications Retry Edit Attempts is not available");
    }

    @Test(dependsOnMethods = {"validateNotificationRightUI"}, groups = {"Notifications"})
    public void validateBeforeChangeFunctionality() throws InterruptedException {
        int BeforeChange = Integer.parseInt(String.valueOf(setting.getNotificationBeforeChargeValue().getText().charAt(0)));
        action.moveToElement(setting.getNotificationBeforeChargeEdit()).perform();
        setting.getNotificationBeforeChargeEdit().click();
        Thread.sleep(1500);
        driver.switchTo().activeElement();
        setting.getEditBeforeCharge().sendKeys(Keys.CONTROL + "A");
        setting.getEditBeforeCharge().sendKeys(String.valueOf(BeforeChange + 1));
        setting.getSaveBeforeCharge().click();
        Thread.sleep(3500);
        driver.switchTo().activeElement();
        int AfterChange = Integer.parseInt(String.valueOf(setting.getNotificationBeforeChargeValue().getText().charAt(0)));
        softAssert.assertFalse(String.valueOf(AfterChange).equals(String.valueOf(BeforeChange)),"Before Charge is not edited/saved");
    }

    @Test(dependsOnMethods = {"validateNotificationRightUI"}, groups = {"Notifications"})
    public void validateRetryTimeFunctionality() throws InterruptedException {
        int beforeRetryTime = Integer.parseInt(String.valueOf(setting.getNotificationRetryTimeValue().getText().charAt(0)));
        int beforeRetryAttempt = Integer.parseInt(String.valueOf(setting.getNotificationRetryAttemptsValue().getText().charAt(0)));
        action.moveToElement(setting.getNotificationRetryTimeEdit()).perform();
        setting.getNotificationRetryTimeEdit().click();
        Thread.sleep(1500);
        driver.switchTo().activeElement();
        setting.getEditRetryTime().sendKeys(Keys.CONTROL + "A");
        setting.getEditRetryTime().sendKeys(String.valueOf(beforeRetryTime + 1));
        setting.getEditRetryAttempts().sendKeys(Keys.CONTROL + "A");
        setting.getEditRetryAttempts().sendKeys(String.valueOf(beforeRetryAttempt + 1));
        setting.getSaveRetry().click();
        Thread.sleep(3500);
        driver.switchTo().activeElement();
        int afterRetryTime = Integer.parseInt(String.valueOf(setting.getNotificationRetryTimeValue().getText().charAt(0)));
        int afterRetryAttempt = Integer.parseInt(String.valueOf(setting.getNotificationRetryAttemptsValue().getText().charAt(0)));
        softAssert.assertFalse(String.valueOf(beforeRetryTime).equals(String.valueOf(afterRetryTime)),"Retry time is not edited/saved");
        softAssert.assertFalse(String.valueOf(beforeRetryAttempt).equals(String.valueOf(afterRetryAttempt)),"Retry attempts is not edited/saved");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"validateNotificationRightUI"}, groups = {"Notifications"})
    public void validateRetryAttemptFunctionality() throws InterruptedException {
        int beforeRetryTime = Integer.parseInt(String.valueOf(setting.getNotificationRetryTimeValue().getText().charAt(0)));
        int beforeRetryAttempt = Integer.parseInt(String.valueOf(setting.getNotificationRetryAttemptsValue().getText().charAt(0)));
        action.moveToElement(setting.getNotificationRetryAttemptsEdit()).perform();
        setting.getNotificationRetryAttemptsEdit().click();
        Thread.sleep(1500);
        driver.switchTo().activeElement();
        setting.getEditRetryTime().sendKeys(Keys.CONTROL + "A");
        setting.getEditRetryTime().sendKeys(String.valueOf(beforeRetryTime + 1));
        setting.getEditRetryAttempts().sendKeys(Keys.CONTROL + "A");
        setting.getEditRetryAttempts().sendKeys(String.valueOf(beforeRetryAttempt + 1));
        setting.getSaveRetry().click();
        Thread.sleep(3500);
        driver.switchTo().activeElement();
        int afterRetryTime = Integer.parseInt(String.valueOf(setting.getNotificationRetryTimeValue().getText().charAt(0)));
        int afterRetryAttempt = Integer.parseInt(String.valueOf(setting.getNotificationRetryAttemptsValue().getText().charAt(0)));
        softAssert.assertFalse(String.valueOf(beforeRetryTime).equals(String.valueOf(afterRetryTime)),"Retry time is not edited/saved");
        softAssert.assertFalse(String.valueOf(beforeRetryAttempt).equals(String.valueOf(afterRetryAttempt)),"Retry attempts is not edited/saved");
        softAssert.assertAll();
    }

    @Test(groups = {"Custom Fields"}, dependsOnMethods = {"goto_Settings"})
    public void goto_CustomFieldsAndVerify(){
        driver.switchTo().activeElement();
        setting.getSettingMenu().get(2).click();
        softAssert.assertTrue(setting.getCustomFieldsTitle().isDisplayed(), "Custom attributes Settings title is not displayed");
        softAssert.assertEquals(setting.getCustomFieldsTitle().getText(),"Custom attributes","Title of Custom attributes is not matched ");
    }
}





