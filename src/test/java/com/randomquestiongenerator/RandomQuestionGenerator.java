package com.randomquestiongenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.baseclass.BaseClass;
import org.testng.annotations.Test;

public class RandomQuestionGenerator extends BaseClass {

	Random random = new Random();
	String questionBody = "what is ";
	String finalques = "";
	String filepath = "C:\\Users\\iamma\\eclipse-workspace\\SKillEnhancement\\src\\test\\resources\\OutputExcel\\sample1.xlsx";
	String SheetName = "Oracle";
	File file;

	@Test(priority = 1)
	public void questionCreation() throws Exception {

		file = new File(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet createSheet = workbook.createSheet(SheetName);

		for (int i = 1; i <= 1; i++) {

			for (int j = i; j <= 100; j++) {
				XSSFRow createRow = createSheet.createRow(j);
				XSSFCell createCell = createRow.createCell(i);

				int x = random.nextInt(50);
				String valueOf = String.valueOf(x);
				finalques = questionBody + valueOf + "?";
				createCell.setCellValue(finalques);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				workbook.write(fileOutputStream);
				System.out.println(finalques);
			}
		}

	}

	
		

	

}
