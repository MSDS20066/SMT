package mainProject;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.loginPage;
import pageObjects.plansPage;

import java.io.IOException;
import java.util.Properties;

public class plans extends base {

    plansPage plan;
    loginPage log;
    Properties data;
    Actions action;
    SoftAssert softAssert;

    public plans() throws IOException {
        driver = initialize_Driver();
        softAssert = new SoftAssert();
        plan = new plansPage(driver);
        log = new loginPage(driver);
        action = new Actions(driver);
        data = prop;
    }

    @Test(groups = {"Plans", "Add Plan"}, priority = 0)
    public void login() throws InterruptedException {
        driver.get("https://" + data.getProperty("env")  + ".copilot.fabric.inc");
        driver.switchTo().frame(log.getFrame());
        log.get_accountID().sendKeys(data.getProperty("account"));
        log.get_email().sendKeys(data.getProperty("email"));
        log.get_password().sendKeys(data.getProperty("password"));
        log.get_button().click();
        Thread.sleep(1500);
        log.get_SMT().click();
    }

    @Test(dependsOnMethods = {"login"}, priority = 1, groups = {"Plans", "Plans List", "UI"})
    public void verifyPlansHome(){
        driver.switchTo().frame(plan.getFrame());
        softAssert.assertTrue(plan.getAddPlanButton().isEnabled(),"Add Plan button is missing");
        softAssert.assertTrue(plan.getExportPlanButton().isEnabled(),"Export Plans button is missing");
        softAssert.assertTrue(plan.getSearchPlanBox().isEnabled(),"Search Plans input box is missing");
        softAssert.assertTrue(plan.getSearchPlanButton().isEnabled(),"Search Plans button is missing");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"verifyPlansHome"}, groups = {"Plans","Add Plan"})
    public void unsavedChangesWarning(){
        plan.getAddPlanButton().click();
        driver.switchTo().frame(plan.getPlanDescFrame());
        plan.getPlanDescBox().sendKeys(prop.getProperty("planDesc"));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(plan.getFrame());
        plan.getPlanTitle().click();
        plan.getPlanTitle().sendKeys(prop.getProperty("planTitle") + Keys.ENTER);
        plan.getBackToListButton().click();
        driver.switchTo().activeElement();
        softAssert.assertEquals(plan.getUnsavedChangesWarningTitle().getText(),"Unsaved changes", "Unsaved changes warning is not accurately titled");
        plan.getUnsavedChangesOKButton().click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(plan.getFrame());
        softAssert.assertTrue(plan.getAddPlanButton().isEnabled(),"Leave without save in warning modal is not working");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"unsavedChangesWarning"}, groups = {"Plans","Add Plan"})
    public void addAndVerifyPlan() throws InterruptedException {
        plan.getAddPlanButton().click();
        driver.switchTo().frame(plan.getPlanDescFrame());
        plan.getPlanDescBox().sendKeys(prop.getProperty("planDesc"));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(plan.getFrame());
        plan.getPlanTitle().click();
        plan.getPlanTitle().sendKeys(prop.getProperty("planTitle") + Keys.ENTER);
        plan.getPlanFreq().sendKeys(Keys.CONTROL + "A");
        plan.getPlanFreq().sendKeys(prop.getProperty("freq"));
        plan.getPlanFreqTypeDropDown().click();
        plan.getPlanFreqTypes().get(0).click();
        plan.getPlanDiscount().sendKeys(Keys.CONTROL + "A");
        plan.getPlanDiscount().sendKeys(prop.getProperty("planDiscount"));
        plan.getAddPlanProductButton().click();
        driver.switchTo().activeElement();
        Thread.sleep(2500);
        plan.getAddPlanProduct().click();
        String itemID = plan.getAddPlanProductTID().getText();
        plan.getAddPlanProductSave().click();
        Thread.sleep(500);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(plan.getFrame());
        softAssert.assertEquals(plan.getAddPlanAddedProductTID().getText(), itemID, "Selected Item was not added");
        plan.getAddPlanPublishButton().click();
        Thread.sleep(10000);
        if (driver.getCurrentUrl().equals("https://sandbox.copilot.fabric.inc/#/smt/subscriptions/add")){
            plan.getAddPlanPublishButton().click();
        }
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"addAndVerifyPlan"}, groups = {"Plans","Plans List"})
    public void verifyPlansSorting(){
        softAssert.assertEquals(plan.getLastCreatedPlan().getText(), prop.getProperty("planTitle"), "Plan is not created OR Plan is not sorted date wise in descending order");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"verifyPlansSorting"}, groups = {"Plans","Plans List"})
    public void verifyPlansExporting(){
        softAssert.assertTrue(plan.getExportPlanButton().isEnabled(),"Export Plans button is missing");
        plan.getExportPlanButton().click();
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"verifyPlansExporting"}, groups = {"Plans","Plan Detail"})
    public void verifyPlanDetail(){
        plan.getLastCreatedPlan().click();
        softAssert.assertEquals(plan.getPlanDetailTitle().getText(), prop.getProperty("planTitle"), "Plan Title is not accurate");
        softAssert.assertEquals(plan.getPlanDetailDesc().getText(), prop.getProperty("planDesc"), "Plan Desc is not accurate");
        softAssert.assertEquals(plan.getPlanDetailFreq().getText(), "Every "+ prop.getProperty("freq") + " weeks", "Plan Frequency is not accurate");
        softAssert.assertAll();
    }


    @Test(dependsOnMethods = {"verifyPlanDetail"}, groups = {"Plans","Plans List", "Edit Plan"})
    public void verifyPlansEditingUnsavedChanges() throws InterruptedException {
        plan.getEditPlanAndCloseButton().click();
        plan.getPlanTitle().sendKeys(Keys.CONTROL + "A");
        plan.getPlanTitle().sendKeys(prop.getProperty("editPlanTitle"));
        plan.getEditPlanAndCloseButton().click();
        driver.switchTo().activeElement();
        plan.getUnsavedChangesOKButton().click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(plan.getFrame());
        softAssert.assertEquals(plan.getPlanDetailTitle().getText(), prop.getProperty("planTitle"), "Edit close button is saving the information");
        plan.getBackToListButton().click();
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"verifyPlansEditingUnsavedChanges"}, groups = {"Plans","Plans List", "Edit Plan"})
    public void verifyPlansEditingSave() throws InterruptedException {
        plan.getLastCreatedPlan().click();
        plan.getEditPlanAndCloseButton().click();
        plan.getPlanTitle().sendKeys(Keys.CONTROL + "A");
        plan.getPlanTitle().sendKeys(prop.getProperty("editPlanTitle"));
        softAssert.assertTrue(plan.getEditPlanSaveButton().isDisplayed());
        plan.getEditPlanSaveButton().click();
        Thread.sleep(1500);
        softAssert.assertFalse(plan.getEditPlanAndCloseButton().getText().equals("Cancel"), "Save Button is not working.");
        plan.getBackToListButton().click();
        softAssert.assertAll();
    }

}
