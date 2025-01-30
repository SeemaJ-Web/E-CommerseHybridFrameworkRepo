package e_Com.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import e_Com.utils.ExtentReporter;
import e_Com.utils.Utility;


public class MyListeners implements ITestListener {
	ExtentReports extentReports;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		 extentReports = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getName();
		 extentTest = extentReports.createTest(testname);
		extentTest.log(Status.INFO,testname + "Test Execution Started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getName();
		extentTest.log(Status.PASS, testname + "Test Successfully Executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getName();
		
		WebDriver driver = null;
		
		try {
			 driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
		String destPath = Utility.captureScreenShot(driver, testname);
		
		extentTest.addScreenCaptureFromPath(destPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL,testname + "Test Got Faild");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testname + "Test Got Skipped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
		
		String pathOfExtendsReport= System.getProperty("user.dir")+"\\test-output\\ExtentReport.html";
		File extentRepo = new File(pathOfExtendsReport);
		
		try {
			Desktop.getDesktop().browse(extentRepo.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
