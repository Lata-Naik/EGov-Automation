package testcore.pages;

import agent.IAgent;
import central.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

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

    public PaymentPage fillTradeLicenseForm() throws Exception{
        fillTradeDetails();
        filTradeLocationDetails();
        fillOwnerInformation();
        uploadFiles();
        submitForm();
        return new PaymentPage(getConfig(),getAgent(),getTestData());
    }

    public void fillTradeDetails() throws Exception{
        getControl("txtTradeName").enterText(getTestData().get("TradeName"));
        Thread.sleep(10000);
//        getControl("drpSelectStructureType").click();
//        selectOptionFromDropDownList(getTestData().get("StructureType"));
//        getControl("drpSelectSubStructureType").click();
//        selectOptionFromDropDownList(getTestData().get("SubStructureType"));
        getControl("txtTradeCommencementDate").enterText(getTestData().get("TradeCommencementDate"));
        Thread.sleep(10000);
        getControl("txtUOMValue").enterText(getTestData().get("UOM"));
    }

    public void filTradeLocationDetails()throws Exception{
        Thread.sleep(10000);
//        getControl("txtTradeCategory").click();
//        selectOptionFromDropDownList(getTestData().get("TradeCategory"));
//        getControl("txtTradeType").click();
//        selectOptionFromDropDownList(getTestData().get("TradeType"));
//        getControl("txtTradeSubType").click();
//        selectOptionFromDropDownByEnter(getTestData().get("TradeSubType"));
//        getControl("txtAccessories").click();
//        selectOptionFromDropDownList(getTestData().get("Accessories"));
//        getControl("txtMohalla").click();
//        selectOptionFromDropDownByEnter(getTestData().get("Mohalla"));
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
        Thread.sleep(5000);
       // getControl("btnUploadProof").enterText(getTestData().get("IDProof"));
        Thread.sleep(10000);
       // getControl("icnUploadClose","isVisible");
       // getControl("btnUploadOwnershipProof").enterText(getTestData().get("OwnershipProof"));
        Thread.sleep(1000);
        getControl("btnNextStep").click();
    }

    public void submitForm() throws Exception{
        getControl("btnSubmit").click();
    }

    public TradeLicense approveTradeLicense() throws Exception{
        selectApplicationToApprove();
        return this;
    }

    public void selectApplicationToApprove() throws Exception{
        switchToIFrame();
//        getControl("txtLicenseApplicationNo").enterText(getTestData().get("ApplicationNumber"));
//        getControl("txtTradeLicenseNo").enterText(getTestData().get("TradeLicenseNo"));
//        getControl("txtOwnerMobileNo").enterText(getTestData().get("OwnerMobileNumber"));
        getControl("txtApplicationStatus").click();
        selectOptionFromDropDownList("PAID");
//        getControl("txtApplicationFromDate").enterText(getTestData().get("ApplicationFromDate"));
//        getControl("txtApplicationToDate").enterText(getTestData().get("ApplicationToDate"));
        Thread.sleep(2000);
        getControl("btnSearch").click();
        selectApplicationByNumber(getTestData().get("ApplicationNumber"));
        approveApplication();
    }
    public void selectApplicationByNumber(String applicationNo) throws Exception{
        String applicationNoXpath = "//span[contains(text(),'"+applicationNo+"')]";
        getAgent().getWebDriver().findElement(By.xpath(applicationNoXpath)).click();
    }

    public void approveApplication()throws Exception{
        getControl("btnApprove").click();
        getControl("txtApprovalComments").enterText(getTestData().get("ApprovalComments"));
      //  getControl("TradeLicense").enterText(getTestData().get("UploadFiles"));
        getControl("btnApprove").click();
    }
    public void rejectApplication()throws Exception{
        getControl("btnReject").click();
    }
    public void isApplicationApproved()throws Exception{
        boolean applicationApproved = getControl("txtApplicationApproved").getText().equalsIgnoreCase("Trade License Approved Successfully");
        logger.info(getControl("txtApplicationApproved").getText());
        Assert.assertTrue(applicationApproved, "Application not approved");
    }
}
