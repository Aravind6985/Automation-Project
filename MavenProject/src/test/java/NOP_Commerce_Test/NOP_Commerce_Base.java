package NOP_Commerce_Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import NOP_Commerce_Page.NOP_Commerce_Page;

public class NOP_Commerce_Base {
	
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void Base()
	{
		driver=new EdgeDriver();
		driver.get("https://admin-demo.nopcommerce.com/login");
		driver.manage().window().maximize(); 
		NOP_Commerce_Page nop=new NOP_Commerce_Page(driver);
		test=report.createTest("Test:- Login");
		nop.login("admin@yourstore.com", "admin");
		nop.login_cnfrm("Dashboard / nopCommerce administration");
		test.pass("Login & Title Verification Passed");
	}
	
	@BeforeTest
	public void report()
	{
		reporter=new ExtentHtmlReporter("./Report/NOP_Commerce_Report.html");
		reporter.config().setDocumentTitle("Automation Project Report");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("NOP Commerce");
		report=new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester Name: Aravind", "Project: NOP Commerce Automation Project");
		report.setSystemInfo("OS: Win 8.1", "BrowserName: Edge");
	}

	
}
