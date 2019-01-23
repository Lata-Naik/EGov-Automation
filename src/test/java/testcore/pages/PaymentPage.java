package testcore.pages;

import agent.IAgent;
import central.Configuration;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Map;

public class PaymentPage extends FullPage {

    public PaymentPage(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
        super(conf, agent, testData);
        assertPageLoad();
    }

    @Override
    public String pageName()
    {
        return PaymentPage.class.getSimpleName();
    }

    public PaymentPage makePaymentTL() throws Exception{
        getControl("btnProceedToPayment").click();
        getControl("btnMakePayment").click();
        logger.info("Amount to be paid "+getControl("txtAmount").getText());
        getControl("txtMasterCard").waitUntilClickable();
        getControl("txtMasterCard").click();
        getControl("txtCardNumber").enterText(getTestData().get("CardNumber"));
        getControl("txtCardMonth").enterText(getTestData().get("CardMonth"));
        getControl("txtCardYear").enterText(getTestData().get("CardYear"));
        getControl("btnPayNow").click();
        getControl("btnSubmit").click();
        Thread.sleep(15000);
        return this;
    }
    public PropertyTax PTmakePayment() throws Exception{
        getControl("txtMasterCard").waitUntilVisible();
        getControl("txtMasterCard").click();
        getControl("txtCardNumber").enterText(getTestData().get("CardNumber"));
        getControl("txtCardMonth").enterText(getTestData().get("CardMonth"));
        getControl("txtCardYear").enterText(getTestData().get("CardYear"));
        getControl("btnPayNow").click();
        getControl("btnSubmit").waitUntilVisible();
        getControl("btnSubmit").click();
        Thread.sleep(15000);
        return new PropertyTax(getConfig(),getAgent(),getTestData());
    }

    public PaymentPage makePaymentCounterEmployeeTL() throws Exception{
        logger.info("Amount to be paid "+getControl("txtAmount").getText());
        if(getTestData().get("ModeOfPayment").equalsIgnoreCase("Cash")){
//            getControl("txtCash").click();
            payByCashTL();
            Thread.sleep(10000);
        }
        else if(getTestData().get("ModeOfPayment").equalsIgnoreCase("Cheque")){
            getControl("txtCheque").click();
            payByChequeTL();
        }
        else if(getTestData().get("ModeOfPayment").equalsIgnoreCase("DD")){
            getControl("txtDD").click();
            payByDDTL();
        }
        else {
            getControl("txtCreditDebitCard").click();
            payByCreditDebitCardTL();
        }
        Thread.sleep(1000);
        getControl("btnSubmitTL").click();
        return this;
    }

    public PropertyTax makePaymentCounterEmployeePT() throws Exception{
        getControl("drpPaymentMode").scrollTo();
        selectfromDropDown(getTestData().get("ModeOfPayment"));
        Thread.sleep(2000);
        if(getTestData().get("ModeOfPayment").equalsIgnoreCase("Cash")){
            payByCashPT();
            Thread.sleep(2000);
        }
        else if(getTestData().get("ModeOfPayment").equalsIgnoreCase("Cheque")){
            payByChequePT();
        }
        else if(getTestData().get("ModeOfPayment").equalsIgnoreCase("DD")){
            payByDDPT();
        }
        else {
            getControl("txtCreditDebitCard").click();
            payByCreditDebitCardPT();
        }
        Thread.sleep(2000);
        getControl("btnPayPT").click();
        Thread.sleep(10000);
        return new PropertyTax(getConfig(),getAgent(),getTestData());
    }



