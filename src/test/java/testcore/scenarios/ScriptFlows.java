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
                .isComplaintRegigistered();
    }

    @Test
    public void assignComplaintGRO() throws Exception{
        logger.debug(this.getTestStartInfoMessage("assignComplaintGRO"));
        home.loginEmployee();
    }
}