package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import core.BaseTest;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TestListenerReporter extends CaptureScreenShot implements ITestListener {


    private ExtentTest test;
    private String actualImageName;
    private static ExtentReports extent;
    private static Calendar calendar;
    private static SimpleDateFormat formatter;


    public TestListenerReporter() {
    }


    static {
        calendar = Calendar.getInstance();
        formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        extent = new ExtentReports(System.getProperty("user.dir")+"\\src\\test\\java\\com\\reports\\"
                                   + formatter.format(calendar.getTime()) + ".html", false);
    }


    @Override
    public void onTestFailure(ITestResult result) {


            if (result.getStatus() == ITestResult.FAILURE) {
                try {

                    actualImageName = takeScreenShot(result.getTestContext().getName());                                   }
                catch (IOException e) {
                    e.printStackTrace(); }
                test.log(LogStatus.FAIL, result.getName() + " test is failed" + result.getThrowable());
                test.log(LogStatus.FAIL, test.addScreenCapture(actualImageName));
            } }


    @Override
    public void onStart(ITestContext context) {
        test = extent.startTest(context.getName());
        test.log(LogStatus.INFO, context.getName() + " test Started");
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, result.getName() + " test is pass");
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
        }
    }


}


