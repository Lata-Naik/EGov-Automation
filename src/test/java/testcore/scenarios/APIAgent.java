package testcore.scenarios;

import agent.IAgent;
import central.Configuration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testcore.pages.FullPage;
import testcore.pages.HomePage;

import java.util.Map;

public class APIAgent extends FullPage {

    public APIAgent(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
        super(conf, agent, testData);
        assertPageLoad();
    }

    @Override
    public String pageName() {
        return HomePage.class.getSimpleName();
    }

//    String  complaintId;

    public String authorizeCitizen() {
        RestAssured.baseURI = getConfig().getProperty("app_browser_url", config);
        Response response = RestAssured.given()
                .header("Authorization", "Basic ZWdvdi11c2VyLWNsaWVudDplZ292LXVzZXItc2VjcmV0").when()
                .post("/user/oauth/token?username=7829727713&password=123456&grant_type=password&scope=read&tenantId=pb&userType=CITIZEN")
                .then().assertThat().log().body().extract().response();
        String access_token = response.path("access_token");
        System.out.println("Citizen access_token is " + access_token);
        return access_token;

    }

    public String createComplaint() {

        RestAssured.baseURI = getConfig().getProperty("app_browser_url", config);

        Response response = RestAssured.given().contentType(ContentType.JSON).body(

                "{\n" + "    \"RequestInfo\": {\n" + "        \"apiId\": \"Rainmaker\",\n"
                        + "        \"ver\": \".01\",\n" + "        \"ts\": \"\",\n"
                        + "        \"action\": \"_create\",\n" + "        \"did\": \"1\",\n"
                        + "        \"key\": \"\",\n" + "        \"msgId\": \"20170310130900|en_IN\",\n"
                        + "        \"authToken\": \"" + authorizeCitizen() + "\"\n" + "    },\n"
                        + "    \"actionInfo\": [\n" + "        {\n" + "            \"media\": []\n" + "        }\n"
                        + "    ],\n" + "    \"services\": [\n" + "        {\n"
                        + "            \"serviceCode\": \"OverflowingOrBlockedDrain\",\n"
                        + "            \"description\": \"test from api of postman client\",\n"
                        + "            \"addressDetail\": {\n" + "                \"latitude\": \"31.322422\",\n"
                        + "                \"longitude\": \"75.573419\",\n"
                        + "                \"city\": \"pb.jalandhar\",\n"
                        + "                \"mohalla\": \"JLC1057\",\n"
                        + "                \"houseNoAndStreetName\": \"from postman\",\n"
                        + "                \"landmark\": \"from postman client\"\n" + "            },\n"
                        + "            \"address\": \"Jalandhar - Nakodar Rd, Shaheed Udham Singh Nagar, Jalandhar, Punjab 144001, India\",\n"
                        + "            \"tenantId\": \"pb.jalandhar\",\n" + "            \"source\": \"web\",\n"
                        + "            \"phone\": \"9742379422\"\n" + "        }\n" + "    ]\n" + "}")

                .when()

                .post("/rainmaker-pgr/v1/requests/_create?tenantId=pb.jalandhar")

                .then().assertThat().statusCode(201).log().body().extract().response();
        // Change item index
        String complaintId = response.path("services[0].serviceRequestId");
        System.out.println("Created Complaint id is " + complaintId);
        return response.path("services[0].serviceRequestId");
    }


    public String authorizeEmployeeGRO() {
        RestAssured.baseURI = getConfig().getProperty("app_browser_url", config);
        Response response = RestAssured.given()
                .header("Authorization", "Basic ZWdvdi11c2VyLWNsaWVudDplZ292LXVzZXItc2VjcmV0").when()
                .post("/user/oauth/token?username=TESTGRO1&password=12345678&grant_type=password&scope=read&tenantId=pb.jalandhar&userType=EMPLOYEE")
                .then().assertThat().log().body().extract().response();
        String access_token = response.path("access_token");
        System.out.println("access_token employee is " + access_token);
        return access_token;

    }


