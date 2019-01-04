package testcore.scenarios;


import org.testng.annotations.Test;

public class PTFlows extends SupportTest {

    @Test
    public void createResidentialPropertyTaxAndMakeFullPaymentCitizen() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createResidentialPropertyTaxAndMakeFullPaymentCitizen"));
        home.loginCitizen()
                .navigateToPropertyTax()
                .applyPropertyTaxCitizen()
                .fillPropertyTaxFormAndMakeFullPayment()
                .PTmakePayment()
                .getDataAfterPaymentSucessfull();
        Thread.sleep(9000);
    }

    @Test
    public void createCommercialPropertyTaxAndMakePartialPaymentCitizen() throws Exception{
        logger.debug(this.getTestStartInfoMessage("createCommercialPropertyTaxAndMakePartialPaymentCitizen"));
        home.loginCitizen()
                .navigateToPropertyTax()
                .applyPropertyTaxCitizen()
                .fillPropertyTaxFormAndMakePartialPayment()
                .PTmakePayment()
                .getDataAfterPaymentSucessfull();
        Thread.sleep(9000);
    }

}