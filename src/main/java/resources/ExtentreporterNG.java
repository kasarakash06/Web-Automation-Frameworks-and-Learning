package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentreporterNG {
	
	
	//for report config
	
	
	
		public static ExtentReports getReportObject()
		{
			// Extentreports (main class for generating and attaching to test) and
					// ExtentSparkReporter (Helper class To create and config report and it report
					// to ExtenetReporter class)

					String path = System.getProperty("user.dir") + "//Extent Reports//index.html";
					ExtentSparkReporter reporter = new ExtentSparkReporter(path);
					reporter.config().setDocumentTitle("Test Result");
					reporter.config().setReportName("Automaion testing results");

					ExtentReports extent = new ExtentReports();
					extent.attachReporter(reporter); // accepts object of ExtentSparkReporter
					extent.setSystemInfo("Tester", "Akash");
					extent.createTest(path);
					return extent;
				
		}
		
		

}
