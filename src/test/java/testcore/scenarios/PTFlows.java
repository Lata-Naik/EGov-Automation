package testcore.scenarios;

import org.testng.annotations.Test;

public class PTFlows extends SupportTest {

    @Test
    public void assessNonResidentialProperty() throws Exception {
        logger.debug(getTestStartInfoMessage("testScript"));
        home.loginEmployee()
                .navigateToPropertyTax()
                .assessNewProperty();
//                .commercialProperty()
//                .pay();
    }
    @Test
    public void assessResidentialProperty() throws Exception {
        logger.debug(getTestStartInfoMessage("testScript"));
        home.loginEmployee()
                .navigateToPropertyTax()
                .assessNewProperty()
                .residentialProperty()
                .pay();
    }
}