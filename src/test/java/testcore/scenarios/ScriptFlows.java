package testcore.scenarios;


import org.testng.annotations.Test;

public class ScriptFlows extends SupportTest {

    @Test
    public void fileComplaintCitizen() throws Exception{
        logger.debug(this.getTestStartInfoMessage("fileComplaintCitizen"));
        home.loginCitizen()
                .navigateToComplaints()
                .createComplaint()
                .isComplaintRegistered();
    }

    @Test
    public void assignComplaintGRO() throws Exception{
        logger.debug(this.getTestStartInfoMessage("assignComplaintGRO"));
        home.loginEmployee()
                .navigateToComplaints()
                .assignComplaintToLME()
                .isComplaintAssigned();
    }

    @Test
    public void resolveComplaintLME() throws Exception{
        logger.debug(this.getTestStartInfoMessage("resolveComplaintLME"));
        home.loginEmployee()
                .navigateToComplaints()
                .searchAndResolveComplaint()
                .isComplaintResolved();
    }

    @Test
    public void rejectComplaintGRO() throws Exception{
        logger.debug(this.getTestStartInfoMessage("rejectComplaintGRO"));
        home.loginEmployee()
                .navigateToComplaints()
                .rejectComplaint()
                .isComplaintRejected();
    }

    @Test
    public void createResidentialPropertyTaxAndMakeFullPaymentCitizen() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createResidentialPropertyTaxAndMakeFullPaymentCitizen"));
        home.loginCitizen()
                .navigateToPropertyTax()
                .applyPropertyTaxCitizen()
                .fillPropertyTaxFormAndMakeFullPayment()
                .PTmakePayment()
                .getDataAfterPaymentSucessfull();
        Thread.sleep(9000);
    }

    @Test
    public void createCommercialPropertyTaxAndMakePartialPaymentCitizen() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createCommercialPropertyTaxAndMakePartialPaymentCitizen"));
        home.loginCitizen()
                .navigateToPropertyTax()
                .applyPropertyTaxCitizen()
                .fillPropertyTaxFormAndMakePartialPayment()
                .PTmakePayment()
                .getDataAfterPaymentSucessfull();
        Thread.sleep(9000);



    }

    public void createTradeLicenseCitizen() throws Exception{
        logger.debug(this.getTestStartInfoMessage("testScript"));
        home.loginCitizen()
                .navigateToTradeLicense()
                .applyTradeLicense()
                .fillTradeLicenseForm()
                .makePayment();
    }

    @Test
    public void approveTradeLicenseApprover() throws Exception {
        logger.debug(this.getTestStartInfoMessage("testScript"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .approveTradeLicense()
                .isApplicationApproved();
    }
}
