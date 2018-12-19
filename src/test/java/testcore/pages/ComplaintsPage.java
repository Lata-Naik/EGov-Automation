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
        getControl("drpComplaintType").click();
        getControl("txtWaterandSewage").click();
        getControl("txtBlockOrOverflowingSewage").click();
        getControl("txtAdditionalDetails").enterText(getTestData().get("AdditionalDetails"));
        getControl("txtLocationDetails").click();
        getControl("txtSearchLocation").enterText(getTestData().get("SearchLocation"));
        getControl("txtSearchLocation").enterText(Keys.ENTER);
        Thread.sleep(1000);
        getControl("btnPick").click();
        Thread.sleep(10000);
        getControl("txtHouseNo").enterText(getTestData().get("HouseNo"));
        getControl("txtLandmark").enterText(getTestData().get("Landmark"));
        getControl("btnSubmitComplaint").click();
        return this;
    }

    public void isComplaintRegigistered() throws Exception{
        boolean complaintRegistered=getControl("txtComplaintRegSuccessMsg").getText().equalsIgnoreCase("Complaint Registered Successfully");
        logger.info("Registred complaint Number is: "+getControl("txtComplaintNumber").getText());
        Assert.assertTrue(complaintRegistered, "Complaint not registered");
    }

}
