#!/bin/bash
mvn -Dbrowser_bin_path="" -Dbrowser_driver_path="/Users/dharma/Documents/Softwares/chromedriver" -Denv="QA" -Dtest="testcore.scenarios.PGRFlows" test


# mvn -Dbrowser_bin_path="" -Dbrowser_driver_path="/Users/dharma/Documents/Softwares/chromedriver"  -Dapp_browser_url="https://mseva-uat.lgpunjab.gov.in/" -Denv="UAT" -Dtest="testcore.scenarios.PGRFlows#fileComplaintCSR" test
