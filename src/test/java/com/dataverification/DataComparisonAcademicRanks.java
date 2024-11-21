package com.dataverification;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.util.CellRangeAddress;
import org.baseclass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.beep.SuccessSound;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataComparisonAcademicRanks extends BaseClass {

	public static void main(String[] args) throws IOException {

		String excelRead = "C:\\Users\\iamma\\eclipse-workspace\\SKillEnhancement\\src\\test\\resources\\InputExcel\\datacomparison-data.xlsx";
		String excelReadSheet = "year3";
		String excelWrite = "C:\\Users\\iamma\\eclipse-workspace\\SKillEnhancement\\src\\test\\resources\\OutputExcel\\final-rank comparison.xlsx";
		String excelWriteSheet = "academic";

//		BrowserLaunch

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://maplogik.com/index.php/student/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		for (int i = 2; i <= 20; i++) {

//		studentLogin

			String studentID = getDataFromExcel(excelRead, excelReadSheet, i, 0);

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

			try {
				WebElement popUp = driverWait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn-close']")));
				popUp.click();

			} catch (Exception e) {
			}
			try {
				WebElement arrear = driver.findElement(By.xpath("(//div[@class=\"row item\"])[1]"));
				String arrearTxt = arrear.getText();

				if (arrearTxt.contains("Arrears")) {

					writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 0, studentID);
					setCellPinkColour(excelWrite, excelWriteSheet, i, 3, "Arrear");
					setCellPinkColour(excelWrite, excelWriteSheet, i, 6, "Arrear");
					setCellPinkColour(excelWrite, excelWriteSheet, i, 9, "Arrear");
//					System.out.println("arrear");
//					excelwrite as arrear

					WebElement logOutClk = driver.findElement(By.xpath("//span[text()='Log out']"));
//					executor.executeScript("arguments[0].scrollIntoView(true);", logOutClk);
					logOutClk.click();
					continue;
				}

				if (arrearTxt.contains("Topper")) {

//					excel write as topper
					WebElement forScrollTop = driver
							.findElement(By.xpath("//h4[text()='Your Profile Visibility Meter']"));
					executor.executeScript("arguments[0].scrollIntoView(true);", forScrollTop);
//		           acedamics marks only

					WebElement collegeRank = driverWait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("(//div[@class=\"col-md-4 text-center\"])[4]")));
					String collegeRankTxt = collegeRank.getText();
					String[] ClgRanksplit = collegeRankTxt.split("/");
					String topclgRankAct = null;
					String topclgRankExp = null;

					for (String clgRankTxt : ClgRanksplit) {
						writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 0, studentID);
						topclgRankAct = getDataFromExcel(excelRead, excelReadSheet, i, 1);
						writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 1, topclgRankAct);
						topclgRankExp = writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 2, clgRankTxt);
//						System.out.println("clgRankTxt" + clgRankTxt);
//		                	"excelpathtowrite" 

						if (topclgRankAct.equals(topclgRankExp)) {
							setCellGreenColour(excelWrite, excelWriteSheet, i, 3, "Match");
						} else {
							setCellRedColour(excelWrite, excelWriteSheet, i, 3, "Not Match");
						}
						break;
					}

					WebElement districtRank = driverWait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("(//div[@class=\"col-md-4 text-center\"])[5]")));
					String districtRankTxt = districtRank.getText();
					String[] districtRankSplit = districtRankTxt.split("/");
					String topdistrictRankAct = null;
					String topdistrictRankExp = null;
					for (String dstRankTxt : districtRankSplit) {
						topdistrictRankAct = getDataFromExcel(excelRead, excelReadSheet, i, 2);
						writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 4, topdistrictRankAct);
						topdistrictRankExp = writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 5, dstRankTxt);
//						System.out.println("dstRankTxt" + dstRankTxt);
//		                	"excelpathtowrite" 
						if (topdistrictRankAct.equals(topdistrictRankExp)) {
							setCellGreenColour(excelWrite, excelWriteSheet, i, 6, "Match");
						} else {
							setCellRedColour(excelWrite, excelWriteSheet, i, 6, "Not Match");
						}
						break;
					}

					WebElement stateRank = driverWait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("(//div[@class=\"col-md-4 text-center\"])[6]")));
					String stateRankTxt = stateRank.getText();
					String[] stateRankSplit = stateRankTxt.split("/");
					String topstateRankAct = null;
					String topstateRankExp = null;
					for (String stateRankText : stateRankSplit) {
						topstateRankAct = getDataFromExcel(excelRead, excelReadSheet, i, 3);
						writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 7, topstateRankAct);
						topstateRankExp = writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 8, stateRankText);
