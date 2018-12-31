package testcore.scenarios;

import org.testng.annotations.Test;

public class UseCases extends SupportTest {

    @Test
    public void assessNewProperty() throws Exception {
        logger.debug(getTestStartInfoMessage("testScript"));
        home.loginEmployee();
    }
}