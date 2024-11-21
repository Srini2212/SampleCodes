package com.pathsetter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.baseclass.BaseClass;
import org.testng.annotations.Test;

public class PathSelector extends BaseClass {
	@Test(priority = 1)
	public static String getPropertyFile(String key) throws Exception, Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream(
				"C:\\Users\\iamma\\eclipse-workspace\\SKillEnhancement\\Config\\config.properties"));
		return (String) properties.get(key);

	}

	@Test(priority = 2)
	public static String getExcelPath() throws Exception {
		File file = new File("E:\\Data\\" + getPropertyFile("Course_Category") + "\\" + getPropertyFile("District")
				+ "\\" + getPropertyFile("College") + "\\" + getPropertyFile("Course_Level") + "\\"
				+ getPropertyFile("Department") + "\\" + getPropertyFile("Year"));
		String excelPath = String.valueOf(file);
		return excelPath;
	}

	/*
	 * @Test(priority = 2) public static String getCourse_Category() throws
	 * Exception {
	 * 
	 * File file = new File("E:\\Data\\" + getPropertyFile("Course_Category"));
	 * String string = String.valueOf(file); return string;
	 * 
	 * }
	 * 
	 * @Test(priority = 3) public static String getDistrict() throws Exception {
	 * File file = new File(getCourse_Category() +
	 * "\\" + getPropertyFile("District")); String string = String.valueOf(file);
	 * return string; }
	 * 
	 * @Test(priority = 4) public static String getcollege() throws Exception { File
	 * file = new File(getDistrict() + "\\" + getPropertyFile("College")); String
	 * string = String.valueOf(file); return string; }
	 * 
	 * @Test(priority = 5) public static String getCourse_Level() throws Exception {
	 * File file = new File(getcollege() + "\\" + getPropertyFile("Course_Level"));
	 * String string = String.valueOf(file); return string; }
	 * 
	 * @Test(priority = 6) public static String getDepartment() throws Exception {
	 * File file = new File(getCourse_Level() +
	 * "\\" + getPropertyFile("Department")); String string = String.valueOf(file);
	 * return string; }
	 * 
	 * @Test(priority = 7) public static String getYear() throws Exception { File
	 * file = new File(getDepartment() + "\\" + getPropertyFile("Year")); String
	 * string = String.valueOf(file); return string; }
	 */

	@Test(priority = 3)
	public static void getStudentDetails() throws Exception {
		File file = new File(getExcelPath() + "\\" + getPropertyFile("StudentDetails") + ".xlsx");
		String string = String.valueOf(file);
		String output = getDataFromExcel(string, "Sheet1", 0, 0);
		System.out.println(output);

	}

	@Test(priority = 4)
	public void excelRead() throws IOException, Exception {
		for (int i = 1; i < 7; i++) {

			String dataFromExcel = getDataFromExcel(getExcelPath() + "\\sem_" + i + ".xlsx", "Sheet1", 0, 0);
			System.out.println(dataFromExcel);
		}
	}

}
