package com.markschecker;

import java.io.IOException;
import java.time.Duration;

import org.baseclass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class marksCheck extends BaseClass {
	static String adminEmail = "admin@gmail.com";
	static String adminPass = "Admin@123";
	String excelPath = "C:\\Users\\iamma\\OneDrive\\Desktop\\10000-11k marks.xlsx";
	String sheetName = "year1";
	static WebDriver driver;

	@Test(priority = 1)
	public void marksCheck() throws IOException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://maplogik.com/index.php/student/login");
		driver.manage().window().maximize();
		for (int i = 1; i <= 2; i++) {

			String studentID = getDataFromExcel(excelPath, sheetName, i, 0);
//	String mobileNumberStudent = getDataFromExcel(ExcelPath, "Student Database", i, 3);

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

			WebElement popUp = driverWait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn-close']")));
			popUp.click();
			WebElement academicClick = driver.findElement(By.xpath("//span[text()='Academic Info']"));
			academicClick.click();
			String semMarkExcel = getDataFromExcel(excelPath, sheetName, i, 5);
			WebElement firstSem = driver.findElement(By.xpath("(//td)[8]"));
			String semOneMarkDB = firstSem.getText();

			if (semMarkExcel.equals(semOneMarkDB)) {

				System.out.println(
						studentID + " " + "excelSemmark=" + semMarkExcel + " " + "maplogiksem1=" + semOneMarkDB);

			} else {
				System.out.println("marks doesnot match");
			}

		}

		driver.findElement(By.xpath("//span[text()='Log out']")).click();
	}
}