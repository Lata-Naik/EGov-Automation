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
                .makePaymentTL()
                .isApplicationPlaced()
                .navigateToHomeAndVerifyApplicationStatus();
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
    public void createTradeLicenseCounterEmployeePayByCash() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createTradeLicenseCounterEmployeePayByCash"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .addNewTradeLicense()
                .makePaymentCounterEmployeeTL()
                .isApplicationPlaced()
                .navigateToHomeAndVerifyApplicationStatus();
    }

    @Test
    public void createTradeLicenseCounterEmployeePayByCheque() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createTradeLicenseCounterEmployeePayByCheque"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .addNewTradeLicense()
                .makePaymentCounterEmployeeTL()
                .isApplicationPlaced()
                .navigateToHomeAndVerifyApplicationStatus();
    }

    @Test
    public void createTradeLicenseCounterEmployeePayByDD() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createTradeLicenseCounterEmployeePayByDD"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .addNewTradeLicense()
                .makePaymentCounterEmployeeTL()
                .isApplicationPlaced()
                .navigateToHomeAndVerifyApplicationStatus();
    }

    @Test
    public void createTradeLicenseCounterEmployeePayByCard() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createTradeLicenseCounterEmployeePayByCard"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .addNewTradeLicense()
                .makePaymentCounterEmployeeTL()
                .isApplicationPlaced()
                .navigateToHomeAndVerifyApplicationStatus();
    }

    @Test
    public void createTradeLicenseCounterEmployeeAdhocPenalty() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createTradeLicenseCounterEmployeeAdhocPenalty"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .addNewTradeLicenseAddPenaltyRebate()
                .makePaymentCounterEmployeeTL()
                .isApplicationPlaced()
                .navigateToHomeAndVerifyApplicationStatus();
    }

    @Test
    public void createTradeLicenseCounterEmployeeAdhocRebate() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createTradeLicenseCounterEmployeeAdhocRebate"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .addNewTradeLicenseAddPenaltyRebate()
                .makePaymentCounterEmployeeTL()
                .isApplicationPlaced()
                .navigateToHomeAndVerifyApplicationStatus();
    }

    @Test
    public void createTradeLicenseCounterEmployeeAdhocPenaltyRebate() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createTradeLicenseCounterEmployeeAdhocPenaltyRebate"));
        home.loginEmployee()
                .navigateToTradeLicense()
                .addNewTradeLicenseAddPenaltyRebate()
                .makePaymentCounterEmployeeTL()
                .isApplicationPlaced()
                .navigateToHomeAndVerifyApplicationStatus();
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