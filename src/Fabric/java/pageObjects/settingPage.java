package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class settingPage {

    public WebDriver driver;
    By frame = By.xpath("//div[@id='root']/div/div/div/iframe");
    By settings = By.xpath("//a[text()='Settings']");
    By title = By.xpath("//div[@class='contentWrapper']/div/div[1]");
    By titleNot = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[1]/div[1]");
    By titleCustomFields = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div/div/div[1]/div");
    By detailNot = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[1]/div[2]");
    By settingNotifications = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[2]/div[2]");
    By settingMenu = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[1]/div");
    By settingTitle = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[1]/div[1]");
    By addWebhookButton = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[1]/button");
    By webhooks = By.className("row");
    By webhookNames = By.xpath("//div[@class='row']/div[1]");
    By webhookURLs = By.xpath("//div[@class='row']/div[2]");
    By webhookViews = By.xpath("//div[@class='right_content']/div/div[3]");
    By webhookModalName = By.xpath("//div[@class='modal_content']/form/div[2]/div[2]/div/label");
    By webhookNameList = By.xpath("//div[@class='modal_content']/form/div[2]/div[3]/ul/li");
    By webhookModalLink = By.xpath("//div[@class='modal_content']/form/div[4]/div/label/div/input");
    By webhookDeleteButton = By.xpath("//div[@class='modal_content']/form/div[5]/div/span");
    By webhookCancelButton = By.xpath("//div[@class='modal_content']/form/div[5]/button[1]");
    By webhookSaveButton = By.xpath("//div[@class='modal_content']/form/div[5]/button[2]");
    By notificationBeforeChargeTitle = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/div[1]");
    By notificationRetryTimeTitle = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/div[1]");
    By notificationRetryAttemptsTitle = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/div[1]");
    By notificationBeforeChargeValue = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/div[2]/div[1]");
    By notificationRetryTimeValue = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[1]");
    By notificationRetryAttemptsValue = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/div[2]/div[1]");
    By notificationBeforeChargeEdit = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/div[2]/div[2]");
    By notificationRetryTimeEdit = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]");
    By notificationRetryAttemptsEdit = By.xpath("//div[@class='contentWrapper']/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/div[2]/div[2]");
    By editBeforeCharge = By.xpath("//div[@class='input__wrapper']/input");
    By saveBeforeCharge = By.xpath("//div[@class='modal_content']/div[2]/div[4]/button[2]");
    By editTime = By.xpath("//div[@class='input__wrapper']/input");
    By saveRetry = By.xpath("//div[@class='modal_content']/div[2]/div[5]/button[2]");


    public settingPage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement getSettings(){
        return driver.findElement(settings);
    }

    public WebElement getFrame(){
        return driver.findElement(frame);
    }

    public WebElement getTitle(){
        return driver.findElement(title);
    }

    public List<WebElement> getSettingMenu(){
        return driver.findElements(settingMenu);
    }

    public WebElement getSettingTitle(){
        return driver.findElement(settingTitle);
    }

    public WebElement getAddWebhookButton(){
        return driver.findElement(addWebhookButton);
    }

    public List<WebElement> getWebhooks(){
        return driver.findElements(webhooks);
    }

    public List<WebElement> getWebhookTitle(){
        return driver.findElements(webhookNames);
    }

    public List<WebElement> getWebhookURL(){
        return driver.findElements(webhookURLs);
    }

    public List<WebElement> getWebhookView(){
        return driver.findElements(webhookViews);
    }

    public WebElement getWebhookModalName(){
        return driver.findElement(webhookModalName);
    }

    public WebElement getWebhookModalLink(){
        return driver.findElement(webhookModalLink);
    }

    public WebElement getWebhookDeleteButton(){
        return driver.findElement(webhookDeleteButton);
    }

    public WebElement getWebhookSaveButton(){
        return driver.findElement(webhookSaveButton);
    }

    public WebElement getWebhookCancelButton(){
        return driver.findElement(webhookCancelButton);
    }

    public List<WebElement> getWebhookNameList() { return driver.findElements(webhookNameList); }

    public WebElement getNotificationTitle() { return driver.findElement(titleNot); }

    public WebElement getNotificationDetail() { return driver.findElement(detailNot); }

    public WebElement getNotificationSettings() { return driver.findElement(settingNotifications); }

    public WebElement getNotificationBeforeChargeTitle() { return driver.findElement(notificationBeforeChargeTitle); }
    public WebElement getNotificationBeforeChargeValue() { return driver.findElement(notificationBeforeChargeValue); }
    public WebElement getNotificationBeforeChargeEdit() { return driver.findElement(notificationBeforeChargeEdit); }

    public WebElement getNotificationRetryTimeTitle() { return driver.findElement(notificationRetryTimeTitle); }
    public WebElement getNotificationRetryTimeValue() { return driver.findElement(notificationRetryTimeValue); }
    public WebElement getNotificationRetryTimeEdit() { return driver.findElement(notificationRetryTimeEdit); }

    public WebElement getNotificationRetryAttemptsTitle() { return driver.findElement(notificationRetryAttemptsTitle); }
    public WebElement getNotificationRetryAttemptsValue() { return driver.findElement(notificationRetryAttemptsValue); }
    public WebElement getNotificationRetryAttemptsEdit() { return driver.findElement(notificationRetryAttemptsEdit); }

    public WebElement getEditBeforeCharge() { return driver.findElement(editBeforeCharge); }
    public WebElement getSaveBeforeCharge() { return driver.findElement(saveBeforeCharge); }

    public WebElement getEditRetryTime() { return  driver.findElements(editTime).get(0); }
    public WebElement getEditRetryAttempts() { return  driver.findElements(editTime).get(1); }
    public WebElement getSaveRetry() { return driver.findElement(saveRetry); }

    public WebElement getCustomFieldsTitle() { return driver.findElement(titleCustomFields); }
}
