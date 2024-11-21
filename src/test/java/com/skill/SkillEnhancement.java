package com.skill;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;

import org.baseclass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author gugan
 * @see using this program you can create training and add assessment for that
 *      training
 *
 */
public class SkillEnhancement extends BaseClass {

	static String adminEmail = "admin@gmail.com";
	static String adminPass = "Admin@123";
	static WebDriver driver;
	WebDriverWait driverWait;
	String excelFile = "C:\\Users\\iamma\\eclipse-workspace\\SKillEnhancement\\src\\test\\resources\\InputExcel\\assessment.xlsx";
	String sheetName = "Sheet1";
	String CourseName = "DevOps";
	String assessmentName = "Certified dev";
	String assessmentCode = "MLAD";

	@Test(priority = 1)
	public void adminLogin() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://maplogik.com/index.php/admin/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement adminEmailId = driver.findElement(By.id("login-email"));
		adminEmailId.sendKeys(adminEmail);
		WebElement adminPassword = driver.findElement(By.id("login-password"));
		adminPassword.sendKeys(adminPass);
		WebElement loginClk = driver.findElement(By.xpath("//button[text()='Log in']"));
		loginClk.click();
	}

	@Test(priority = 2)
	public void skillEnhancement() {
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement skillEnClk = driver.findElement(By.xpath("//span[text()='Skill Enhancement']"));
		driverWait.until(ExpectedConditions.visibilityOf(skillEnClk));
		skillEnClk.click();
		WebElement allTrainingClk = driver.findElement(By.xpath("//span[text()='All Trainings']"));
		driverWait.until(ExpectedConditions.visibilityOf(allTrainingClk));
		allTrainingClk.click();
		WebElement addnewClk = driver.findElement(By.xpath("//a[@tabindex=0]"));
		addnewClk.click();
	}

	@Test(priority = 3)
	public void addNew() throws Exception {
		WebElement trainingNameTxt = driver.findElement(By.id("training_name"));
		driverWait.until(ExpectedConditions.visibilityOf(trainingNameTxt));
		trainingNameTxt.sendKeys(CourseName);

		WebElement imgUpload = driver.findElement(By.id("imag"));
		imgUpload.sendKeys("C:\\Users\\iamma\\Downloads\\cmp2.png");

		WebElement authorTxt = driver.findElement(By.id("title_video1"));
		authorTxt.sendKeys("CJ");

		WebElement introTxt = driver.findElement(By.xpath("//textarea[@name='introduction']"));
		introTxt.sendKeys(
				"Tableau Software is an American interactive data visualization software company focused on business intelligence. It was founded in 2003 in Mountain View, California, and is currently headquartered in Seattle, Washington. In 2019 the company was acquired by Salesforce for $15.7 billion");

		WebElement pointsDd = driver.findElement(By.xpath("//select[@name='points']"));
		pointsDd.sendKeys("5");

		WebElement paymentTypeRd = driver.findElement(By.xpath("//input[@value='Free']"));
		paymentTypeRd.click();

		WebElement coreGroupTxt = driver.findElement(By.xpath("//select[@id='select2-multiple']"));
		Select select = new Select(coreGroupTxt);
		select.selectByValue("4");
		select.selectByValue("5");
		select.selectByValue("12");

		WebElement durationTxt = driver.findElement(By.id("duration"));
		durationTxt.sendKeys("2");

		WebElement mostWantedRd = driver.findElement(By.xpath("(//input[@value='Yes'])[1]"));
		driverWait.until(ExpectedConditions.elementToBeClickable(mostWantedRd));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", mostWantedRd);
//		mostWantedRd.click();

		WebElement companyNosTxt = driver.findElement(By.xpath("//input[@name='number_of_companies']"));
		companyNosTxt.sendKeys("100");

		WebElement mostPopularRdb = driver.findElement(By.xpath("(//input[@value='Yes'])[2]"));
		driverWait.until(ExpectedConditions.elementToBeClickable(mostPopularRdb));
		js.executeScript("arguments[0].click()", mostPopularRdb);
//		mostWantedRd.click();

		WebElement courseIntroTxt = driver.findElement(By.xpath("//textarea[@name='coursecontent']"));
		courseIntroTxt.sendKeys(
				". Data visualization is important because human beings understand things that are visually well descriptive and interesting. So, working with data visualization tools like Tableau will help anyone understand data better, as it gives one access to the amount of data in easily digestible visuals. Also, well-designed graphics are usually the simplest and the most powerful way to present any data.");

		WebElement studentEnrolledTxt = driver.findElement(By.xpath("//input[@name='number_of_students']"));
		studentEnrolledTxt.sendKeys("350");
		try {
			WebElement cmpImg1 = driver.findElement(By.xpath("//input[@id='lgimag1']"));
			cmpImg1.sendKeys("C:\\Users\\iamma\\Downloads\\cmp1.png");
		} catch (Exception e) {
		}
		try {

			WebElement cmpImg2 = driver.findElement(By.xpath("//input[@id='lgimag2']"));
			driverWait.until(ExpectedConditions.elementToBeClickable(cmpImg2));
			cmpImg2.sendKeys("C:\\Users\\iamma\\Downloads\\cmp2.png");
		} catch (Exception e) {
		}
		try {

			WebElement cmpImg3 = driver.findElement(By.xpath("//input[@id='lgimag3']"));
			driverWait.until(ExpectedConditions.elementToBeClickable(cmpImg3));
			cmpImg3.sendKeys("C:\\Users\\iamma\\Downloads\\cmp3.png");
		} catch (Exception e) {

		}

		try {

			WebElement cmpImg4 = driver.findElement(By.xpath("//input[@id='lgimag4']"));
			driverWait.until(ExpectedConditions.elementToBeClickable(cmpImg4));
			cmpImg4.sendKeys("C:\\Users\\iamma\\Downloads\\cmp4.png");
		} catch (Exception e) {

		}
		try {

			WebElement cmpImg5 = driver.findElement(By.xpath("//input[@id='lgimag5']"));
			driverWait.until(ExpectedConditions.elementToBeClickable(cmpImg5));
			cmpImg5.sendKeys("C:\\Users\\iamma\\Downloads\\cmp4.png");
		} catch (Exception e) {

		}
		try {

			WebElement cmpImg6 = driver.findElement(By.xpath("//input[@id='lgimag6']"));
			driverWait.until(ExpectedConditions.elementToBeClickable(cmpImg6));
			cmpImg6.sendKeys("C:\\Users\\iamma\\Downloads\\cmp3.png");
		} catch (Exception e) {

		}
		try {

			WebElement cmpImg6 = driver.findElement(By.xpath("//input[@id='lgimag7']"));
			driverWait.until(ExpectedConditions.elementToBeClickable(cmpImg6));
			cmpImg6.sendKeys("C:\\Users\\iamma\\Downloads\\cmp3.png");
		} catch (Exception e) {

		}
		try {

			WebElement cmpImg6 = driver.findElement(By.xpath("//input[@id='lgimag8']"));
			driverWait.until(ExpectedConditions.elementToBeClickable(cmpImg6));
			cmpImg6.sendKeys("C:\\Users\\iamma\\Downloads\\cmp3.png");
		} catch (Exception e) {

		}
		try {

			WebElement cmpImg6 = driver.findElement(By.xpath("//input[@id='lgimag9']"));
			driverWait.until(ExpectedConditions.elementToBeClickable(cmpImg6));
			cmpImg6.sendKeys("C:\\Users\\iamma\\Downloads\\cmp3.png");
		} catch (Exception e) {

		}
		try {

			WebElement cmpImg6 = driver.findElement(By.xpath("//input[@id='lgimag10']"));
			driverWait.until(ExpectedConditions.elementToBeClickable(cmpImg6));
			cmpImg6.sendKeys("C:\\Users\\iamma\\Downloads\\cmp3.png");
		} catch (Exception e) {

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement assessmentDurationTxt = driver.findElement(By.xpath("//input[@name='duration_assessment']"));
		assessmentDurationTxt.clear();
		assessmentDurationTxt.sendKeys("2");

		WebElement questionsPerAssessmentTxt = driver.findElement(By.xpath("//input[@name='num_que_for_assessment']"));
		questionsPerAssessmentTxt.clear();
		questionsPerAssessmentTxt.sendKeys("10");

		WebElement marksPerQuestion = driver.findElement(By.xpath("//input[@name='individual_que_marks']"));
		marksPerQuestion.clear();
		marksPerQuestion.sendKeys("10");

		WebElement assessmentCodeTxt = driver.findElement(By.xpath("//input[@name=\"assessment_code\"]"));
		assessmentCodeTxt.sendKeys(assessmentCode);
		WebElement assessmantName = driver.findElement(By.xpath("//input[@name=\"assessment_name\"]"));
		assessmantName.sendKeys(assessmentName);

		WebElement saveandcontinueBtn = driver.findElement(By.id("submitbtn"));
		driverWait.until(ExpectedConditions.elementToBeClickable(saveandcontinueBtn));
//		driverWait.until(ExpectedConditions.elementToBeClickable(mostPopularRdb));
		js.executeScript("arguments[0].click()", saveandcontinueBtn);

	}

	@Test(priority = 4)
	public void addCourseVideo() throws AWTException, InterruptedException {

		WebElement videoTitle = driver.findElement(By.xpath("//input[@id='title_video1']"));
		videoTitle.sendKeys("Introduction");
		try {
			WebElement vdoUpload = driver.findElement(By.id("video1"));
			vdoUpload.sendKeys("C:\\Users\\iamma\\Downloads\\video.mp4");
//			Robot robot = new Robot();
//			StringSelection filepath = new StringSelection("C:\\Users\\iamma\\Downloads\\video");
//			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath, null);
//			robot.keyPress(KeyEvent.VK_CONTROL);
//			robot.keyPress(KeyEvent.VK_V);
//			robot.keyRelease(KeyEvent.VK_V);
//			robot.keyRelease(KeyEvent.VK_CONTROL);
//			robot.keyPress(KeyEvent.VK_ENTER);
//			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (InvalidArgumentException e) {
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement clkandCntBtn = driver.findElement(By.id("submitbtn"));
		clkandCntBtn.click();
	}

	@Test(priority = 5)
	public void addingAssessment() throws IOException {

		WebElement skillenClk = driver.findElement(By.xpath("//span[text()=\"Skill Enhancement\"]"));
		skillenClk.click();
		WebElement alltrainingClk = driver.findElement(By.xpath("//span[text()=\"All Trainings\"]"));
		alltrainingClk.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement addAssessment = driver.findElement(By.xpath("(//a[@title=\"Add Assessment\"])[1]"));
		driverWait.until(ExpectedConditions.visibilityOf(addAssessment));
		addAssessment.click();

		for (int i = 1; i < 41; i++) {

			WebElement addasClk = driver.findElement(By.xpath(
					"//a[@class=\"dt-button create-new btn btn-primary waves-effect waves-float waves-light\"]"));
			driverWait.until(ExpectedConditions.visibilityOf(addasClk));
			addasClk.click();

			String question = getDataFromExcel(excelFile, sheetName, i, 0);
			WebElement questionName = driver.findElement(By.xpath("(//input[@id=\"training_name\"])[1]"));
			questionName.sendKeys(question);

			WebElement questionImage = driver.findElement(By.id("image_file"));
			questionImage.sendKeys("C:\\Users\\iamma\\Downloads\\sdlc.png");

			String optionA = getDataFromExcel(excelFile, sheetName, i, 1);
			WebElement aOption = driver.findElement(By.xpath("(//input[@id=\"training_name\"])[2]"));
			aOption.sendKeys(optionA);
			String optionB = getDataFromExcel(excelFile, sheetName, i, 2);
			WebElement bOption = driver.findElement(By.xpath("(//input[@id=\"training_name\"])[3]"));
			bOption.sendKeys(optionB);
			String optionC = getDataFromExcel(excelFile, sheetName, i, 3);
			WebElement cOption = driver.findElement(By.xpath("(//input[@id=\"training_name\"])[4]"));
			cOption.sendKeys(optionC);
			String optionD = getDataFromExcel(excelFile, sheetName, i, 4);
			WebElement dOption = driver.findElement(By.xpath("(//input[@id=\"training_name\"])[5]"));
			dOption.sendKeys(optionD);
			WebElement correctAnswer = driver.findElement(By.xpath("//select[@name='correctanswer']"));
			Select select = new Select(correctAnswer);
			select.selectByValue("1");
			WebElement saveBtn = driver.findElement(By.id("submitbtn"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", saveBtn);

		}
		driver.quit();
	}
}
