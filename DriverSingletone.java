package by.htp.test.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSingletone {
	
	private static final String CHROME = "webdriver.chrome.driver";
	private static final String CHROME_PATH = "D:\\driver\\chromedriver.exe";
	
	private static WebDriver driver;
	
	static{
		System.setProperty(CHROME, CHROME_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	}
	
	private DriverSingletone(){		
	}
	
	public static WebDriver getDriver(){
		return driver;
	}

}
