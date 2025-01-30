package e_Com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports generateExtentReport() {
		//we are calling extentReporter
		ExtentReports extentreports = new ExtentReports();
		
		//this below line will create me a folder in the test-output folder
		File extentReportFile = new File(System.getProperty("user.dir")+ "/test-output/ExtentReport.html");
		
		//using extentSparkReporter for betterReport Purpose you find it by yourself
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentReportFile);
		
		//set the name Theme and timestamp using extentSparkReporter
		sparkreporter.config().setTheme(Theme.STANDARD);
		sparkreporter.config().setReportName("E-Commerse Regression Report");
		sparkreporter.config().setDocumentTitle("E-Com Automation Report");
		sparkreporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		//Attatch the spark reporter to extent reporter
		extentreports.attachReporter(sparkreporter);
		
		//.to show all the reports we are writing this code below
		// now as we've already stored some imp data into the property files
		// to get that data will have to create a constructor of config.property
		
		Properties configprop = new Properties();
		File configfile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\e_Com\\configue\\configue.properties");
		 
		try {
			FileInputStream configfiles = new FileInputStream(configfile);
			configprop.load(configfiles);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
		//After writing this statement above file will give us checked exception will handle it by try/catch block
		extentreports.setSystemInfo("Browser Name", configprop.getProperty("browserName"));
		extentreports.setSystemInfo("Valid Email", configprop.getProperty("ValidEmail"));
		extentreports.setSystemInfo("Valid PASS", configprop.getProperty("ValidPass"));
		
		//these lines comes from system.getProperties().List(System.out); this will return us few functions 
		// which we are using for our extentReport Why we are not writing it by ourself coz these are already present in the computer
		// and we'r just fetching them automatically
		extentreports.setSystemInfo("Valid PASS", System.getProperty("os.name"));
		extentreports.setSystemInfo("Valid PASS", System.getProperty("user.name"));
		extentreports.setSystemInfo("Valid PASS", System.getProperty("java.version"));
		
		return extentreports;
	}

}
