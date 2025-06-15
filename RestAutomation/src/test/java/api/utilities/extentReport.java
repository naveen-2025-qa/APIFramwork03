package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReport {

	
	public static ExtentReports extentReport()
	{
		String Path="";
		ExtentSparkReporter reporter =new ExtentSparkReporter(Path);
		reporter.config().setReportName("APITest automation");
		
		ExtentReports report=new ExtentReports();
		report.attachReporter(reporter);
		ExtentTest Test=report.createTest("");
		return report;
	}
}
