package eg.api.restassured;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DemoQA_API {

	RequestSpecification httpRequest;
	Response response;
	int resCode;

	@BeforeMethod
	public void setUp() {

	}

	// To validate the JSON Response
	@Test
	public void responseValidation() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/hyderabad");
		String resBody = response.getBody().asString();
		Assert.assertEquals(resBody.contains("Hyderabad"), true);
	}

	// Validating the data in Response
	@Test
	public void responseValidation2() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/hyderabad");
		JsonPath jsonOp = response.jsonPath();
		Assert.assertEquals(jsonOp.get("Temperature"), "23.7 Degree celsius");
		// Assert will fail due to change in temperature
	}

	// Authentication test
	@Test
	public void responseValidation3() {
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
		// Authentication
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");
		RestAssured.authentication = authScheme;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/");
		// Status Code Check
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
