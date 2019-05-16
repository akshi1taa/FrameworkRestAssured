package tests;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTest1;
import base.Session;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginTest extends BaseTest1 {
	
	// public static String sessionid;
	
	@Test(dataProvider = "getData")
	public void doLogin(Hashtable<String,String> data)
	{
		System.out.println(data.get("Username"));
		System.out.println(data.get("Password"));
		
		String uid=data.get("Username");
		String pass=data.get("Password");
		
		Session obj=new Session();
		
		obj.username=uid;	 //or, obj.setUsername(uid);
		obj.password=pass;	//or, obj.setPassword(pass);
		
		Response resp=given().filter(new RequestLoggingFilter(requestCapture)).contentType(ContentType.JSON)
				.log().body().when().body(obj).post();
		
		System.out.println("---------------------------Response------------------------");
		resp.prettyPrint();
		
		sessionid=resp.header("sessionid");
		System.out.println("Session id is "+sessionid);
		
		addReqLinkToReport("Login Request", "LoginRequest"+iteration, requestWriter.toString());	//#
		test.log(Status.INFO, resp.prettyPrint());
		
		System.out.println("__________________________________________________________________________________________________________________");
	}

}
