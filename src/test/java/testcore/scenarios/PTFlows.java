package testcore.scenarios;

import org.testng.annotations.Test;

public class PTFlows extends SupportTest {
    @Test
    public void createResidentialPropertyTaxAndMakeFullPaymentCitizen() throws Exception {
        home.loginCitizen()
                .CitizenNavigateToPropertyTax()
                .applyPropertyTaxCitizen()
                .fillPropertyTaxFormAndMakeFullPaymentCitizen()
                .PTmakePayment()
                .getDataAfterPaymentSucessfull();
        Thread.sleep(9000);
    }

    @Test
    public void createCommercialPropertyTaxAndMakePartialPaymentCitizen() throws Exception {
        home.loginCitizen()
                .CitizenNavigateToPropertyTax()
                .applyPropertyTaxCitizen()
                .fillPropertyTaxFormAndMakePartialPayment()
                .PTmakePayment()
                .getDataAfterPaymentSucessfull();
        Thread.sleep(9000);
    }

    @Test
    public void createResidenatlPropertyTaxAndPayByCheckAndReassesEmployee() throws Exception{
        home.loginEmployee()
                .EmployeeNavigateToPropertyTax()
                .applyPropertyTaxEmployee()
                .fillPropertyTaxFormAndMakeFullPaymentWithRebateOrChargesEmployee()
                .makePaymentCounterEmployeePT()
                .getDataAfterSucessfulPaymentAndReassessChangeBuildUpArea()
                .makePaymentCounterEmployeePT();



    }

    @Test
    public void createResidentialPropertyTaxAndPayByCheckAndAssessAndPayEmployee() throws Exception{
        home.loginEmployee()
                .EmployeeNavigateToPropertyTax()
                .applyPropertyTaxEmployee()
                .fillPropertyTaxFormAndMakeFullPaymentWithRebateOrChargesEmployee()
                .makePaymentCounterEmployeePT()
                .getDataAfterSucessfulPaymentAndAssessAndPay()
                .makePaymentCounterEmployeePT();



    }
}
