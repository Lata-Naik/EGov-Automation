package testcore.scenarios;




import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;


public class PTFlows extends SupportTest {
    @Test
    public void createResidentialPropertyTaxAndMakeFullPaymentCitizen() throws Exception {
        home.loginCitizen()
                .CitizenNavigateToPropertyTax()
                .applyPropertyTaxCitizen()
                .fillPropertyTaxFormAndMakeFullPaymentCitizen()
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

    @Test
    public void createResidentialPropertyTaxForFullExemption() throws Exception {
        home.loginCitizen()
                .CitizenNavigateToPropertyTax()
                .applyPropertyTaxCitizen()
                .fillPropertyTaxFormAndMakeFullPaymentCitizen()
                .getDataAfterPaymentSucessfull();
    }

    @Test
    public void createCommercialPropertyTaxForMultipleOwner() throws Exception {
        home.loginCitizen()
                .CitizenNavigateToPropertyTax()
                .applyPropertyTaxCitizen()
                .fillCommercialPropertyTaxFormAndMakeFullPaymentCitizen()
                .getDataAfterPaymentSucessfull();
    }

    @Test
    public void createResidentialPropertyTaxExemptionUpTo5000() throws Exception {
        home.loginCitizen()
                .CitizenNavigateToPropertyTax()
                .applyPropertyTaxCitizen()
                .fillPropertyTaxFormAndMakeFullPaymentCitizen()
                .getDataAfterPaymentSucessfull();
    }



}




