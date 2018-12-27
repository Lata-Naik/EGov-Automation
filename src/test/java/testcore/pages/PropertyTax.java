package testcore.pages;

import agent.IAgent;
import central.Configuration;
import org.openqa.selenium.By;

import java.util.Map;

public class PropertyTax extends FullPage {

    public PropertyTax(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
        super(conf, agent, testData);
        assertPageLoad();
    }

    @Override
    public String pageName() {
        return PropertyTax.class.getSimpleName();
    }

    public PropertyTax applyPropertyTaxCitizen() throws Exception {
        getControl("btnAssessAndPay").waitUntilClickable();
        getControl("btnAssessAndPay").click();
        getControl("btnAddNewProperty").waitUntilClickable();
        getControl("btnAddNewProperty").click();
        getControl("financialYear2018_19").waitUntilClickable();
        getControl("financialYear2018_19").click();
        return this;
    }
    public PaymentPage fillPropertyTaxForm()throws Exception{
        fillPropertyAddress();
        fillAssessmentInformation();
        fillOwnerInformation();
        fillReviewAndPay();
//        getDataAfterPaymentSucessfull();
        return new PaymentPage(getConfig(),getAgent(),getTestData());

    }

    public void getDataAfterPaymentSucessfull() throws Exception{
        getControl("txtPropertyID").waitUntilVisible();
        String propertyID = getControl("txtPropertyID").getText();
        String assessmentID = getControl("txtAssessmentID").getText();
        int payableAmount = Integer.parseInt(getControl("txtPaybleAmount").getText());
        int amountPaid = Integer.parseInt(getControl("txtAmountPaid").getText());
        int amountDue = Integer.parseInt(getControl("txtAmountDue").getText());
        System.out.println("=========================================================>"+propertyID);
        System.out.println("=========================================================>"+assessmentID);
        System.out.println("=========================================================>"+payableAmount);
        System.out.println("=========================================================>"+amountPaid);
        System.out.println("=========================================================>"+amountDue);
        getControl("btnFinish").click();
    }

    private void fillReviewAndPay() throws Exception{

        scrollDown(20); // #TODO
        Thread.sleep(3000);
//        getControl("chkDeclaration").click(); #TODO
        getControl("btnPay").waitUntilClickable();
        getControl("btnPay").click();
    }

    private void fillOwnerInformation() throws Exception{
        getControl("txtOwnerName").waitUntilVisible();
        getControl("txtOwnerName").enterText(getTestData().get("OwnerName"));
//        getControl("rdoGenderFemale").click(); #TODO
        getControl("txtMobileNo").waitUntilVisible();
        getControl("txtMobileNo").enterText(getTestData().get("MobileNo"));
        getControl("txtGuardianName").waitUntilVisible();
        getControl("txtGuardianName").enterText(getTestData().get("GuardianName"));
        getControl("drpOwnerRelationship").waitUntilClickable();
        getControl("drpOwnerRelationship").click();
        selectfromDropDown(getTestData().get("OwnerRelationship"));
        Thread.sleep(1000);
        getControl("drpOwnerCategory").click();
        selectfromDropDown(getTestData().get("OwnerCategory"));
//        getControl("txtEMailID").enterText(getTestData().get("EMailID")); #TODO
        scrollDown();
        Thread.sleep(1000);
//        getControl("chkSameAsPropertyAddres").click();#TODO
        getControl("btnNext").click();
    }


    public void fillPropertyAddress()throws Exception{
        getControl("drpDwnCity").click();
        selectfromDropDown(getTestData().get("PropertyCity"));
        getControl("txtColonyName").enterText(getTestData().get("ColonyName"));
        getControl("txtHouseNo").enterText(getTestData().get("HouseNo"));
        getControl("txtStreetName").enterText(getTestData().get("StreetName"));
        getControl("txtPincode").enterText(getTestData().get("Pincode"));
        getControl("drpMohalla").click();
        selectOptionFromDropDownByEnter(getTestData().get("Mohalla"));
        getControl("txtOldPropertyID").enterText(getTestData().get("ExistingPropertyID"));
        getControl("btnNext").click();
    }

    private void fillAssessmentInformation() throws Exception{
        getControl("drpPropertyUsageType").click();
        selectfromDropDown(getTestData().get("PropertyUsageType"));
        Thread.sleep(1000);
        getControl("drpPropertyType").click();
        selectfromDropDown(getTestData().get("PropertyType"));
        Thread.sleep(1000);
        getControl("drpOccupancy").click();
        selectfromDropDown(getTestData().get("Occupancy"));
        getControl("txtFlatBuildUpArea").enterText(getTestData().get("BuildUpArea"));
        Thread.sleep(9000);
        scrollDown();
        Thread.sleep(1000);
        getControl("drpFloorName").click();
        selectfromDropDown(getTestData().get("FloorName"));
        Thread.sleep(1000);
        getControl("btnNext").click();
    }


    public void selectfromDropDown(String drpValue) throws Exception{
        String xpathCity = "//span[@class='menu-class']/*/*/div[text()='"+drpValue+"']";
        getAgent().getWebDriver().findElement(By.xpath(xpathCity)).click();
    }

}
