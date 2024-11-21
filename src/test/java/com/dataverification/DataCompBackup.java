package com.dataverification;

import java.io.IOException;
import java.text.DecimalFormat;

import org.baseclass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DataCompBackup  extends BaseClass{
public static void main(String[] args) throws IOException {

	WebDriver driver = null;
	WebDriverWait driverWait = null;
	
	// overallrank	
	WebElement overallRankCollege = driverWait.until(ExpectedConditions.visibilityOfElementLocated(
			By.xpath("(//span[@class=\"digit-rating arrowBox curve-left width-one\"])[1]")));
	String overallRankCollegeTxt = overallRankCollege.getText();
	String[] overallSplit = overallRankCollegeTxt.split("/");
	for (String overallRankSplit : overallSplit) {
		System.out.println("overallRankCollegeTxt=" + overallRankSplit);
//	"excelpathtowrite"
		break;
	}
	
//skillpoints
	WebElement skillPoints = driver
			.findElement(By.xpath("(//span[@class=\"digit-rating arrowBox curve-left width-two\"])[1]"));
	String skillPointsTxt = skillPoints.getText();
	String[] skillpointSplit = skillPointsTxt.split(" ");
	for (String skillPointTxt : skillpointSplit) {
//	"excelpathtowrite"
		getDataFromExcel("excelRead", "skillPointsTxt", 0, 0);
		System.out.println("skillPointsTxt=" + skillPointTxt);
		break;
	}
	
// achievements
	WebElement achievementsPoits = driver
			.findElement(By.xpath("(//span[@class=\"digit-rating arrowBox curve-left width-three\"])[1]"));
	String achievementsPoitsTxt = achievementsPoits.getText();
	String[] achievementspointsSplit = achievementsPoitsTxt.split(" ");
	for (String achievementpointText : achievementspointsSplit) {
		System.out.println("achievementsPoitsTxt=" + achievementpointText);
//	"excelpathtowrite"	
		break;

	}
	
	
//	academic percentage
	WebElement academicsPercentage = driver
			.findElement(By.xpath("(//span[@class=\"digit-rating arrowBox curve-left width-four\"])[1]"));
	String academicsPercentageTxt = academicsPercentage.getText();
	String replace = academicsPercentageTxt.replace("%", "");
	Float academicpercentInt = Float.parseFloat(replace);
	System.out.println(academicpercentInt);
	Float a = academicpercentInt / 10;
	DecimalFormat decimalFormat = new DecimalFormat("#.##");
	String finalacademic = decimalFormat.format(a);
	System.out.println(finalacademic);
//"excelpathtowrite"

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}
