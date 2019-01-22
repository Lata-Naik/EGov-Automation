# EGOV
eGoverments Automation test cases
Clone and run with local and Saucelab.

## Saucelab Key Update

```[src/main/java/agent/internal/WebAgentFactory.java]
USERNAME = "<saucelab-username>"
ACCESS_KEY = "<saucelab-key>"```

# test
Then you should uncomment

initDriverinCloud();

and comment
initDriver(config, browser);

If you wants to run in local you should do vice versa.
