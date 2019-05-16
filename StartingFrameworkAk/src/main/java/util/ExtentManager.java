package util;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

//import base.BaseTest1;

public class ExtentManager {

	static ExtentReports extent;
	public static String repFolder;

	public static ExtentReports getInstance(String path) {
		if (extent == null) {
			// System.out.println(path+"\\report.html");
			// createInstance(path+"\\report.html");

			// for better report config:
			Date date = new Date();
			String folderName = date.toString().replace(":", "_").replace(" ", "_");
			new File(path + "\\" + folderName + "\\log").mkdirs(); // new folder and a new file will be created for report
																	
			System.out.println("Directory should have created:- " + path + "\\" + folderName + "\\log");

			// BaseTest1.
			repFolder = path + "\\" + folderName; // #to clear
			String filename = path + "\\" + folderName + "\\report.html";

			createInstance(filename);
		}

		return extent;
	}

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.setAppendExisting(true); // func adds new tests to the existing report file along with previous ones
		// without this function, new test rep would have overwritten the older ones in
		// same file, though not ideal this way

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}

}