    public void payByCashTL()throws Exception{
        addPayerDetails();
    }
    public void payByCashPT()throws Exception{
        addPayerDetailsPT();
    }
    public void payByChequeTL()throws Exception{
        getControl("drpPaidByChequeTL").click();
        selectOptionFromDropDownList(getTestData().get("PaidBy"));
        Thread.sleep(2000);
        getControl("txtPayerNameChequeTL").enterText(getTestData().get("PayerName"));
        getControl("txtPayerMobileNumberChequeTL").enterText(getTestData().get("PayerMobileNumber"));
        getControl("txtChequeNumberTL").enterText(getTestData().get("ChequeNumber"));
        getControl("txtChequeDateTL").enterText(getTestData().get("ChequeDate"));
        scrollDown(2);
        getControl("txtChequeIFSCTL").enterText(getTestData().get("ChequeIFSC"));
        getControl("icnSearchIFSCChequeTL").click();
    }
    public void payByChequePT()throws Exception{
        addPayerDetailsPT();
        getControl("txtChequeNumberPT").enterText(getTestData().get("ChequeNumber"));
        getControl("txtChequeIFSCPT").enterText(getTestData().get("ChequeIFSC"));
        getControl("txtChequeDatePT").click();
        Thread.sleep(2000);
        getControl("btnChequeDateOKPT").click();
        Thread.sleep(1000);
    }
    public void payByDDTL()throws Exception{
        getControl("drpPaidByDDTL").click();
        selectOptionFromDropDownList(getTestData().get("PaidBy"));
        Thread.sleep(5000);
        getControl("txtPayerNameDDTL").enterText(getTestData().get("PayerName"));
        getControl("txtPayerMobileNumberDDTL").enterText(getTestData().get("PayerMobileNumber"));
        getControl("txtDDNumber").enterText(getTestData().get("DDNumber"));
        getControl("txtDDDate").enterText(getTestData().get("DDDate"));
        scrollDown(2);
        getControl("txtDDIFSC").enterText(getTestData().get("DDIFSC"));
        getControl("icnSearchIFSCDDTL").click();
    }
    public void payByDDPT()throws Exception{
        addPayerDetailsPT();
        getControl("txtDDNumberPT").enterText(getTestData().get("DDNumber"));
        getControl("txtDDDatePT").enterText(getTestData().get("DDDate"));
        getControl("txtDDIFSCPT").enterText(getTestData().get("DDIFSC"));
    }
    public void payByCreditDebitCardTL()throws Exception{
        getControl("drpPaidByCardTL").click();
        selectOptionFromDropDownList(getTestData().get("PaidBy"));
        Thread.sleep(2000);
        getControl("txtPayerNameCardTL").enterText(getTestData().get("PayerName"));
        getControl("txtPayerMobileNumberCardTL").enterText(getTestData().get("PayerMobileNumber"));
        getControl("txtCardNumberTL").enterText(getTestData().get("CardNumber"));
        getControl("txtTransactionNumber").enterText(getTestData().get("TransactionNumber"));
        getControl("txtRepeatTransactionNumber").enterText(getTestData().get("TransactionNumber"));
    }
    public void payByCreditDebitCardPT()throws Exception{
        addPayerDetailsPT();
        getControl("txtCardNumberPT").enterText(getTestData().get("CardNumber"));
        getControl("txtTransactionNumberPT").enterText(getTestData().get("TransactionNumber"));
        getControl("txtRepeatTransactionNumberPT").enterText(getTestData().get("TransactionNumber"));
    }

    public void addPayerDetails() throws Exception{
        getControl("drpPaidByCashTL").click();
        selectOptionFromDropDownList(getTestData().get("PaidBy"));
        Thread.sleep(5000);
        getControl("txtPayerNameCashTL").enterText(getTestData().get("PayerName"));
        getControl("txtPayerMobileNumberCashTL").enterText(getTestData().get("PayerMobileNumber"));
    }
    public void addPayerDetailsPT() throws Exception{
        Thread.sleep(1000);
        scrollUp(1);
        Thread.sleep(1000);
        getControl("drpPaidByPT").scrollTo();
        Thread.sleep(1000);
        selectfromDropDown(getTestData().get("PaidBy"));
        Thread.sleep(1000);
        getControl("txtPayerNamePT").enterText(getTestData().get("PayerName"));
        getControl("txtPayerMobileNumberPT").enterText(getTestData().get("PayerMobileNumber"));
    }

    public PaymentPage isApplicationPlaced() throws Exception{
        boolean applicationApproved = getControl("txtSuccessMessage").getText().equalsIgnoreCase("Payment has been collected successfully!");
        logger.info(getControl("txtApplicationNumber").getText());
        Assert.assertTrue(applicationApproved, "Application not placed");
        return new PaymentPage(getConfig(),getAgent(),getTestData());
    }

    public void selectfromDropDown(String drpValue) throws Exception {
        String xpathCity = "//span[@class='menu-class']/*/*/div[text()='" + drpValue + "']";
//        getControl(xpathCity).click();
        getAgent().getWebDriver().findElement(By.xpath(xpathCity)).click();
    }

    public void navigateToHomeAndVerifyApplicationStatus() throws Exception{
        String number = getControl("txtApplicationNumber").getText();
        String[] application=number.split(" ");
        String applicationNumber=application[2];
        getControl("btnGoToHome").click();
        isApplicationIsInPendingStatus(applicationNumber);
    }

    public void isApplicationIsInPendingStatus(String applicationNumber) throws Exception {
        getControl("txtEnterApplicationNumber").enterText(applicationNumber);
        getControl("btnSearch").click();
        logger.info("Application Status "+getControl("txtApplicationStatus").getText());
        boolean applicationStatus = getControl("txtApplicationStatus").getText()
                .equalsIgnoreCase("Pending Approval");
        Assert.assertTrue(applicationStatus, "Application is not in Pending Status");
    }
}
