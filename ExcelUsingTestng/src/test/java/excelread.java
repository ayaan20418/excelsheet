import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class excelread {
	
	WebDriver driver;
	
@Test(dataProvider="passing")

public void enterthecredential(String username,String password) throws InterruptedException {

	    WebDriverManager.chromedriver().setup();
		
		driver =new ChromeDriver();
		 
		driver.get("http://demosite.center/wordpress/wp-login.php");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();

		driver.findElement(By.id("user_login")).sendKeys(username);

		driver.findElement(By.id("user_pass")).sendKeys(password);
						
		Thread.sleep(8000);
		
		System.out.println(driver.getTitle());
		
	}
	
@AfterMethod
public void teardown() throws InterruptedException {
	
	Thread.sleep(5000);
	
	driver.quit();
}
	
@DataProvider(name="passing")
	public Object[] [] passdata(){
	
	ExcelReadConfig config=new ExcelReadConfig("C:\\eclipse\\Frame\\ExcelTng\\testdata\\demo.xlsx");
	
	int rows=config.getRowCount(0);
	
	Object[] [] data=new Object[rows][2];
	
	for(int i=0;i<rows;i++) {
		
		data[i][0]=config.getData(0, i, 0);
		
		data[i][1]=config.getData(0, i, 1);
				
	}
	
	return data;	
	
}
}
