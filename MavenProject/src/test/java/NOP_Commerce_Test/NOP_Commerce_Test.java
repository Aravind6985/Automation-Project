package NOP_Commerce_Test;

import java.awt.AWTException;
import java.io.FileNotFoundException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import NOP_Commerce_Page.NOP_Commerce_Page;

public class NOP_Commerce_Test extends NOP_Commerce_Base{
	
	@Test (priority = 2)
	public void Add_Customer() throws InterruptedException
	{
		test=report.createTest("Test:- Add new Customer");
		NOP_Commerce_Page nop=new NOP_Commerce_Page(driver);
		NOP_Commerce_Support sup=new NOP_Commerce_Support(driver);
		nop.cust();
		String email=sup.randomstr()+"@gmail.com";
		String pass=sup.randomstr()+"@123";
		String fname=sup.randomstr();
		String lname=sup.randomstr();
		nop.cust_dtls(email, pass, fname, lname);
		nop.gender("male");
		nop.cmpny_dtls("ABC Company");
		nop.vendor_mngr("Vendor 2");
		nop.newsletter("Test store 2");
		nop.cust_role("guests");
		nop.comments("Adding new customer.....");
		sup.datepicker(25,"July 2018");
		nop.save("The new customer has been added successfully");
		test.pass("New Customer Added Successfully");
	}
	
	@Test (priority = 1)
	public void Add_Catagory() throws InterruptedException, FileNotFoundException, AWTException
	{
		test=report.createTest("Test:- Add new Catagory");
		NOP_Commerce_Page nop=new NOP_Commerce_Page(driver);
		nop.catalog();
		nop.catalog_dtls("Shirt");
		nop.catalog_ctgry("Apparel");
		nop.fileupload();
		nop.catg_save("The new category has been added successfully");
		test.pass("New Catagory Added Successfully");
	}
	
	@AfterMethod
	public void Finish(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(Status.FAIL, "Test Case Failed: "+result.getName());
			test.error("ERROR!!!");
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
			test.log(Status.PASS, "Test Case Passed: "+result.getName());
		
		else if(result.getStatus()==ITestResult.SKIP)
			test.log(Status.SKIP, "Test Case Skipped: "+result.getName());
		
		System.out.println("Test Completed");
	}
	
	@AfterTest
	public void quit()
	{
		test.info("Test Completed");
		report.flush();
		driver.quit();
	}
	

	
}
