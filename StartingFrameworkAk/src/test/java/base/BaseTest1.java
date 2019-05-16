package base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;

import util.DataUtil;
import util.ExtentManager;
import util.Xls_Reader;

public class BaseTest1 {

	public static String sessionid;
	public Xls_Reader xl;
	public SoftAssert softasrt = new SoftAssert();
	public Properties prop;

	public ExtentReports exRep;
	public static String repFolder;
	public ExtentTest test;
	public int iteration;

	public static StringWriter requestWriter;
	public static PrintStream requestCapture;

	@BeforeTest
	public void init() {
		prop = new Properties();
		try // if not in try, system will force to do so, as exception handling in files
			// required
		{
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "//src//test//resources//project.properties");

			prop.load(fs); // loading the properties file
		}

		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		xl = new Xls_Reader(prop.getProperty("xlspath"));
		RestAssured.baseURI = prop.getProperty("baseUrl");

		String testname = this.getClass().getSimpleName().toLowerCase(); // hence, in properties, property name was kept
																		// same as class name.So that, it
		RestAssured.basePath = prop.getProperty(testname); 				// will provide the respective path to tests
	}

	@BeforeMethod
	public void report() {
		iteration++;
		exRep = ExtentManager.getInstance(prop.getProperty("reportPath"));

		repFolder = ExtentManager.repFolder; // changed!

		test = exRep.createTest("Login");

		requestWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true); // # what is this statement doing?
		
	}

	@AfterMethod
	public void after() {
		exRep.flush(); // # //solved!
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData1(xl, this.getClass().getSimpleName()); // 2nd argument will return the name of class
																		// that call this function

	}

	public void reportFailure(String errMsg, boolean stop) {
		softasrt.fail(errMsg);

		if (stop)
			softasrt.assertAll();
	}

	public void addReqLinkToReport(String msg, String fileName, String content) {
		System.out.println("hey from base test, repFolder=" + repFolder);

		try // new folder named log created inside the date wala folder
		{
			System.out.println(repFolder + "\\log\\" + fileName + ".html");
			new File(repFolder + "\\log\\" + fileName + ".html").createNewFile();		//#
		} 
			

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileWriter fw;

		try {
			fw = new FileWriter(repFolder + "\\log\\" + fileName + ".html"); // # //changing path here changed solved
																				// above issue..still req.:to have
																				// better idea
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
