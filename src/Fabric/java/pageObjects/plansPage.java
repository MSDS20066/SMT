package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class plansPage {

    public WebDriver driver;
    By frame = By.xpath("//div[@id='root']/div/div/div/iframe");
    By addPlan = By.xpath("//div[@class='contentWrapper']/div/div/main/div[1]/div[1]/div[1]/button[1]");
    By exportPlan = By.xpath("//div[@class='contentWrapper']/div/div/main/div[1]/div[1]/div[1]/button[2]");
    By searchPlanBox = By.xpath("//div[@class='search']/div/div/div/div/label/div/input");
    By searchPlanButton = By.xpath("//div[@class='search']/button");
    By addPlanTitle = By.xpath("//section[@class='content']/div[1]/div[1]/div[2]/div[1]/div[1]/label/div/input");
    By addPlanDescFrame = By.xpath("//section[@class='content']/div[1]/div[1]/div[2]/div[2]/div[2]/div/div/iframe");
    By addPlanDesc = By.tagName("p");
    By addPlanFreq = By.xpath("//section[@class='content']/div[1]/div[2]/div[3]/div/div[2]/div[1]/div[1]/div/label/div/input");
    By addPlanFreqTypeDropDown = By.xpath("//section[@class='content']/div[1]/div[2]/div[3]/div/div[2]/div[1]/div[2]/div/div/div[2]");
    By addPlanFreqTypes = By.xpath("//section[@class='content']/div[1]/div[2]/div[3]/div/div[2]/div[1]/div[2]/div/div/div[3]/ul/li");
    By addPlanDiscount = By.xpath("//section[@class='content']/div[1]/div[2]/div[3]/div/div[2]/div[2]/div/div/label/div/input");
    By addPlanProductButton = By.xpath("//button[text()='Add Products']");
    By addPlanProducts = By.xpath("//div[@class='table']/div/table/tbody/tr[1]/td[1]/span/label/span");
    By addPlanProductsTID = By.xpath("//div[@class='table']/div/table/tbody/tr[1]/td[6]");
    By addPlanProductsSaveButton = By.xpath("//div[@class='modal__buttons']/button[2]");
    By addPlanProductsCancelButton = By.xpath("//button[text()='Cancel']");
    By addPlanAddedProductTID = By.xpath("//div[@class='table']/div/table/tbody/tr[1]/td[3]");
    By addPlanPublishButton = By.xpath("//section[@class='header']/div[2]/div/button");
    By addPlanBackToListButton = By.className("backButton");
    By unsavedChangesWarningTitle = By.className("cm-heading");
    By unsavedChangesOkButton = By.xpath("//div[@class='modal_content']/div[2]/button");
    By planListLastAdded = By.xpath("//div[@class='table']/div/table/tbody/tr[1]/td[2]");
    By editPlanAndCancelButton = By.xpath("//section[@class='header']/div[2]/div/button[1]");
    By editPlanSaveButton = By.xpath("//section[@class='header']/div[2]/div/button[2]");
    By planDetailTitle = By.xpath("//div[@class='col-left']/div[1]/div[2]/div/p");
    By planDetailDesc = By.xpath("//div[@class='col-left']/div[1]/div[2]/div/div/p");
    By planDetailFreq = By.xpath("//div[@class='col-left']/div[2]/div[2]/div[1]/div[2]/p");


    public plansPage(WebDriver driver){
        this.driver = driver;
    }
    public WebElement getFrame(){
        return driver.findElement(frame);
    }
    public WebElement getAddPlanButton() { return driver.findElement(addPlan); }
    public WebElement getExportPlanButton() { return driver.findElement(exportPlan); }
    public WebElement getSearchPlanBox() { return driver.findElement(searchPlanBox); }
    public WebElement getSearchPlanButton() { return driver.findElement(searchPlanButton); }
    public WebElement getPlanTitle() { return driver.findElement(addPlanTitle); }
    public WebElement getPlanDescFrame() { return driver.findElement(addPlanDescFrame); }
    public WebElement getPlanDescBox() { return driver.findElement(addPlanDesc); }
    public WebElement getPlanFreq() { return driver.findElement(addPlanFreq); }
    public WebElement getPlanFreqTypeDropDown() { return driver.findElement(addPlanFreqTypeDropDown); }
    public List<WebElement> getPlanFreqTypes() { return driver.findElements(addPlanFreqTypes); }
    public WebElement getPlanDiscount() { return driver.findElement(addPlanDiscount); }
    public WebElement getAddPlanProductButton() { return driver.findElement(addPlanProductButton); }
    public WebElement getAddPlanProduct() { return driver.findElement(addPlanProducts); }
    public WebElement getAddPlanProductTID() { return driver.findElement(addPlanProductsTID); }
    public WebElement getAddPlanProductSave() { return driver.findElement(addPlanProductsSaveButton); }
    public WebElement getAddPlanProductCancel() { return driver.findElement(addPlanProductsCancelButton); }
    public WebElement getAddPlanAddedProductTID() { return driver.findElement(addPlanAddedProductTID); }
    public WebElement getAddPlanPublishButton() { return driver.findElement(addPlanPublishButton); }
    public WebElement getBackToListButton() { return driver.findElement(addPlanBackToListButton); }
    public WebElement getUnsavedChangesWarningTitle() { return driver.findElement(unsavedChangesWarningTitle); }
    public WebElement getUnsavedChangesOKButton() { return driver.findElement(unsavedChangesOkButton); }
    public WebElement getLastCreatedPlan() { return driver.findElement(planListLastAdded); }
    public WebElement getEditPlanAndCloseButton() { return driver.findElement(editPlanAndCancelButton); }
    public WebElement getEditPlanSaveButton() { return driver.findElement(editPlanSaveButton); }
    public WebElement getPlanDetailTitle() { return driver.findElement(planDetailTitle); }
    public WebElement getPlanDetailDesc() { return driver.findElement(planDetailDesc); }
    public WebElement getPlanDetailFreq() { return driver.findElement(planDetailFreq); }
}
