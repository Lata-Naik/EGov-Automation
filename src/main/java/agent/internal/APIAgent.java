package agent.internal;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIAgent {

	public String authorizeCitizen() {
		RestAssured.baseURI = "https://egov-micro-qa.egovernments.org";
		Response response = RestAssured.given()
				.header("Authorization", "Basic ZWdvdi11c2VyLWNsaWVudDplZ292LXVzZXItc2VjcmV0").when()
				.post("/user/oauth/token?username=EMP1&password=EMP1&grant_type=password&scope=read&tenantId=pb.amritsar&userType=EMPLOYEE")
				.then().assertThat().log().body().extract().response();
		String access_token = response.path("access_token");
		System.out.println("access_token is " + access_token);
		return access_token;

	}

	public String createComplaint() {

		RestAssured.baseURI = "https://egov-micro-qa.egovernments.org";

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
		System.out.println("Complaint id is " + complaintId);
		return complaintId;

	}

	public String createTradeLicense() {
		return null;

	}
}
