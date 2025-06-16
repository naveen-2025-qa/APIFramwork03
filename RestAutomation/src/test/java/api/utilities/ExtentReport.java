package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            String path = System.getProperty("user.dir") + "/reports/ExtentReport.html"; // Set a proper path
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("API Test Automation");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
