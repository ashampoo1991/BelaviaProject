package by.htp.test.simple;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.velocity.test.provider.Child;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import by.htp.test.driver.DriverSingletone;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

@Test
public class TestBelavia {

	// private static final String CHROME = "webdriver.chrome.driver";
	// private static final String CHROME_PATH = "D:\\driver\\chromedriver.exe";

	private WebDriver driver;

	WebElement from;
	WebElement to;
	WebElement oneWay;
	WebElement calendar;
	WebElement date;
	WebElement submit;
	WebElement openCalendar;
	// WebElement ticketDate;
	WebElement ticketPrice;
	WebElement nextPage;

	// @Test(dataProvider = "dp")
	// public void f(Integer n, String s) {
	// }

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	// @DataProvider
	// public Object[][] dp() {
	// return new Object[][] {
	// new Object[] { 1, "a" },
	// new Object[] { 2, "b" },
	// };
	// }
	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {

		driver = DriverSingletone.getDriver();

	}

	@AfterSuite
	public void afterSuite() {

		// driver.quit();
		// driver.close();
	}

	@Test
	public void initialSearch() {

		driver.get("https://belavia.by");

		from = driver.findElement(By.name("OriginLocation_Combobox"));
		from.sendKeys("msq");
		from.sendKeys(Keys.ENTER);

		to = driver.findElement(By.name("DestinationLocation_Combobox"));
		to.sendKeys("ri");
		to.sendKeys(Keys.ENTER);

		oneWay = driver.findElement(By.xpath("//*[@id='step-2']/div[1]/div/label[1]"));
		oneWay.click();

		calendar = driver.findElement(By.xpath("//*[@id=\"step-2\"]/div[2]/div[1]/div/a"));
		calendar.click();

		date = driver.findElement(By.xpath("//*[@id=\"calendar\"]/div/div[2]/table/tbody/tr[1]/td[3]/a"));
		date.click();

		submit = driver.findElement(By.xpath("//*[@id=\"step-2\"]/div[4]/div/button"));
		submit.click();

	}

	@Test(dependsOnMethods = { "initialSearch" })
	public void getTicketsCalendar() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"outbound\"]/div[1]/div/div[2]/a")));
		openCalendar = driver.findElement(By.xpath("//*[@id=\"outbound\"]/div[1]/div/div[2]/a"));

		Thread.sleep(1000);

		// ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+openCalendar.getLocation().y+")");
		// System.out.println("element is clickable");

		openCalendar.click();

	}

	@Test(dependsOnMethods = { "getTicketsCalendar" })
	public void getTicketsInfo() throws InterruptedException {

		int i = 0;
		
//		String y = null;
//		String x = null;

		while (i < 40) {

			List<WebElement> ticketDate = driver.findElements(By.xpath("//span[@class='date']"));

			for (WebElement td : ticketDate) {

				// if (ticketPriceValue = driver.findElement(By.xpath("//div[@class='price']"));

				String tdText = td.getText();

				if (!tdText.endsWith("июл")) {

					System.out.println(tdText);
//					y = tdText;
					i++;
					// System.out.println(tpText);
					// ticketPrice = driver.findElement(By.xpath("//span[@class='date']/.."));
					// String tpId = ticketPrice.getAttribute("id").replace("outbound_", "");
					// if (!tpId.startsWith("18-07")) {
					// System.out.println(tpId);
					// }
				}

			}

			Thread.sleep(500);
			nextPage = driver.findElement(By.className("icon-right-open"));

			nextPage.click();

			// List<WebElement> ticketDateId =
			// driver.findElements(By.xpath("//div[@id='outbound*']/.."));
			// for (WebElement tdid : ticketDateId) {
			// String x = tdid.getAttribute("id").replace("outbound_", "");
			// if (!x.startsWith("18-07")) {
			// System.out.println(tdid);
			// }//*[@id="matrix"]/div[5]/div/div[2]/div/label/text()
			
			List<WebElement>ticketPrices = driver.findElements(By.xpath("//*[@id='matrix']/div/div/div[2]/div/label"));
			
			for (WebElement tp : ticketPrices)
			{
				String tpText = tp.getText();
//				x = tpText;
				System.out.println(tpText);
			}
			
//			for (int p = 0; p<ticketPrices.size(); p++) {
//				System.out.println(y);
//				System.out.println(x);
//			}
		}
		System.out.println(i);
	}
}
