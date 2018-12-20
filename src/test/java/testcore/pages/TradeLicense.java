package testcore.pages;

import agent.IAgent;
import central.Configuration;
import org.openqa.selenium.Keys;

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
        getControl("drpSelectStructureType").click();
        selectOptionFromDropDownList(getTestData().get("StructureType"));
        getControl("drpSelectSubStructureType").click();
        selectOptionFromDropDownList(getTestData().get("SubStructureType"));
        getControl("txtTradeCommencementDate").enterText(getTestData().get("TradeCommencementDate"));
        getControl("txtUOMValue").enterText(getTestData().get("UOM"));
    }

    public void filTradeLocationDetails()throws Exception{
        getControl("txtTradeCategory").click();
        selectOptionFromDropDownList(getTestData().get("TradeCategory"));
        getControl("txtTradeType").click();
        selectOptionFromDropDownList(getTestData().get("TradeType"));
        getControl("txtTradeSubType").click();
        selectOptionFromDropDownByEnter(getTestData().get("TradeSubType"));
        getControl("txtAccessories").click();
        selectOptionFromDropDownList(getTestData().get("Accessories"));
        getControl("txtMohalla").click();
        selectOptionFromDropDownByEnter(getTestData().get("Mohalla"));
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
        Thread.sleep(1000);
        getControl("btnUploadProof").enterText(getTestData().get("IDProof"));
        Thread.sleep(5000);
        getControl("icnUploadClose","isVisible");
        getControl("btnUploadOwnershipProof").enterText(getTestData().get("OwnershipProof"));
        Thread.sleep(1000);
        getControl("btnNextStep").click();
    }

    public void submitForm() throws Exception{
        getControl("btnSubmit").click();
    }
}
