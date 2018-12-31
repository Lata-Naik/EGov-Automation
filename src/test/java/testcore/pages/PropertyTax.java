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
}