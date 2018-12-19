package testcore.pages;

import agent.IAgent;
import central.Configuration;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.Map;

public class HomePage extends FullPage {

    public HomePage(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
        super(conf, agent, testData);
        assertPageLoad();
    }

    @Override
    public String pageName() {
        return HomePage.class.getSimpleName();
    }

    public HomePage loginCitizen()throws Exception{
        getControl("txtMobileNumber").enterText(getTestData().get("MobileNumber"));
        getControl("btnContinue").click();
        getControl("txtOTP").enterText(getTestData().get("OTP"));
        getControl("btnGetStarted").click();
        isUserLoggedIn();
        return this;
    }

    public HomePage loginEmployee()throws Exception{
        getControl("txtUserIdEmployee").enterText(getTestData().get("UserID"));
        getControl("txtPasswordEmployee").enterText(getTestData().get("Password"));
        getControl("txtCityEmployee").click();
        getControl("txtSearchCity").enterText(getTestData().get("City"));
        getControl("txtCitySearchResult").click();
        getControl("btnLogin").click();
        isUserLoggedIn();
        return this;
    }

    public ComplaintsPage navigateToComplaints() throws Exception{
        getControl("btnComplaints").click();
        return new ComplaintsPage(getConfig(),getAgent(),getTestData());
    }

    public TradeLicense navigateToTradeLicense() throws Exception{
        getControl("btnTradeLicense").click();
        return new TradeLicense(getConfig(),getAgent(),getTestData());
    }

    public void isUserLoggedIn()throws Exception{
        boolean usedLogin = getControl("txtWelcomeText").getText()
                .equalsIgnoreCase("Welcome "+getTestData().get("UserName")+",");
        logger.info("Logged in user "+getTestData().get("UserName"));
        Assert.assertTrue(usedLogin, "User not logged in");
    }
}
