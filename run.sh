#!/bin/bash
mvn -Dapp_browser_url="https://egov-micro-qa.egovernments.org/" -Denv="QA" -Dtest="testcore.scenarios.TLFlows#approveTradeLicenseApprover" test
