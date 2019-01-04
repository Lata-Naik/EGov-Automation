package testcore.pages;

import agent.IAgent;
import central.Configuration;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.Map;

public class ComplaintsPage extends FullPage {

    public ComplaintsPage(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
        super(conf, agent, testData);
        assertPageLoad();
    }

    @Override
    public String pageName() {
        return ComplaintsPage.class.getSimpleName();
    }

    public ComplaintsPage createComplaint() throws Exception{
        getControl("btnFileComplaint").click();
        //getControl("txtUploadPhoto").enterText(getTestData().get("UploadPhoto"));
        getControl("drpComplaintType").click();
        getControl("txtSearchComplaintType").enterText(getTestData().get("ComplaintType"));
        selectOptionFromDropDownByEnter(getTestData().get("ComplaintType"));
        getControl("txtAdditionalDetails").enterText(getTestData().get("AdditionalDetails"));
        getControl("txtCity").enterText(getTestData().get("City"));
        selectOptionFromDropDownByEnter(getTestData().get("City"));
        getControl("txtMohalla").enterText(getTestData().get("Mohalla"));
        selectOptionFromDropDownByEnter(getTestData().get("Mohalla"));;
        getControl("txtLocationDetails").click();
        getControl("txtSearchLocation").enterText(getTestData().get("SearchLocation"));
        getControl("txtSearchLocation").enterText(Keys.ENTER);
        Thread.sleep(1000);
        getControl("btnPick").click();
        getControl("txtHouseNo").enterText(getTestData().get("HouseNo"));
        getControl("txtLandmark").enterText(getTestData().get("Landmark"));
        getControl("btnSubmitComplaint").click();
        return this;
    }

    public void isComplaintRegistered() throws Exception{
        boolean complaintRegistered=getControl("txtComplaintRegSuccessMsg").getText().equalsIgnoreCase("Complaint Registered Successfully");
        logger.info("Registered complaint Number is: "+getControl("txtComplaintNumber").getText());
        Assert.assertTrue(complaintRegistered, "Complaint not registered");
    }

    public ComplaintsPage assignComplaintToLME() throws Exception{
        getControl("txtFirstComplaint").click();
        getControl("btnAssign").click();
        getControl("txtSearchLME").enterText(getTestData().get("LMEName"));
        getControl("txtLMEName").click();
        getControl("btnAssign").click();
        return this;
    }

    public void isComplaintAssigned()throws Exception{
        boolean complaintAssigned=getControl("txtAssignedToLMEmsg").isVisible();
        logger.info("Complaint: "+getControl("txtAssignedToLMEmsg").getText());
        Assert.assertTrue(complaintAssigned, "Complaint not assigned To LME");
    }

    public ComplaintsPage resolveComplaint() throws Exception{
        getControl("txtFirstComplaint").click();
        getControl("btnMarkResolved").click();
        getControl("txtComment").enterText(getTestData().get("Comment"));
        getControl("btnMarkResolved").click();
        return this;
    }

    public ComplaintsPage searchAndResolveComplaint() throws Exception{
        searchComplaintByMobileNumber();
        getControl("btnMarkResolved").click();
        getControl("txtComment").enterText(getTestData().get("Comment"));
        getControl("btnMarkResolved").click();
        return this;
    }

    public void isComplaintResolved() throws Exception{
        boolean resolvedSuccess=getControl("txtResolvedSuccessMsg").getText().equalsIgnoreCase("Resolved");
        logger.info("Complaint Status: "+getControl("txtResolvedSuccessMsg").getText());
        Assert.assertTrue(resolvedSuccess, "Complaint not resolved by LME");
    }

    public void searchComplaintByMobileNumber() throws Exception{
        getControl("txtSearchMobileNumber").enterText(getTestData().get("SearchMobileNumber"));
        getControl("txtFirstComplaint").click();
    }

    public void searchComplaintByComplaintNumber() throws Exception{
        getControl("txtSearchComplaintNumber").enterText(getTestData().get("SearchComplaintNumber"));
        getControl("txtFirstComplaint").click();
    }

    public ComplaintsPage rejectComplaint() throws Exception{
        getControl("txtFirstComplaint").click();
        getControl("btnReject").click();
        getControl("rdoReasonToReject").click();
        getControl("txtComment").enterText(getTestData().get("Comment"));
        getControl("btnSubmit").click();
        return this;
    }

    public void isComplaintRejected() throws Exception{
        boolean rejectedSuccess=getControl("txtRejectedSuccessMsg").getText().equalsIgnoreCase("You have Rejected this complaint.");
        Assert.assertTrue(rejectedSuccess, "Complaint not rejected by GRO");
    }

    public ComplaintsPage createComplaintCSR()throws Exception{
        getControl("icnAddComplaint").click();
        getControl("txtCitizenName").enterText(getTestData().get("CitizenName"));
        getControl("txtCitizenNumber").enterText(getTestData().get("CitizenNumber"));
        getControl("drpComplaintType").click();
        getControl("txtSearchComplaintType").enterText(getTestData().get("ComplaintType"));
        selectOptionFromDropDownByEnter(getTestData().get("ComplaintType"));
        getControl("txtAdditionalDetails").enterText(getTestData().get("AdditionalDetails"));
        getControl("txtMohalla").enterText(getTestData().get("Mohalla"));
        selectOptionFromDropDownByEnter(getTestData().get("Mohalla"));
        getControl("txtHouseNo").enterText(getTestData().get("HouseNo"));
        getControl("txtLandmark").enterText(getTestData().get("Landmark"));
        getControl("btnSubmitComplaint").click();
        return this;
    }

    public ComplaintsPage rateComplaint() throws Exception{
        getControl("txtMyComplaint").click();
        scrollDownTillElement("txtResolvedComplaint");
        getControl("txtResolvedComplaint").click();
        getControl("btnRate").click();
        getControl("btnStarRate4").click();
//        getControl("txtFeedBackService").click();
        getControl("txtFeedBackComments").enterText(getTestData().get("RateComments"));
        getControl("btnFeedBackSubmit").click();
        return this;
    }
    public ComplaintsPage isRateSubmitted() throws Exception{
        boolean rejectedSuccess=getControl("txtRateSubmittedMsg").getText().equalsIgnoreCase("Thank You for your feedback!");
        Assert.assertTrue(rejectedSuccess, "Rate not Submitted");

        return this;
    }
    public ComplaintsPage logoutUser() throws Exception{
        getControl("icnProfile").click();
        getControl("txtLogout").click();
        getControl("btnLogout").click();
        isUserLoggedOut();
        return this;
    }

    public void isUserLoggedOut()throws Exception{
        boolean userLogin = getControl("txtLogin").isVisible();
        Assert.assertTrue(userLogin, "User not logged out");
    }
}
