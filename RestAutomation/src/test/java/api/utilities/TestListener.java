package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener extends extentReport implements ITestListener{
	
	ExtentReports extent=extentReport.extentReport();
	ExtentTest Test;
	@Override
    public void onTestStart(ITestResult result) {
		Test=extent.createTest(result.getName());
       
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test succeeded: " + result.getName());
        Test=extent.createTest(result.getName());
        Test.log(Status.PASS, "Test PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        
        Test=extent.createTest(result.getName());
        Test.log(Status.FAIL, "Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	Test=extent.createTest(result.getName());
        Test.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
    	extent.flush();
    }
}
