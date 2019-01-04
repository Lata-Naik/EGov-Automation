package testcore.scenarios;


import org.testng.annotations.Test;

public class PGRFlows extends SupportTest {

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
    public void assignComplaintDGRO() throws Exception{
        logger.debug(this.getTestStartInfoMessage("assignComplaintDGRO"));
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
    public void fileComplaintCSR() throws Exception {
        logger.debug(this.getTestStartInfoMessage("fileComplaintCSR"));
        home.loginEmployee()
                .navigateToComplaints()
                .createComplaintCSR()
                .isComplaintRegistered();
    }

    @Test
    public void rateComplaintCitizen() throws Exception{
        logger.debug(this.getTestStartInfoMessage("rateComplaintCitizen"));
        home.loginCitizen()
                .navigateToComplaints()
                .rateComplaint()
                .isRateSubmitted();
    }
}