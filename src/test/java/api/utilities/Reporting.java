package api.utilities;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{
 

 public ExtentReports extent;
 public ExtentTest logger;
 public ExtentSparkReporter sparkReporter;
 public ExtentTest test;
 
  
 String repName;
 
 public void onStart(ITestContext testContext)
 {
	 
	 String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	 repName="Test_Report-"+timeStamp +".html";
	 sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/"+repName);// to specify the report  location
	 
	 
	 sparkReporter.config().setDocumentTitle("RestAssured Automation Project");//title of the report 
	 sparkReporter.config().setReportName("PetStoreUsersAPI");//Name of the report 
	 
	 sparkReporter.config().setTheme(Theme.DARK);
	 
	 extent=new ExtentReports();
	 extent.attachReporter(sparkReporter);
	 extent.setSystemInfo("Application", "PetStoreUsersAPI");
	 extent.setSystemInfo("Opearting System", System.getProperty("os.name"));
	 extent.setSystemInfo("User anme", System.getenv("user.name"));
	 extent.setSystemInfo("Envirnamet", "QA");
	 
	 extent.setSystemInfo("User", "Hareesh");
	 
	 
	 
	  }
 
 public void onTestSuccess(ITestResult result)
 
 {
	 
	 
	 test=extent.createTest(result.getName());
	 test.assignCategory(result.getMethod().getGroups());
	 test.createNode(result.getName());
	 test.log(Status.PASS, "Test Passed");
	 
 }
 public void onTestFailure(ITestResult result)
 
 {
	 test=extent.createTest(result.getName());
	 test.createNode(result.getName());
	 test.assignCategory(result.getMethod().getGroups());
	 test.log(Status.FAIL, "Test Failed");
	 test.log(Status.FAIL, result.getThrowable().getMessage());
	 
 }
 
 public void onTestSkipped(ITestResult result)
 
 {
	 test=extent.createTest(result.getName());
	 test.createNode(result.getName());
	 test.assignCategory(result.getMethod().getGroups());
	 test.log(Status.SKIP, "Test SKIPPED");
	 test.log(Status.SKIP, result.getThrowable().getMessage());
	 
 }
 
 
public void onFinish(ITestContext testcontext)
 
 {

	 extent.flush();
 }
}