package testcore.scenarios;


import org.testng.annotations.Test;

public class ScriptFlows extends SupportTest {

    @Test
    public void createTradeLicenseCitizen() throws Exception{
        logger.debug(this.getTestStartInfoMessage("testScript"));
        home.loginCitizen()
                .navigateToTradeLicense()
                .applyTradeLicense()
                .fillTradeLicenseForm();
    }

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
}