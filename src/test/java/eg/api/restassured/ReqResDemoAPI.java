package eg.api.restassured;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReqResDemoAPI {
	RequestSpecification httpRequest;
	int resCode;

	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "https://reqres.in/api";
	}
	
	@Test
	public void getRequestMethod2() {
		httpRequest = RestAssured.given();
		httpRequest.params("page", "2");
		Response res = httpRequest.request(Method.GET, "/users");
//		System.out.println(res.body().prettyPrint());
		resCode = res.getStatusCode();
		Assert.assertEquals(resCode, 200);	
	}
	
	@Test
	public void getRequestMethod3() {
		httpRequest = RestAssured.given();
		Response res = httpRequest.request(Method.GET, "/users/2");
//		System.out.println(res.body().prettyPrint());
		resCode = res.getStatusCode();
		Assert.assertEquals(resCode, 200);
	}
	
	@Test
	public void postMethod1() {
		httpRequest = RestAssured.given();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "Gowtham");
		jsonObj.put("job", "SAE");
		httpRequest.body(jsonObj.toString());
		httpRequest.header("Content-Type","application/json");
		Response res = httpRequest.request(Method.POST,"/users");
		Assert.assertEquals(res.getStatusCode(), 201);
		System.out.println(res.getBody().asString());
	}
	
	// To read all Headers in the response
	@Test
	public void postPrintAllHeaders() {
		httpRequest = RestAssured.given();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "Gowtham");
		jsonObj.put("job", "SAE");
		httpRequest.body(jsonObj.toString());
		httpRequest.header("Content-Type","application/json");
		Response res = httpRequest.request(Method.POST,"/users");
		Headers headers= res.getHeaders();
		for(Header head: headers) {
			System.out.println(head.getName()+" : "+head.getValue());
		}
	}
}
