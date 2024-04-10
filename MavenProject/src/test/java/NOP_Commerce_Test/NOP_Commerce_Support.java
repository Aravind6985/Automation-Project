package NOP_Commerce_Test;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NOP_Commerce_Support {
	
	WebDriver driver;
	
	public NOP_Commerce_Support(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String randomstr()
	{
		String rand_str=RandomStringUtils.randomAlphabetic(10);
		return rand_str;
	}
	
	public void datepicker( int i, String Month_Year) throws InterruptedException
	{
		By calender=By.xpath("//*[@id='customer-info']/div[2]/div[6]/div[2]/span[1]/span/span");
		By r_arrow=By.xpath("//*[@id='DateOfBirth_dateview']/div/div/a[3]");
		By l_arrow=By.xpath("//*[@id='DateOfBirth_dateview']/div/div/a[1]/span");
		By cntre=By.xpath("//*[@id='DateOfBirth_dateview']/div/div/a[2]");
		By calender_table=By.xpath("//*[@id='DateOfBirth_dateview']/div/div[2]/table/tbody/tr/td/a[contains(text(),'"+i+"')]");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(calender).click();
		Thread.sleep(100);
		while(true)
		{
			Thread.sleep(100);
			String actual=driver.findElement(cntre).getText();
			if(Month_Year.equalsIgnoreCase(actual))
				break;
			else
				driver.findElement(l_arrow).click();
		}
		//Thread.sleep(500);
		int links=driver.findElements(calender_table).size();
		for(int j=0;j<links;)
		{
			//Thread.sleep(500);
			List<WebElement>day=driver.findElements(calender_table);
			WebElement daysel=day.get(j);
			daysel.click();
			break;
		}
		
	}

}
