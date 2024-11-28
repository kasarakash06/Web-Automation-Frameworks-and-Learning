package wizehub.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentreporterNG;

//ITestlistners is an interface from testNG class also extending BaseTest for screenshot code
public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentreporterNG.getReportObject();
	ExtentTest test; // holds entry about test case in report

	// test object we are making synchronized
	ThreadLocal<ExtentTest> extentTests = new ThreadLocal<ExtentTest>(); // no matter if u run concurrently each 'test'
																			// object creation have its own thread (id)
																			// and it won't interrupt other overriding
																			// variable.
//now we will push test object to threadLocal object reference in below method using Set()

	@Override
	public void onTestStart(ITestResult result) // result will hold information about test which is going to get
												// executed
	{ // before starting test control will go here in this method

		test = extent.createTest(result.getMethod().getMethodName());// dynamic naming or else we can give testName
		extentTests.set(test); // it will assign unique thread id (also picks running test unique id), so it
								// knows which test is actually pushing into that set() and it creates a map
		// id (ErrorvValidationTest->test)

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		// test.log(Status.PASS, "Test is passed");
		extentTests.get().log(Status.PASS, "Test is passed"); // get will check which thread id its asking to get
																// information which is set
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// test.log(Status.FAIL, "Test is failed");

		extentTests.get().fail(result.getThrowable()); // here test.fail is there, throwable will print error name in result report

		// here we got driver from the particular method which it has life of
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			// Getting class
			// from TestNG XML then to real class submit order then field driver then get
			// that instance
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// take screenshot
		// attach it to report
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extentTests.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); // path of screenshot saved in
																						// project, name of screenshot
		// test.addVideoFromPath(null, null) //can attach video also
//YOUR TESTNG.XML SHOULD KNOW ABOUT YOUR LISTNER FILE 
	}

	/*
	 * @Override public void onTestSkipped(ITestResult result) {
	 * 
	 * }
	 * 
	 * @Override public void onTestFailedButWithinSuccessPercentage(ITestResult
	 * result) {
	 * 
	 * }
	 * 
	 * @Override public void onTestFailedWithTimeout(ITestResult result) {
	 * 
	 * }
	 * 
	 * @Override public void onStart(ITestContext context) { //before running
	 * execution of all test methods
	 * 
	 * }
	 */

	@Override
	public void onFinish(ITestContext context) {// after running execution of all test methods

		extent.flush();
	}

}
