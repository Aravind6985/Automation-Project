package NOP_Commerce_Page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class NOP_Commerce_Page {
	
	WebDriver driver;
	
	//Add new customer locators
	By log_email=By.name("Email");
	By log_pass=By.name("Password");
	By log_login=By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button");
	By customer=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[4]/a");
	By customer_cust=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[4]/ul/li[1]/a/p");
	By customer_add=By.xpath("/html/body/div[3]/div[1]/form[1]/div/div/a/i");
	By cust_email=By.name("Email");
	By cust_pass=By.name("Password");
	By cust_fname=By.name("FirstName");
	By cust_lname=By.name("LastName");
	By cust_gendermale=By.xpath("//*[@id=\"Gender_Male\"]");
	By cust_genderfemale=By.xpath("//*[@id=\"Gender_Female\"]");
	By cust_cmpny=By.name("Company");
	By cust_tax=By.name("IsTaxExempt");
	By cust_comment=By.name("AdminComment");
	By cust_manager=By.name("VendorId");
	By cust_news=By.xpath("//*[@id=\"customer-info\"]/div[2]/div[9]/div[2]/div/div[1]/div/div");
	By cust_news1=By.xpath("//li[contains(text(),'Your store name')]");
	By cust_news2=By.xpath("//li[contains(text(),'Test store 2')]");
	By cust_role=By.xpath("//*[@id=\"customer-info\"]/div[2]/div[10]/div[2]/div/div[1]/div/div");
	By cust_role1=By.xpath("//li[contains(text(), 'Administrators')]");
	By cust_role2=By.xpath("//li[contains(text(), 'Forum Moderators')]");
	By cust_role3=By.xpath("//li[contains(text(), 'Guests')]");
	By cust_role4=By.xpath("//li[contains(text(), 'Registered')]");
	By cust_role5=By.xpath("//li[contains(text(), 'Vendors')]");
	By save_btn=By.xpath("/html/body/div[3]/div[1]/form/div[1]/div/button[1]/i");
	
	//Add new catalog locators
	By catalog=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[2]/a");
	By catalog_catg=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[2]/ul/li[2]/a/p");
	By catalog_add=By.xpath("/html/body/div[3]/div[1]/div[1]/div/a");
	By cat_name=By.name("Name");
	By cat_desc=By.xpath("//*[@id=\"Description_ifr\"]");
	By cat_parent=By.xpath("//*[@id=\"ParentCategoryId\"]");
	By cat_upload=By.xpath("/html/body/div[3]/div[1]/form/section/div/div/nop-cards/nop-card[1]/div/div[2]/div[3]/div[2]/div[2]/div[2]/div[1]/div/div");
	By cat_save=By.name("save");
	
	public NOP_Commerce_Page(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void login(String email, String pass)
	{
		driver.findElement(log_email).clear();
		driver.findElement(log_email).sendKeys(email);
		driver.findElement(log_pass).clear();
		driver.findElement(log_pass).sendKeys(pass);
		driver.findElement(log_login).click();
	}
	
	public void login_cnfrm(String title)
	{
		String expected=title;
		String actual=driver.getTitle();
		Assert.assertEquals(expected, actual);
	}
	
	public void cust() throws InterruptedException
	{
		driver.findElement(customer).click();
		Thread.sleep(1000);
		driver.findElement(customer_cust).click();
		driver.findElement(customer_add).click();
	}
	
	public void cust_dtls(String email, String pass, String fname, String lname)
	{
		driver.findElement(cust_email).sendKeys(email);
		driver.findElement(cust_pass).sendKeys(pass);
		driver.findElement(cust_fname).sendKeys(fname);
		driver.findElement(cust_lname).sendKeys(lname);
	}
	
	public void gender(String gender) throws InterruptedException
	{
		if(gender.equalsIgnoreCase("male"))
			driver.findElement(cust_gendermale).click();
		else if(gender.equalsIgnoreCase("female"))
			driver.findElement(cust_genderfemale).click();
	}
	
	public void cmpny_dtls(String cmpny)
	{
		driver.findElement(cust_cmpny).sendKeys(cmpny);
		driver.findElement(cust_tax).click();
	}
	
	public void vendor_mngr(String mngr)
	{
		WebElement vendor=driver.findElement(cust_manager);
		Select dd=new Select(vendor);
		dd.selectByVisibleText(mngr);
	}
	
	public void newsletter(String news) throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(cust_news).click();
		Thread.sleep(2000);
		if(news.equalsIgnoreCase("Your store name"))
			driver.findElement(cust_news1).click();
		else if(news.equalsIgnoreCase("Test store 2"))
			driver.findElement(cust_news2).click();
	}
	
	public void cust_role(String role) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String actual_role=driver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[1]")).getText();
		if(!role.equalsIgnoreCase(actual_role))
		{
			driver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
			driver.findElement(cust_role).click();
			Thread.sleep(1000);
			WebElement list_role = null;
			if(role.equalsIgnoreCase("Administrators"))
				list_role=driver.findElement(cust_role1);
			
			else if(role.equalsIgnoreCase("Forum Moderators"))
				list_role=driver.findElement(cust_role2);
			
			else if(role.equalsIgnoreCase("Guests"))
				list_role=driver.findElement(cust_role3);
			
			else if(role.equalsIgnoreCase("Vendors"))
				list_role=driver.findElement(cust_role5);
			
			else 
				driver.findElement(cust_role4);
			list_role.click();
		}
		
	}
	
	public void comments(String comment)
	{
		driver.findElement(cust_comment).sendKeys(comment);
	}
		
	public void save(String message) throws InterruptedException
	{
		driver.findElement(save_btn).click();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(message));
		System.out.println("New Customer added successfully");
	}
	
	public void catalog() throws InterruptedException
	{
		driver.findElement(catalog).click();
		Thread.sleep(2000);
		driver.findElement(catalog_catg).click();
		driver.findElement(catalog_add).click();
	}
	
	public void catalog_dtls(String name) throws InterruptedException
	{
		Thread.sleep(2500);
		driver.findElement(cat_name).sendKeys(name);
	}
	
	public void catalog_ctgry(String parent)
	{
		WebElement category=driver.findElement(cat_parent);
		Select dd=new Select(category);
		dd.selectByVisibleText(parent);
	}
	
	public void fileupload() throws InterruptedException, FileNotFoundException, AWTException
	{
		driver.findElement(cat_upload).click();
		Thread.sleep(2000);
		StringSelection file=new StringSelection("E:\\Luminar\\Software testing Class Notes\\10-Automation Project\\Shirt.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);
		Robot bot=new Robot();
		bot.keyPress(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_V);
		bot.keyRelease(KeyEvent.VK_V);
		bot.keyRelease(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		bot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void catg_save(String message) throws InterruptedException
	{
		driver.findElement(cat_save).click();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(message));
		System.out.println("New Category added successfully");
	}

}
