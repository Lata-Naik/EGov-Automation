package testcore.scenarios;


import org.testng.annotations.Test;

public class TLFlows extends SupportTest {

    @Test
    public void createTradeLicenseCitizen() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createTradeLicenseCitizen"));
        home.loginCitizen()
                .navigateToTradeLicense()
                .applyTradeLicense()
                .fillTradeLicenseForm()
                .makePayment()
                .isApplicationPlaced();
    }

    @Test
    public void approveTradeLicenseApprover() throws Exception {
        logger.debug(this.getTestStartInfoMessage("approveTradeLicenseApprover"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .approveTradeLicense()
                .isApplicationApproved();
    }
    @Test
    public void rejectTradeLicenseApprover() throws Exception {
        logger.debug(this.getTestStartInfoMessage("rejectTradeLicenseApprover"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .rejectTradeLicense()
                .isApplicationRejected();
    }

    @Test
    public void createTradeLicenseCounterEmployee() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createTradeLicenseCounterEmployee"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .addNewTradeLicense()
                .makePaymentCounterEmployee()
                .isApplicationPlaced();
    }

    @Test
    public void cancelApprovedLicenseApprover() throws Exception{
        logger.debug(this.getTestStartInfoMessage("cancelApprovedLicenseApprover"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .cancelTradeLicense()
                .isApprovedTradeLicenseCancelled();
    }
}