package testcore.scenarios;


import org.testng.annotations.Test;

public class TLFlows extends SupportTest {

    @Test
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
    @Test
    public void rejectTradeLicenseApprover() throws Exception {
        logger.debug(this.getTestStartInfoMessage("testScript"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .rejectTradeLicense()
                .isApplicationRejected();
    }
}