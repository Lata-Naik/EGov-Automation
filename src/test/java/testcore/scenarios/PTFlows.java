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
    public void createResidenatlPropertyTaxAndPayByChequeAndReassesEmployee() throws Exception {
        home.loginEmployee()
                .EmployeeNavigateToPropertyTax()
                .applyPropertyTaxEmployee()
                .fillResidentialPropertyTaxFormAndMakeFullPaymentWithRebateOrChargesEmployee()
                .makePaymentCounterEmployeePT()
                .getDataAfterSucessfulPaymentAndReassessChangeBuildUpArea()
                .makePaymentCounterEmployeePT();


    }

    @Test
    public void createResidentialPropertyTaxAndPayByChequeAndAssessAndPayEmployee() throws Exception {
        home.loginEmployee()
                .EmployeeNavigateToPropertyTax()
                .applyPropertyTaxEmployee()
                .fillResidentialPropertyTaxFormAndMakeFullPaymentWithRebateOrChargesEmployee()
                .makePaymentCounterEmployeePT()
                .getDataAfterSucessfulPaymentAndAssessAndPay()
                .makePaymentCounterEmployeePT();
    }

    @Test
    public void createCommercialPropertyTaxAndPayByCashEmployee() throws Exception {
        home.loginEmployee()
                .EmployeeNavigateToPropertyTax()
                .applyPropertyTaxEmployee()
                .fillCommercialPropertyTaxFormAndMakeFullPaymentWithCashEmployee()
                .makePaymentCounterEmployeePT()
                .getDataAfterPaymentSucessfull();
    }

    @Test
    public void createResidentialPropertyTaxAndPayByChequeEmployee() throws Exception {
        home.loginEmployee()
                .EmployeeNavigateToPropertyTax()
                .applyPropertyTaxEmployee()
                .fillResidentialPropertyTaxFormAndMakeFullPaymentWithChequeEmployee()
                .makePaymentCounterEmployeePT()
                .getDataAfterPaymentSucessfull();

    }
}



