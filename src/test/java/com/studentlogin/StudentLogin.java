package com.studentlogin;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.baseclass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StudentLogin extends BaseClass {
	String ExcelPath = "C:\\Users\\iamma\\Downloads\\student_basic _database.xlsx";
	WebDriver driver;

	@Test(priority = 1)
	public void browserLaunch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("http://maplogik.com/index.php/student/login");
		
		driver.manage().window().maximize();
	}

	@Test(priority = 2)
	public void studentActivateChrome() throws AWTException, InterruptedException, IOException {

		for (int i = 1346; i <= 2000; i++) {

			String studentID = getDataFromExcel(ExcelPath, "Student Database", i, 0);
//			String mobileNumberStudent = getDataFromExcel(ExcelPath, "Student Database", i, 3);

			WebElement studentIdTxt = driver.findElement(By.id("login-student-id"));
			studentIdTxt.sendKeys(studentID);

			WebElement studentMobNoTxt = driver.findElement(By.id("login-mobile"));
			studentMobNoTxt.sendKeys("9887676565", Keys.ENTER);

			WebElement otpReq = driver.findElement(By.id("test_otp"));
			WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driverWait.until(ExpectedConditions.visibilityOf(otpReq));

			String otpText = otpReq.getText();
			WebElement otpEnter = driver.findElement(By.id("login-otp"));
			otpEnter.sendKeys(otpText, Keys.ENTER);

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			WebElement paynowClk = driver.findElement(By.id("rzp-button1"));
			executor.executeScript("arguments[0].scrollIntoView(true);", paynowClk);
			executor.executeScript("arguments[0].click()", paynowClk);

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

			driver.switchTo().frame(0);

			WebElement netbankingClk = driver.findElement(By.xpath("//div[text()='Netbanking']"));
			executor.executeScript("arguments[0].scrollIntoView(true);", netbankingClk);
			netbankingClk.click();
			WebElement hdfcClick = driverWait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("(//label[@class='radio-label mfix'])[1]")));
			hdfcClick.click();

			WebElement payynowClk = driverWait
					.until(ExpectedConditions.elementToBeClickable(By.id("redesign-v15-cta")));
//			executor.executeScript("arguments[0].scrollIntoView(true);", payynowClk);
			Thread.sleep(1000);
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			executor.executeScript("arguments[0].click()", payynowClk);

			Set<String> windowHandles = driver.getWindowHandles();
			List<String> li = new LinkedList<String>();
			li.addAll(windowHandles);
			driver.switchTo().window(li.get(1));

			WebElement successClk = driver.findElement(By.xpath("//button[text()='Success']"));
			successClk.click();

			driver.switchTo().window(li.get(0));
			Thread.sleep(6000);
			driver.findElement(By.xpath("//span[text()='Dashboard']")).click();
			Thread.sleep(3000);
			WebElement logoutClk = driverWait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Log out']")));
			executor.executeScript("arguments[0].click()", logoutClk);
			System.out.println(studentID);
		}

		driver.quit();

	}
}