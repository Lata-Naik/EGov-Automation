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
        Thread.sleep(1000);
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
        getControl("btnProceedToPayment").click();
        logger.info("Amount to be paid "+getControl("txtAmount").getText());
        if(getTestData().get("ModeOfPayment").equalsIgnoreCase("Cash")){
//            getControl("txtCash").click();
            payByCash();
            Thread.sleep(10000);
        }
        else if(getTestData().get("ModeOfPayment").equalsIgnoreCase("Cheque")){
            getControl("txtCheque").click();
            payByCheque();
        }
        else if(getTestData().get("ModeOfPayment").equalsIgnoreCase("DD")){
            getControl("txtDD").click();
            payByDD();
        }
        else {
            getControl("txtCreditDebitCard").click();
            payByCreditDebitCard();
        }
        getControl("btnSubmit").click();
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



    public void payByCash()throws Exception{
        addPayerDetails();
    }
    public void payByCashPT()throws Exception{
        addPayerDetailsPT();
    }
    public void payByCheque()throws Exception{
        addPayerDetails();
        getControl("txtChequeNumber").enterText(getTestData().get("ChequeNumber"));
        getControl("txtChequeDate").enterText(getTestData().get("ChequeDate"));
        getControl("txtChequeIFSC").enterText(getTestData().get("ChequeIFSC"));
        getControl("txtChequeBankName").enterText(getTestData().get("ChequeBankName"));
        getControl("txtChequeBankBranch").enterText(getTestData().get("ChequeBankBranch"));
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
    public void payByDD()throws Exception{
        addPayerDetails();
        getControl("txtDDNumber").enterText(getTestData().get("DDNumber"));
        getControl("txtDDDate").enterText(getTestData().get("DDDate"));
        getControl("txtDDIFSC").enterText(getTestData().get("DDIFSC"));
        getControl("txtDDBankName").enterText(getTestData().get("DDBankName"));
        getControl("txtDDBankBranch").enterText(getTestData().get("DDBankBranch"));
    }
    public void payByDDPT()throws Exception{
        addPayerDetailsPT();
        getControl("txtDDNumberPT").enterText(getTestData().get("DDNumber"));
        getControl("txtDDDatePT").enterText(getTestData().get("DDDate"));
        getControl("txtDDIFSCPT").enterText(getTestData().get("DDIFSC"));
    }
    public void payByCreditDebitCard()throws Exception{
        addPayerDetails();
        getControl("txtCardNumber").enterText(getTestData().get("CardNumber"));
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
        getControl("drpPaidBy").click();
        selectOptionFromDropDownList(getTestData().get("PaidBy"));
        Thread.sleep(5000);
        getControl("txtPayerName").enterText(getTestData().get("PayerName"));
        getControl("txtPayerMobileNumber").enterText(getTestData().get("PayerMobileNumber"));
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

    public void isApplicationPlaced() throws Exception{
        boolean applicationApproved = getControl("txtSuccessMessage").getText().equalsIgnoreCase("Payment has been collected successfully!");
        logger.info(getControl("txtApplicationNumber").getText());
        Assert.assertTrue(applicationApproved, "Application not placed");
        }

    public void selectfromDropDown(String drpValue) throws Exception {
        String xpathCity = "//span[@class='menu-class']/*/*/div[text()='" + drpValue + "']";
//        getControl(xpathCity).click();
        getAgent().getWebDriver().findElement(By.xpath(xpathCity)).click();
    }
}