//						System.out.println("stateRankText" + stateRankText);
//		                	"excelpathtowrite" 
						if (topstateRankAct.equals(topstateRankExp)) {
							setCellGreenColour(excelWrite, excelWriteSheet, i, 9, "Match");
						} else {
							setCellRedColour(excelWrite, excelWriteSheet, i, 9, "Not Match");
						}
						break;
					}

					WebElement logOutClk = driver.findElement(By.xpath("//span[text()='Log out']"));
//					executor.executeScript("arguments[0].scrollIntoView(true);", logOutClk);
					logOutClk.click();
					continue;

				}
			} catch (Exception e) {
			}
			try {
//				collecting datas
//				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				WebElement forScroll = driver.findElement(By.xpath("//h4[text()='Your Profile Visibility Meter']"));
				executor.executeScript("arguments[0].scrollIntoView(true);", forScroll);
//           acedamics marks only

				WebElement collegeRank = driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("(//div[@class=\"col-md-4 text-center\"])[4]")));

				String collegeRankTxt = collegeRank.getText();
				String[] ClgRanksplit = collegeRankTxt.split("/");
				String clgRankExp = null;
				String clgRankAct = null;
				for (String clgRankTxt : ClgRanksplit) {
					writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 0, studentID);
					clgRankAct = getDataFromExcel(excelRead, excelReadSheet, i, 1);
					writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 1, clgRankAct);
					clgRankExp = writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 2, clgRankTxt);
//					System.out.println("clgRankTxt" + clgRankTxt);
//                	"excelpathtowrite" 

					if (clgRankAct.equals(clgRankExp)) {
						setCellGreenColour(excelWrite, excelWriteSheet, i, 3, "Match");
					} else {
						setCellRedColour(excelWrite, excelWriteSheet, i, 3, "Not Match");
					}
					break;
				}

				WebElement districtRank = driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("(//div[@class=\"col-md-4 text-center\"])[5]")));

				String districtRankTxt = districtRank.getText();
				String[] districtRankSplit = districtRankTxt.split("/");
				String districtRankAct = null;
				String districtRankExp = null;
				for (String dstRankTxt : districtRankSplit) {
					districtRankAct = getDataFromExcel(excelRead, excelReadSheet, i, 2);
					writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 4, districtRankAct);
					districtRankExp = writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 5, dstRankTxt);

//					System.out.println("dstRankTxt" + dstRankTxt);
//                	"excelpathtowrite" 
					if (districtRankAct.equals(districtRankExp)) {
						setCellGreenColour(excelWrite, excelWriteSheet, i, 6, "Match");
					} else {
						setCellRedColour(excelWrite, excelWriteSheet, i, 6, "Not Match");
					}
					break;
				}

				WebElement stateRank = driverWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("(//div[@class=\"col-md-4 text-center\"])[6]")));

				String stateRankTxt = stateRank.getText();
				String[] stateRankSplit = stateRankTxt.split("/");
				String stateRankAct = null;
				String stateRankExp = null;
				for (String stateRankText : stateRankSplit) {
					stateRankAct = getDataFromExcel(excelRead, excelReadSheet, i, 3);
					writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 7, stateRankAct);
					stateRankExp = writeDataInExistingExcel(excelWrite, excelWriteSheet, i, 8, stateRankText);
//					System.out.println("stateRankText" + stateRankText);
//                	"excelpathtowrite" 
					if (stateRankAct.equals(stateRankExp)) {
						setCellGreenColour(excelWrite, excelWriteSheet, i, 9, "Match");
					} else {
						setCellRedColour(excelWrite, excelWriteSheet, i, 9, "Not Match");
					}
					break;
				}

				WebElement logOutClk = driver.findElement(By.xpath("//span[text()='Log out']"));
				executor.executeScript("arguments[0].scrollIntoView(true);", logOutClk);
				logOutClk.click();
			} catch (Exception e) {

			}
		}
		driver.quit();
		SuccessSound.musicFile();

	}

}