    public String assignComplaint(){
        String id =   createComplaint();
        RestAssured.baseURI = "https://egov-micro-qa.egovernments.org";
        Response response = RestAssured.given().contentType(ContentType.JSON).body("{\n" +
                "    \"RequestInfo\": {\n" +
                "        \"apiId\": \"Rainmaker\",\n" +
                "        \"ver\": \".01\",\n" +
                "        \"ts\": \"\",\n" +
                "        \"did\": \"1\",\n" +
                "        \"key\": \"\",\n" +
                "        \"msgId\": \"20170310130900|en_IN\",\n" +
                "        \"authToken\": \""+authorizeEmployeeGRO()+"\"\n" +
                "    },\n" +
                "    \"actionInfo\": [\n" +
                "        {\n" +
                "            \"action\": \"assign\",\n" +
                "            \"assignee\": \"163\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"services\": [\n" +
                "        {\n" +
                "            \"citizen\": {\n" +
                "                \"id\": 762,\n" +
                "                \"uuid\": \"e4134081-ddf8-430f-a011-26f4895944de\",\n" +
                "                \"name\": \"Lata\",\n" +
                "                \"permanentAddress\": \"ATAR SINGH COLONY - Area2, amritsar\",\n" +
                "                \"mobileNumber\": \"7829727713\",\n" +
                "                \"aadhaarNumber\": null,\n" +
                "                \"pan\": null,\n" +
                "                \"emailId\": null,\n" +
                "                \"userName\": \"7829727713\",\n" +
                "                \"password\": null,\n" +
                "                \"active\": true,\n" +
                "                \"type\": \"CITIZEN\",\n" +
                "                \"gender\": \"MALE\",\n" +
                "                \"tenantId\": \"pb\",\n" +
                "                \"roles\": [\n" +
                "                    {\n" +
                "                        \"name\": \"Citizen\",\n" +
                "                        \"code\": \"CITIZEN\",\n" +
                "                        \"tenantId\": null\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tenantId\": \"pb.jalandhar\",\n" +
                "            \"serviceCode\": \"OverflowingOrBlockedDrain\",\n" +
                "            \"serviceRequestId\": \""+id+"\",\n" +
                "            \"description\": \"test\",\n" +
                "            \"addressId\": \"9372d448-1352-4b8f-a5eb-0931741cc7a6\",\n" +
                "            \"address\": \"Jalandhar - Nakodar Rd, Shaheed Udham Singh Nagar, Jalandhar, Punjab 144001, India\",\n" +
                "            \"accountId\": \"762\",\n" +
                "            \"phone\": \"7829727713\",\n" +
                "            \"addressDetail\": {\n" +
                "                \"uuid\": \"9372d448-1352-4b8f-a5eb-0931741cc7a6\",\n" +
                "                \"houseNoAndStreetName\": \"test\",\n" +
                "                \"mohalla\": \"JLC487\",\n" +
                "                \"locality\": \"New Suraj Ganj\",\n" +
                "                \"city\": \"pb.jalandhar\",\n" +
                "                \"latitude\": 31.322422,\n" +
                "                \"longitude\": 75.573419,\n" +
                "                \"landmark\": \"test\",\n" +
                "                \"tenantId\": \"pb.jalandhar\"\n" +
                "            },\n" +
                "            \"active\": true,\n" +
                "            \"status\": \"open\",\n" +
                "            \"source\": \"web\",\n" +
                "            \"auditDetails\": {\n" +
                "                \"createdBy\": \"762\",\n" +
                "                \"lastModifiedBy\": \"762\",\n" +
                "                \"createdTime\": 1543575667842,\n" +
                "                \"lastModifiedTime\": 1543575667842\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}")

                .when()

                .post("/rainmaker-pgr/v1/requests/_update?tenantId=pb.jalandhar")

                .then().assertThat().log().body().extract().response();
        // Change item index
        String complaintId = response.path("actionHistory[0].actions[0].businessKey");
        System.out.println("Complaint id assigned is " + complaintId);
        String lmeID = response.path("actionHistory[0].actions[0].assignee");
        System.out.println("Complaint assigned to " + lmeID);
        return complaintId;
    }

    public String createTradeLicense() {
        return null;

    }
}
