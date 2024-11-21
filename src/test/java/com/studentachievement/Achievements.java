package com.studentachievement;

import java.io.IOException;
import java.time.Duration;

import org.baseclass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Achievements extends BaseClass {
	WebDriver driver;
	String studentId = "SWECV0009";
	String studentMobNo = "9876576536";
	WebDriverWait wait;
	String productsolExcelPath = "C:\\Users\\iamma\\eclipse-workspace\\SKillEnhancement\\src\\test\\resources\\InputExcel\\productandsolutions.xlsx";
	String productsolsheetName = "Sheet1";
	JavascriptExecutor executor;

	@Test(priority = 1)
	public void studentLogin() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://maplogik.com/index.php/student/login");
		driver.manage().window().maximize();

		WebElement studentIdTxt = driver.findElement(By.id("login-student-id"));
		studentIdTxt.sendKeys(studentId);

		WebElement studentMobNoTxt = driver.findElement(By.id("login-mobile"));
		studentMobNoTxt.sendKeys(studentMobNo, Keys.ENTER);

		WebElement otpReq = driver.findElement(By.id("test_otp"));
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driverWait.until(ExpectedConditions.visibilityOf(otpReq));

		String otpText = otpReq.getText();
		WebElement otpEnter = driver.findElement(By.id("login-otp"));
		otpEnter.sendKeys(otpText, Keys.ENTER);

	}

	@Test(priority = 2)
	public void productAndSolutionSelect() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement achievementClk = driver.findElement(By.xpath("//span[text()='Achievement']"));
		achievementClk.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement cocurricularClk = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Co Curricular Achievements']")));
		cocurricularClk.click();
		executor = (JavascriptExecutor) driver;
		WebElement productandsolutionClk = driver
				.findElement(By.xpath("//span[text()='Product / Solution Development']"));
		executor.executeScript("arguments[0].scrollIntoView(true);", productandsolutionClk);
		productandsolutionClk.click();

	}

	@Test(priority = 3)
	public void productandsolutionsFill() throws IOException {

		for (int i = 1; i <= 11; i++) {

			String titleInput = getDataFromExcel(productsolExcelPath, productsolsheetName, i, 0);
			WebElement titleTxt = driver.findElement(By.id("title"));
			titleTxt.sendKeys(titleInput);

			String industryInput = getDataFromExcel(productsolExcelPath, productsolsheetName, i, 1);
			WebElement industrySlt = driver.findElement(By.xpath("//select[@name=\"industry\"]"));
			industrySlt.sendKeys(industryInput);

			String statementInput = getDataFromExcel(productsolExcelPath, productsolsheetName, i, 2);
			WebElement statementTxt = driver.findElement(By.xpath("//textarea[@name=\"purpose\"]"));
			statementTxt.sendKeys(statementInput);

			String yearofdevInput = getDataFromExcel(productsolExcelPath, productsolsheetName, i, 3);
			WebElement yearofdevDt = driver.findElement(By.xpath("//input[@id=\"pd-months-year\"]"));
			yearofdevDt.sendKeys(yearofdevInput);

			WebElement productPhoto = driver.findElement(By.xpath("//input[@id=\"photo\"]"));
			productPhoto.sendKeys("C:\\Users\\iamma\\Downloads\\solar.jpg");

			WebElement productVideo = driver.findElement(By.xpath("//input[@id=\"video\"]"));
			productVideo.sendKeys("C:\\Users\\iamma\\Downloads\\video.mp4");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			WebElement submitClk = driver.findElement(By.id("submitbtn"));
			executor.executeScript("arguments[0].click()", submitClk);

		}

	}

	@Test(priority = 4)
	public void symposiumSelect() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement achievementClk = driver.findElement(By.xpath("//span[text()='Achievement']"));
		achievementClk.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement cocurricularClk = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Co Curricular Achievements']")));
		cocurricularClk.click();
		executor = (JavascriptExecutor) driver;
		WebElement symposiumClk = driver.findElement(By.xpath("//span[text()='Symposium']"));
		executor.executeScript("arguments[0].scrollIntoView(true);", symposiumClk);
		symposiumClk.click();

	}

	@Test(priority = 5)
	public void symposiumFill() throws IOException {

		String symposiumExcelPath = "C:\\Users\\iamma\\eclipse-workspace\\SKillEnhancement\\src\\test\\resources\\InputExcel\\symposium.xlsx";
		String symposiumSheetName = "Sheet1";
		for (int i = 1; i < 10; i++) {

			WebElement titleTxt = driver.findElement(By.id("title"));
			String titleInput = getDataFromExcel(symposiumExcelPath, symposiumSheetName, i, 1);
			titleTxt.sendKeys(titleInput);

			WebElement eventCatagoryslt = driver.findElement(By.xpath("//select[@name=\"category\"]"));
			String eventCatagoryInput = getDataFromExcel(symposiumExcelPath, symposiumSheetName, i, 2);
			eventCatagoryslt.sendKeys(eventCatagoryInput);

			WebElement symConductedByTxt = driver.findElement(By.id("conducted_by"));
			String symConductedByInput = getDataFromExcel(symposiumExcelPath, symposiumSheetName, i, 3);
			symConductedByTxt.sendKeys(symConductedByInput);

			WebElement dateAndYear = driver.findElement(By.id("pd-months-year"));
			String dateAndYearInput = getDataFromExcel(symposiumExcelPath, symposiumSheetName, i, 4);
			dateAndYear.sendKeys(dateAndYearInput);

			WebElement lvlSlt = driver.findElement(By.xpath("//select[@name=\"level\"]"));
			String lvlInput = getDataFromExcel(symposiumExcelPath, symposiumSheetName, i, 5);
			lvlSlt.sendKeys(lvlInput);

			WebElement awardslt = driver.findElement(By.xpath("//select[@name=\"award\"]"));
			String awardsltInput = getDataFromExcel(symposiumExcelPath, symposiumSheetName, i, 6);
			awardslt.sendKeys(awardsltInput);

			WebElement certificateUpload = driver.findElement(By.id("certificate"));
			certificateUpload.sendKeys("C:\\Users\\iamma\\Downloads\\pdf.pdf");

			WebElement submitClk = driver.findElement(By.id("submitbtn"));
			executor.executeScript("arguments[0].click()", submitClk);

		}
	}

	@Test(priority = 6)
	public void technicalRecordselect() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement achievementClk = driver.findElement(By.xpath("//span[text()='Achievement']"));
		achievementClk.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement cocurricularClk = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Co Curricular Achievements']")));
		cocurricularClk.click();
		executor = (JavascriptExecutor) driver;
		WebElement technicalRecordClk = driver.findElement(By.xpath("//span[text()='Technical Record Creation']"));
		executor.executeScript("arguments[0].scrollIntoView(true);", technicalRecordClk);
		technicalRecordClk.click();

	}

	@Test(priority = 7)
	public void technicalRecordFill() throws IOException {

		String technicalRecordExcelPath = "C:\\Users\\iamma\\eclipse-workspace\\SKillEnhancement\\src\\test\\resources\\InputExcel\\Technical record.xlsx";
		String technicalRecordSheetName = "Sheet1";

		for (int i = 1; i < 11; i++) {

			WebElement titleTxt = driver.findElement(By.id("title"));
			String titelInput = getDataFromExcel(technicalRecordExcelPath, technicalRecordSheetName, i, 1);
			titleTxt.sendKeys(titelInput);

			WebElement fieldSlt = driver.findElement(By.xpath("//select[@name=\"which_field\"]"));
			String fieldInput = getDataFromExcel(technicalRecordExcelPath, technicalRecordSheetName, i, 2);
			fieldSlt.sendKeys(fieldInput);

			WebElement placeTxt = driver.findElement(By.id("place"));
			String placeInput = getDataFromExcel(technicalRecordExcelPath, technicalRecordSheetName, i, 3);
			placeTxt.sendKeys(placeInput);

			WebElement dateAndYearSlt = driver.findElement(By.id("pd-months-year"));
			String dateAndYearInput = getDataFromExcel(technicalRecordExcelPath, technicalRecordSheetName, i, 4);
			dateAndYearSlt.sendKeys(dateAndYearInput);

			WebElement scopeSlt = driver.findElement(By.xpath("//select[@name=\"scope\"]"));
			String scopeInput = getDataFromExcel(technicalRecordExcelPath, technicalRecordSheetName, i, 5);
			scopeSlt.sendKeys(scopeInput);

			WebElement awardSlt = driver.findElement(By.xpath("//select[@name=\"award\"]"));
			String awardInput = getDataFromExcel(technicalRecordExcelPath, technicalRecordSheetName, i, 6);
			awardSlt.sendKeys(awardInput);

			WebElement photoUpload = driver.findElement(By.id("photo"));
			photoUpload.sendKeys("C:\\Users\\iamma\\Downloads\\VFX.png");

			WebElement certificateUpload = driver.findElement(By.id("certificate"));
			certificateUpload.sendKeys("C:\\Users\\iamma\\Downloads\\pdf.pdf");

			WebElement videoUpload = driver.findElement(By.id("video"));
			videoUpload.sendKeys("C:\\Users\\iamma\\Downloads\\video.mp4");

			WebElement submitClk = driver.findElement(By.id("submitbtn"));
			executor.executeScript("arguments[0].click()", submitClk);
		}
	}

	@Test(priority = 8)
	public void otherTechnicalRecordselect() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement achievementClk = driver.findElement(By.xpath("//span[text()='Achievement']"));
		achievementClk.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement cocurricularClk = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Co Curricular Achievements']")));
		cocurricularClk.click();
		executor = (JavascriptExecutor) driver;
		WebElement technicalRecordClk = driver.findElement(By.xpath("//span[text()='Other Technical Activities']"));
		executor.executeScript("arguments[0].scrollIntoView(true);", technicalRecordClk);
		technicalRecordClk.click();

	}

	@Test(priority = 9)
	public void otherTechnicalRecordsFill() throws IOException {

		String otherTechnicalExcelPath = "C:\\Users\\iamma\\eclipse-workspace\\SKillEnhancement\\src\\test\\resources\\InputExcel\\other technical records.xlsx";
		String otherTechnicalSheetName = "Sheet1";

		for (int i = 1; i < 11; i++) {

			WebElement titleTxt = driver.findElement(By.id("title"));
			String titleInput = getDataFromExcel(otherTechnicalExcelPath, otherTechnicalSheetName, i, 1);
			titleTxt.sendKeys(titleInput);

			WebElement conductedBySlt = driver.findElement(By.id("conduted_by"));
			String conductedByInput = getDataFromExcel(otherTechnicalExcelPath, otherTechnicalSheetName, i, 2);
			conductedBySlt.sendKeys(conductedByInput);

			WebElement placeTxt = driver.findElement(By.id("place"));
			String placeInput = getDataFromExcel(otherTechnicalExcelPath, otherTechnicalSheetName, i, 3);
			placeTxt.sendKeys(placeInput);

			WebElement dateAndYear = driver.findElement(By.id("pd-months-year"));
			String dateAndYearInput = getDataFromExcel(otherTechnicalExcelPath, otherTechnicalSheetName, i, 4);
			dateAndYear.sendKeys(dateAndYearInput);

			WebElement awardSlt = driver.findElement(By.xpath("//select[@name=\"award\"]"));
			String awardInput = getDataFromExcel(otherTechnicalExcelPath, otherTechnicalSheetName, i, 5);
			awardSlt.sendKeys(awardInput);

			WebElement certificateUpload = driver.findElement(By.id("certificate"));
			certificateUpload.sendKeys("C:\\Users\\iamma\\Downloads\\pdf.pdf");

			WebElement submitClk = driver.findElement(By.id("submitbtn"));
			executor.executeScript("arguments[0].click()", submitClk);

		}
	}

	@Ignore
	@Test(priority = 9)
	public void extraCurricular() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement achievementClk = driver.findElement(By.xpath("//span[text()='Achievement']"));
		achievementClk.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement extraCurricularClk = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[text()='Extra Curricular Activities']")));
		extraCurricularClk.click();
		WebElement recordCreation = driver.findElement(By.xpath("//span[text()='Record Creation']"));
		executor.executeScript("arguments[0].scrollIntoView(true);", recordCreation);
		recordCreation.click();
	}

	@Ignore
	@Test(priority = 10)
	public void recordCreationFill() throws IOException {
		String recordCreationExcelPath = "C:\\Users\\iamma\\eclipse-workspace\\SKillEnhancement\\src\\test\\resources\\InputExcel\\Technical record.xlsx";
		String recordCreationSheetName = "Sheet1";

		for (int i = 1; i < 11; i++) {

			WebElement titleTxt = driver.findElement(By.id("title"));
			String titelInput = getDataFromExcel(recordCreationExcelPath, recordCreationSheetName, i, 1);
			titleTxt.sendKeys(titelInput);

			WebElement placeTxt = driver.findElement(By.id("conducted_by"));
			String placeInput = getDataFromExcel(recordCreationExcelPath, recordCreationSheetName, i, 2);
			placeTxt.sendKeys(placeInput);

			WebElement dateAndYear = driver.findElement(By.id("pd-months-year"));
			String dateAndYearInput = getDataFromExcel(recordCreationExcelPath, recordCreationSheetName, i, 3);
			dateAndYear.sendKeys(dateAndYearInput);

			WebElement scopeSlt = driver.findElement(By.xpath("//select[@name=\"award\"]"));
			String scopeInput = getDataFromExcel(recordCreationExcelPath, recordCreationSheetName, i, 4);
			scopeSlt.sendKeys(scopeInput);

		}

	}

}