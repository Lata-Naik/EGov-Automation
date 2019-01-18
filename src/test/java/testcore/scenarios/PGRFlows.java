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
        String complaintNumber=apiAgent.createComplaint();
        home.loginEmployee()
                .navigateToComplaints()
                .assignComplaintToLME(complaintNumber)
                .isComplaintAssigned();
    }

    //@Test
    public void assignComplaintDGRO() throws Exception{
        logger.debug(this.getTestStartInfoMessage("assignComplaintDGRO"));
        String complaintNumber=apiAgent.createComplaint();
        home.loginEmployee()
                .navigateToComplaints()
                .assignComplaintToLME(complaintNumber)
                .isComplaintAssigned();
    }

    @Test
    public void resolveComplaintLME() throws Exception{
        logger.debug(this.getTestStartInfoMessage("resolveComplaintLME"));
        String complaintNumber = apiAgent.assignComplaint();
        home.loginEmployee()
                .navigateToComplaints()
                .searchAndResolveComplaint(complaintNumber)
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
        String complaintNumber=apiAgent.resolveComplaint();
        home.loginCitizen()
                .navigateToComplaints()
                .rateComplaint(complaintNumber)
                .isRateSubmitted();
    }

    @Test
    public void reopenClosedComplaintCitizen() throws Exception{
        logger.debug(this.getTestStartInfoMessage("reopenClosedComplaintCitizen"));
        home.loginCitizen()
                .navigateToComplaints()
                .reopenComplaintResolved()
                .isComplaintReopened();
    }

    @Test
    public void reopenRejectedComplaintCitizen() throws Exception{
        logger.debug(this.getTestStartInfoMessage("reopenRejectedComplaintCitizen"));
        home.loginCitizen()
                .navigateToComplaints()
                .reopenComplaintRejected()
                .isComplaintReopened();
    }

    @Test
    public void reassignToLMEasGRO() throws Exception{
        logger.debug(this.getTestStartInfoMessage("reassignToLMEasGRO"));
        home.loginEmployee()
                .navigateToComplaints()
                .reassignComplaintToLME()
                .isComplaintReassigned();
    }

   // @Test
    public void reassignToLMEasDGRO() throws Exception{
        logger.debug(this.getTestStartInfoMessage("reassignToLMEasDGRO"));
        home.loginEmployee()
                .navigateToComplaints()
                .reassignComplaintToLME()
                .isComplaintReassigned();
    }

    @Test
    public void rejectReassignRequestGRO() throws Exception{
        logger.debug(this.getTestStartInfoMessage("rejectReassignRequestGRO"));
        home.loginEmployee()
                .navigateToComplaints()
                .rejectReassignRequest()
                .isReassignRequestRejected();
    }

    //@Test
    public void rejectReassignRequestDGRO() throws Exception{
        logger.debug(this.getTestStartInfoMessage("rejectReassignRequestDGRO"));
        home.loginEmployee()
                .navigateToComplaints()
                .rejectReassignRequest()
                .isReassignRequestRejected();
    }

    @Test
    public void requestReassignLME() throws Exception{
        logger.debug(this.getTestStartInfoMessage("requestReassignLME"));
        String complaintNumber=apiAgent.createComplaint();
        home.loginEmployee()
                .navigateToComplaints()
                .searchAndReassignRequest(complaintNumber)
                .isReassignRequestSubmitted();
    }
}