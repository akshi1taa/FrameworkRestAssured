package tests;

import static io.restassured.RestAssured.given;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import base.BaseTest1;
import base.Session;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SimpleLogin1Validation extends BaseTest1 {

	@Test
	public void login() {

		Session obj = new Session();

		obj.username = "seleniumtraining10@gmail.com";
		obj.password = "trivia45";

		Response resp = given().contentType(ContentType.JSON).log().body().when().body(obj).post("/login");

		System.out.println("---------------------------Response------------------------");
		resp.prettyPrint();

		JsonPath js = resp.jsonPath(); 		// new JsonPath();
		String st = js.get("loginStatus");
		if (!"success".equals(st)) 			// success is expected value
			reportFailure("invalid response status", true);

		System.out.println("session id is " + resp.header("sessionid"));
		String sessionid = resp.header("sessionid");

		if (!Pattern.matches("\\w+", sessionid)) 	// or [a-zA=Z0-9]* the regular expression part alternatively
		{
			reportFailure("Session id format is not correct", true);
		}
		//also, alternatively both the statements could have passed false as 2nd argument and then assertAll() func. at end could be used to ensure each condition fulfills

	}

}
