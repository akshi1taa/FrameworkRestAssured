package tests;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.Test;

import base.BaseTest1;
import base.Category;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddCategory extends BaseTest1 {

	@Test(dataProvider="getData")
	public void addCat(Hashtable<String,String> data) {
		
		System.out.println(data.get("CategoryName"));
		String catname=data.get("CategoryName");
		
		//doLogin("seleniumtraining10@gmail.com", "trivia45");
		Category cat = new Category();
		cat.setCategoryname(catname);

		Response resp = given().contentType(ContentType.JSON).headers("sessionid",sessionid)
				.log().all().body(cat).post();
		
		resp.prettyPrint();
		System.out.println("__________________________________________________________________________________________________________________");
	}

}
