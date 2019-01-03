package testcore.pages;

import agent.IAgent;
import central.Configuration;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
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

    public PaymentPage makePayment() throws Exception{
        getControl("txtMasterCard").waitUntilClickable();
        getControl("txtMasterCard").click();
//        getControl("btnProceedToPayment").click();
//        getControl("btnMakePayment").click();
//        logger.info("Amount to be paid "+getControl("txtAmount").getText());
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

    public PaymentPage makePaymentCounterEmployee() throws Exception{
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

    public void payByCash()throws Exception{
        addPayerDetails();
    }
    public void payByCheque()throws Exception{
        addPayerDetails();
        getControl("txtChequeNumber").enterText(getTestData().get("ChequeNumber"));
        getControl("txtChequeDate").enterText(getTestData().get("ChequeDate"));
        getControl("txtChequeIFSC").enterText(getTestData().get("ChequeIFSC"));
        getControl("txtChequeBankName").enterText(getTestData().get("ChequeBankName"));
        getControl("txtChequeBankBranch").enterText(getTestData().get("ChequeBankBranch"));
    }
    public void payByDD()throws Exception{
        addPayerDetails();
        getControl("txtDDNumber").enterText(getTestData().get("DDNumber"));
        getControl("txtDDDate").enterText(getTestData().get("DDDate"));
        getControl("txtDDIFSC").enterText(getTestData().get("DDIFSC"));
        getControl("txtDDBankName").enterText(getTestData().get("DDBankName"));
        getControl("txtDDBankBranch").enterText(getTestData().get("DDBankBranch"));
    }
    public void payByCreditDebitCard()throws Exception{
        addPayerDetails();
        getControl("txtCardNumber").enterText(getTestData().get("CardNumber"));
        getControl("txtTransactionNumber").enterText(getTestData().get("TransactionNumber"));
        getControl("txtRepeatTransactionNumber").enterText(getTestData().get("TransactionNumber"));
    }

    public void addPayerDetails() throws Exception{
        getControl("drpPaidBy").click();
        selectOptionFromDropDownList(getTestData().get("PaidBy"));
        Thread.sleep(5000);
        getControl("txtPayerName").enterText(getTestData().get("PayerName"));
        getControl("txtPayerMobileNumber").enterText(getTestData().get("PayerMobileNumber"));
    }

    public void isApplicationPlaced() throws Exception{
        boolean applicationApproved = getControl("txtSuccessMessage").getText().equalsIgnoreCase("Payment has been collected successfully!");
        logger.info(getControl("txtApplicationNumber").getText());
        Assert.assertTrue(applicationApproved, "Application not placed");
        }
}
