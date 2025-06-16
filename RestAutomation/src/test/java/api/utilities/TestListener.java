package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
    
    private static ExtentReports extent = ExtentReport.getInstance();
    private ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(testName);
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = testThread.get();
        if (test != null) {
            test.log(Status.PASS, "Test PASSED: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testThread.get();
        if (test != null) {
            test.log(Status.FAIL, "Test FAILED: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = testThread.get();
        if (test != null) {
            test.log(Status.SKIP, "Test SKIPPED: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}