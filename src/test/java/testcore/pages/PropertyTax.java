package testcore.pages;

import agent.IAgent;
import central.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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
        Thread.sleep(2000);
        getControl("btnAssessAndPay").click();
        getControl("btnAddNewProperty").waitUntilClickable();
        getControl("btnAddNewProperty").click();
        getControl("financialYear2018_19").waitUntilClickable();
        getControl("financialYear2018_19").click();
        return this;
    }

    public PropertyTax applyPropertyTaxEmployee() throws Exception {
        Thread.sleep(2000);
        getControl("btnAssessNewPropertyEmployee").waitUntilClickable();
        getControl("btnAddNewProperty").click();
        getControl("financialYear2018_19").waitUntilClickable();
        getControl("financialYear2018_19").click();
        return this;
    }

    public PaymentPage fillPropertyTaxFormAndMakeFullPaymentCitizen() throws Exception {
        fillPropertyAddressCitizen();
        fillResidentialAssessmentInformation();
        fillOwnerInformation();
        fillReviewAndFullPaymentCitizen();
        return new PaymentPage(getConfig(), getAgent(), getTestData());

    }

    public PaymentPage fillResidentialPropertyTaxFormAndMakeFullPaymentWithRebateOrChargesEmployee() throws Exception {
        fillPropertyAddressEmployee();
        fillResidentialAssessmentInformation();
        fillOwnerInformation();
        addRebateOrCharges();
        reviewAndPayPageEmployee();
        return new PaymentPage(getConfig(), getAgent(), getTestData());

    }
    public PaymentPage fillCommercialPropertyTaxFormAndMakeFullPaymentWithCashEmployee() throws Exception{
        fillPropertyAddressEmployee();
        fillCommercialAssessmentInformation();
        fillOwnerInformation();
        reviewAndPayPageEmployee();
        return new PaymentPage(getConfig(), getAgent(), getTestData());

    }

    private void addRebateOrCharges() throws Exception {
        scrollDown(20); // #TODO
        Thread.sleep(2000);
        getControl("btnAddRabateOrCharges").click();
        Thread.sleep(2000);
        fillAdditionalCharges(getTestData().get("ChargeType"));
        getControl("btnSubmit").waitUntilClickable();
        Thread.sleep(2000);
        getControl("btnSubmit").click();
        Thread.sleep(2000);
    }

    private void fillAdditionalCharges (String chargeType) throws Exception {
        if(chargeType.equalsIgnoreCase("Rebate")){
            getControl("txtrebateChargeAmount").enterText(getTestData().get("ChargeAmount"));
            getControl("drpAdhocRebateReason").click();
            selectfromDropDown(getTestData().get("rebateReason"));
        }
        if(chargeType.equalsIgnoreCase("Penalty")){
            getControl("txtPenaltyChargeAmount").enterText(getTestData().get("ChargeAmount"));
            getControl("drpAdhocPenaltyReason").click();
            selectfromDropDown(getTestData().get("penaltyReason"));
        }
    }

    public PaymentPage fillPropertyTaxFormAndMakePartialPayment() throws Exception {
        fillPropertyAddressCitizen();
        fillCommercialAssessmentInformation();
        fillOwnerInformation();
        fillReviewAndPartialPaymentCitizen();
        return new PaymentPage(getConfig(), getAgent(), getTestData());
    }

    public void getDataAfterPaymentSucessfull() throws Exception {
        getControl("txtPropertyID").waitUntilVisible();
        String propertyID = getControl("txtPropertyID").getText();
        String assessmentID = getControl("txtAssessmentID").getText();
        int payableAmount = Integer.parseInt(getControl("txtPaybleAmount").getText());
        int amountPaid = Integer.parseInt(getControl("txtAmountPaid").getText());
        int amountDue = Integer.parseInt(getControl("txtAmountDue").getText());
        System.out.println("=========================================================>" + propertyID);
        System.out.println("=========================================================>" + assessmentID);
        System.out.println("=========================================================>" + payableAmount);
        System.out.println("=========================================================>" + amountPaid);
        System.out.println("=========================================================>" + amountDue);
        if ((payableAmount - amountPaid) != amountDue)
            throw new Exception("Calculation is Wrong.");
        getControl("btnFinish").click();
    }

    private void fillReviewAndFullPaymentCitizen() throws Exception {
        scrollDown(20); // #TODO
        Thread.sleep(3000);
//        getControl("chkDeclaration").click(); #TODO
        getControl("rdoFullPayment").click();
        getControl("btnPay").waitUntilClickable();
        getControl("btnPay").click();
    }

    private void PaymentDetailsFullPaymentEmployee() throws Exception {
        scrollDown(1); // #TODO
        Thread.sleep(3000);
        getControl("rdoFullPayment").click();
    }


    private void reviewAndPayPageEmployee() throws Exception {
        scrollDown(20); // #TODO
        Thread.sleep(3000);
        getControl("btnNext").waitUntilClickable();
        getControl("btnNext").click();
    }

    private void fillReviewAndPartialPaymentCitizen() throws Exception {
        scrollDown(20); // #TODO
        Thread.sleep(3000);
//        getControl("chkDeclaration").click(); #TODO
        getControl("rdoPartialPayment").click();
        Thread.sleep(3000);
        getControl("txtAmountToBePaid").click();
        getControl("txtAmountToBePaid").enterText(Keys.CONTROL + "a");
        getControl("txtAmountToBePaid").enterText(Keys.BACK_SPACE);
        Thread.sleep(3000);
        getControl("txtAmountToBePaid").enterText(getTestData().get("AmountToBePaid"));
        getControl("btnPay").waitUntilClickable();
        getControl("btnPay").click();
    }

    private void paymentDetailsPartialPaymentEmployee() throws Exception {
        scrollDown(1); // #TODO
        Thread.sleep(3000);
        getControl("rdoPartialPayment").click();
        Thread.sleep(3000);
        getControl("txtAmountToBePaid").click();
        getControl("txtAmountToBePaid").enterText(Keys.CONTROL + "a");
        getControl("txtAmountToBePaid").enterText(Keys.BACK_SPACE);
        Thread.sleep(3000);
        getControl("txtAmountToBePaid").enterText(getTestData().get("AmountToBePaid"));
    }

    private void fillOwnerInformation() throws Exception {
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


    public void fillPropertyAddressCitizen() throws Exception {
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

    public void fillPropertyAddressEmployee() throws Exception {
        getControl("txtColonyName").enterText(getTestData().get("ColonyName"));
        getControl("txtHouseNo").enterText(getTestData().get("HouseNo"));
        getControl("txtStreetName").enterText(getTestData().get("StreetName"));
        getControl("txtPincode").enterText(getTestData().get("Pincode"));
        getControl("drpMohalla").enterText(getTestData().get("Mohalla"));
        selectOptionFromDropDownByEnter(getTestData().get("Mohalla"));
        getControl("txtOldPropertyID").enterText(getTestData().get("ExistingPropertyID"));
        getControl("btnNext").click();
    }

    private void fillResidentialAssessmentInformation() throws Exception {
        getControl("drpPropertyUsageType").click();
        selectfromDropDown(getTestData().get("PropertyUsageType"));
        Thread.sleep(1000);
        scrollDown();
        getControl("drpPropertyType").click();
        selectfromDropDown(getTestData().get("PropertyType"));
        Thread.sleep(1000);
        getControl("drpOccupancy").click();
        selectfromDropDown(getTestData().get("Occupancy"));
        getControl("txtResidentialFlatBuildUpArea").enterText(getTestData().get("BuildUpArea"));
        Thread.sleep(3000);
        getControl("txtResidentialFlatBuildUpArea").enterText(Keys.CONTROL + "a");
        getControl("txtResidentialFlatBuildUpArea").enterText(Keys.BACK_SPACE);
        Thread.sleep(3000);
        getControl("txtResidentialFlatBuildUpArea").enterText(getTestData().get("BuildUpArea"));
        Thread.sleep(2000);
        scrollDown();
        Thread.sleep(1000);
        getControl("drpFloorName").click();
        selectfromDropDown(getTestData().get("FloorName"));
        Thread.sleep(1000);
        getControl("btnNext").click();
    }

    private void fillCommercialAssessmentInformation() throws Exception {
        getControl("drpPropertyUsageType").click();
        selectfromDropDown(getTestData().get("PropertyUsageType"));
        Thread.sleep(1000);
        getControl("drpPropertyType").click();
        selectfromDropDown(getTestData().get("PropertyType"));
        Thread.sleep(1000);
        scrollDown();
        getControl("drpSubUsageType").click();
        selectfromDropDown(getTestData().get("SubUsageType"));
        Thread.sleep(1000);
        getControl("drpOccupancy").click();
        selectfromDropDown(getTestData().get("Occupancy"));
        scrollDown();
        Thread.sleep(1000);
        getControl("txtCommercialFlatBuildUpArea").enterText(getTestData().get("BuildUpArea"));
        scrollDown();
        Thread.sleep(1000);
        getControl("drpFloorName").click();
        selectfromDropDown(getTestData().get("FloorName"));
        Thread.sleep(1000);
        getControl("btnNext").click();
    }

    public PaymentPage getDataAfterSucessfulPaymentAndReassessChangeBuildUpArea() throws Exception {
        getControl("txtPropertyID").waitUntilVisible();
        String propertyID = getControl("txtPropertyID").getText();
        String assessmentID = getControl("txtAssessmentID").getText();
        int payableAmount = Integer.parseInt(getControl("txtPaybleAmount").getText());
        int amountPaid = Integer.parseInt(getControl("txtAmountPaid").getText());
        int amountDue = Integer.parseInt(getControl("txtAmountDue").getText());
        System.out.println("=========================================================>" + propertyID);
        System.out.println("=========================================================>" + assessmentID);
        System.out.println("=========================================================>" + payableAmount);
        System.out.println("=========================================================>" + amountPaid);
        System.out.println("=========================================================>" + amountDue);
        if ((payableAmount - amountPaid) != amountDue)
            throw new Exception("Calculation is Wrong.");
        getControl("btnFinish").click();
        reassessPropertyEmployee(propertyID);
        ReassessChangeBuildUpArea();
        return new PaymentPage(getConfig(), getAgent(), getTestData());
    }

    public PaymentPage getDataAfterSucessfulPaymentAndAssessAndPay() throws Exception {
        getControl("txtPropertyID").waitUntilVisible();
        String propertyID = getControl("txtPropertyID").getText();
        String assessmentID = getControl("txtAssessmentID").getText();
        int payableAmount = Integer.parseInt(getControl("txtPaybleAmount").getText());
        int amountPaid = Integer.parseInt(getControl("txtAmountPaid").getText());
        int amountDue = Integer.parseInt(getControl("txtAmountDue").getText());
        System.out.println("=========================================================>" + propertyID);
        System.out.println("=========================================================>" + assessmentID);
        System.out.println("=========================================================>" + payableAmount);
        System.out.println("=========================================================>" + amountPaid);
        System.out.println("=========================================================>" + amountDue);
        if ((payableAmount - amountPaid) != amountDue)
            throw new Exception("Calculation is Wrong.");
        getControl("btnFinish").click();
        assessAndPayEmployee(propertyID);
        return new PaymentPage(getConfig(), getAgent(), getTestData());
    }

    private void ReassessChangeBuildUpArea() throws Exception {
        getControl("btnNext").click();
        getControl("txtResidentialFlatBuildUpArea").enterText(Keys.CONTROL + "a");
        getControl("txtResidentialFlatBuildUpArea").enterText(Keys.BACK_SPACE);
        Thread.sleep(3000);
        getControl("txtResidentialFlatBuildUpArea").enterText(getTestData().get("ReassessBuildUpArea"));
        getControl("btnNext").click();
        getControl("btnNext").click();
        getControl("btnNext").click();
        return;

    }


    public void reassessPropertyEmployee(String propertyID) throws Exception {
        getControl("btnSearchProperty").click();
        getControl("txtPropertyTaxUniqueID").enterText(propertyID);
        getControl("btnSearch").click();
        Thread.sleep(3000);
        getControl("btnView").click();
        Thread.sleep(3000);
        getControl("btnAssessmentHistory").click();
        scrollDown(10);
        getControl("drpSelectAction").click();
        getControl("btnReassess").click();
        Thread.sleep(9000);
        getControl("btnGoBack").click();
        Thread.sleep(2000);
        getControl("btnGoBack").click();
        Thread.sleep(2000);
        getControl("btnGoBack").click();
        Thread.sleep(2000);
    }

    public void assessAndPayEmployee(String propertyID) throws Exception {
        getControl("btnSearchProperty").click();
        getControl("txtPropertyTaxUniqueID").enterText(propertyID);
        getControl("btnSearch").click();
        Thread.sleep(3000);
        getControl("btnView").click();
        Thread.sleep(3000);
        getControl("btnAssess&Pay").click();
        Thread.sleep(2000);
        getControl("financialYear2017_18").click();
        Thread.sleep(5000);
        getControl("btnNext").click();

    }


    public void selectfromDropDown(String drpValue) throws Exception {
        String xpathCity = "//span[@class='menu-class']/*/*/div[text()='" + drpValue + "']";
//        getControl(xpathCity).click();
        getAgent().getWebDriver().findElement(By.xpath(xpathCity)).click();
    }

}
