package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Temptest {
	
	SoftAssert softasrt;
	
	@BeforeTest
	public void beforeTest()
	{
		softasrt=new SoftAssert();
	}
	
	
	public void reportFailure(String errMsg,boolean stop)
	{
				softasrt.fail(errMsg);
				
				if(stop)
					softasrt.assertAll();	//if assert all not called, the test in console show passed(just the msg will be printed)
	}
	
	@Test
	public void test01()
	{
		reportFailure("error",false);
		reportFailure("error",false);
		reportFailure("error",false);
		reportFailure("error" ,false);
		
		softasrt.assertAll();//hence, if its passed here, then test will also show error.
	}

}
