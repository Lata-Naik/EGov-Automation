package testcore.pages;

import agent.IAgent;
import central.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class TradeLicense extends FullPage {

    public TradeLicense(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
        super(conf, agent, testData);
        assertPageLoad();
    }

    @Override
    public String pageName()
    {
        return TradeLicense.class.getSimpleName();
    }

    public TradeLicense applyTradeLicense() throws Exception{
        switchToIFrame();
        getControl("btnApply").click();
        getControl("txtSelectCity").enterText(getTestData().get("City"));
        getControl("txtSelectCity").enterText(Keys.ENTER);
        getControl("btnSelect").click();
        return this;
    }

    public void fillTradeLicenseForm() throws Exception{
       fillTradeDetails();
       filTradeLocationDetails();
        fillOwnerInformation();
        uploadFiles();
        submitForm();
    }

    public void fillTradeDetails() throws Exception{
        getControl("txtTradeName").enterText(getTestData().get("TradeName"));
        Thread.sleep(10000);
//        getControl("drpSelectStructureType").enterText(getTestData().get("StructureType"));
//        getControl("drpSelectStructureType").enterText(Keys.ENTER);
//        getControl("drpSelectSubStructureType").enterText(getTestData().get("SubStructureType"));
//        getControl("drpSelectSubStructureType").enterText(Keys.ENTER);
        getControl("txtTradeCommencementDate").enterText(getTestData().get("TradeCommencementDate"));
        Thread.sleep(20000);
        getControl("txtUOMValue").enterText(getTestData().get("UOM"));
    }

    public void filTradeLocationDetails()throws Exception{
       // getControl("txtMohalla").enterText(Keys.ENTER);
        Thread.sleep(10000);
        getControl("btnNextStep").click();
    }

    public void fillOwnerInformation()throws Exception{
        Thread.sleep(10000);
        getControl("txtMobileNo").enterText(getTestData().get("MobileNo"));
        getControl("txtName").enterText(getTestData().get("Name"));
        getControl("txtFatherHusbandName").enterText(getTestData().get("FatherHusbandName"));
        Thread.sleep(10000);
        getControl("txtDOB").enterText(getTestData().get("DOB"));
        getControl("txtAddress").enterText(getTestData().get("Address"));
        getControl("btnNextStep").click();
        switchToDefaultFrame();
    }

    public void uploadFiles()throws Exception{
        switchToIFrame();
        getControl("btnUploadIDProof").click();
        Thread.sleep(10000);
       // getAgent().getWebDriver().findElement(By.xpath("//span[text()='UPLOAD FILE']")).sendKeys("/home/ubuntu/Documents/Test/EGov/src/test/resources/uploadFiles/image2.jpeg");
        getControl("btnUploadOwnershipProof").click();
        Thread.sleep(10000);
       // getAgent().getWebDriver().findElement(By.xpath("(//span[text()='UPLOAD FILE'])[2]")).sendKeys("/home/ubuntu/Documents/Test/EGov/src/test/resources/uploadFiles/image1");
      //  getControl("btnUploadIDProof").enterText(getTestData().get("IDProof"));
       // getControl("btnUploadOwnershipProof").enterText(getTestData().get("OwnershipProof"));
        getControl("btnNextStep").click();
    }

    public void submitForm() throws Exception{
        getControl("btnSubmit").click();
    }
}
