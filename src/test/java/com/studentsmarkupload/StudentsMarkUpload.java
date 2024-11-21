package com.studentsmarkupload;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.baseclass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StudentsMarkUpload extends BaseClass {

	@Test(priority = 1)
	public static String getPropertyFile(String key) throws Exception, Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream(
				"C:\\Users\\pc\\eclipse-workspace\\automation\\SKillEnhancement\\Config\\config.properties"));
		return (String) properties.get(key);

	}

	@Test(priority = 2)
	public static String getExcelPath() throws Exception {
		File file = new File(getPropertyFile("StartingPath") + getPropertyFile("Course_Category") + "\\" + getPropertyFile("District")
				+ "\\" + getPropertyFile("College") + "\\" + getPropertyFile("Type") + "\\"
				+ getPropertyFile("Department") + "\\" + getPropertyFile("Year"));
		String excelPath = String.valueOf(file);
		return excelPath;

	}

	@Test(priority = 3)
	public static String getSemester() throws Exception {
		File file = new File(getExcelPath() + "\\");
		String string = String.valueOf(file);
		return string;

	}

	@Test(priority = 4)
	public void marksUpload() throws Exception {
		String semExcel = "semester_";
		String semesterSlt = "Semester";
		String semNumber = getPropertyFile("Semester");
		Integer intSemNumber = Integer.parseInt(semNumber);
		String propFileClgName = getPropertyFile("College");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get(getPropertyFile("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement emailTxt = driver.findElement(By.id("login-email"));
		emailTxt.sendKeys(getPropertyFile("UserName"));
		WebElement passwordTxt = driver.findElement(By.id("login-password"));
		passwordTxt.sendKeys(getPropertyFile("Password"));
		WebElement loginSubmitClk = driver.findElement(By.xpath("//button[text()='Log in']"));
		loginSubmitClk.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement clgActivation = driver.findElement(By.xpath("//span[text()='College Activation']"));
		clgActivation.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement search = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type=\"search\"]")));
		search.sendKeys(propFileClgName);
		WebElement clgName = driver.findElement(By.xpath("(//a[@class=\"college_hyper\"])[1]"));
		String clgNameTxt = clgName.getText();
		if (clgNameTxt.equals(propFileClgName)) {
			WebElement importmarks = driver.findElement(By.xpath("//a[@title=\"Import Marks\"]"));
			importmarks.click();
		} else {
			System.out.println("college match not found");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		for (int i = 1; i <= intSemNumber; i++) {
			WebElement type = driver.findElement(By.xpath("//select[@id=\"course_type\"]"));
			type.sendKeys(getPropertyFile("Type"));

			WebElement courseSlt = driver.findElement(By.xpath("//select[@id=\"course_name\"]"));
			courseSlt.sendKeys(getPropertyFile("Department"));

			WebElement semSlt = driver.findElement(By.xpath("//select[@name=\"semester\"]"));
			semSlt.sendKeys(semesterSlt + " " + i);

			WebElement chooseFile = driver.findElement(By.xpath("//input[@id=\"import_file\"]"));
			chooseFile.sendKeys(getSemester() + "\\" + semExcel + i + ".csv");

			WebElement submitClk = driver.findElement(By.xpath("//button[@id=\"submitbtn\"]"));
			submitClk.click();
		}

	}

}
