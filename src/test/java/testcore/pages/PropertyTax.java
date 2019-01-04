package testcore.pages;

import agent.IAgent;
import central.Configuration;

import java.util.Map;

public class PropertyTax extends FullPage {

    public PropertyTax(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
        super(config, agent, testData);
    }

    @Override
    public String pageName() {
        return null;
    }

    public PropertyTax assessNewProperty() throws Exception {
        getControl("btnApply").click();
        getControl("btnFY").click();
        return this;
    }
    public PropertyTax commercialProperty() {
        return this;
    }
    public void pay() {
    }

    public PropertyTax residentialProperty() {
        return this;
    }
}