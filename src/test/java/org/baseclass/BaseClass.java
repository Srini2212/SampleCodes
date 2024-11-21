package org.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	public static void implicitwait(int sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));

	}

//	1 to load url
	public static void browserLaunch(String url) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}

//   2 find elementid
	public static WebElement findElementID(String id) {

		WebElement element = driver.findElement(By.id(id));
		return element;
	}

//	3 find elementname
	public static WebElement findElementName(String name) {
		WebElement element = driver.findElement(By.name(name));
		return element;
	}

//	4 find elementclassname
	public static WebElement findElementClassName(String data) {
		WebElement element = driver.findElement(By.id(data));
		return element;
	}

//	5 find elementxpath
	public static WebElement findElementXpath(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();
		return element;
	}

//	6 sendkeys
	public static void sendKeys(WebElement element, String data) {
		element.sendKeys(data);
	}

//	7 getattribute
	public String getAttribute(WebElement element, String name) {
		String string = element.getAttribute(name);
		return string;
	}

//	8 gettext
	public String getText(WebElement element) {
		String string = element.getText();
		return string;
	}

// 9 actions doubleclick
	public void doubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}

//	10 actions rightclick
	public void rightClick() {
		Actions actions = new Actions(driver);
		actions.contextClick().perform();
	}

//	11 actions mouseover
	public void mouseOver(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

//	12 alert accept
	public void alertHandlingAccept() {
		driver.switchTo().alert().accept();
	}

//	13 alert dismiss
	public void alertHandlingDismiss() {
		driver.switchTo().alert().dismiss();
	}

//	14 select getoptions
	public List<WebElement> getOptions(WebElement element) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		return options;
	}
//  15	select using index

	public void selectIndex(WebElement element, int index) {

		Select select = new Select(element);
		select.selectByIndex(index);
	}

//	16 select using value
	public void selectValue(WebElement element, String value) {

		Select select = new Select(element);
		select.selectByValue(value);
	}

//	17 select using visible text
	public void selectVisibleText(WebElement element, String text) {

		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
//	18 deselct all

	public void deselectAll(WebElement element) {

		Select select = new Select(element);
		select.deselectAll();
	}

//  19	 get first selected options
	public WebElement getFirstSelectedOptions(WebElement element) {

		Select select = new Select(element);
		WebElement selectedOption = select.getFirstSelectedOption();
		return selectedOption;
	}

//	20 get all selected options

	public List<WebElement> getAllSelectedOptions(WebElement element) {

		Select select = new Select(element);
		List<WebElement> selectedOptions = select.getAllSelectedOptions();
		return selectedOptions;

	}

//	21 navigate

	public void navigate(String url) {

		driver.navigate().to(url);
	}

//  22 click
	public void click(WebElement element) {

		element.click();
	}

//		23 close all windows

	public static void quit() {

		driver.quit();
	}

//	24 close current window

	public void close() {

		driver.close();
	}

//	25 get title
	public String getTitle() {

		String title = driver.getTitle();
		return title;
	}

//	26 get the entered url

	public String getCurrentUrl() {

		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

//	27 sendkeysusing js

	public static void javaScriptSendKeys(WebElement element, String value) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].setAttribute('value',value)", element);
	}

//	28 click button by javascript

	public void javaScriptClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
	}

//	29 get window handle

	public String getWindowHandle() {

		String windowHandle = driver.getWindowHandle();

		return windowHandle;
	}

//	30 get window handles

	public Set<String> getWindowHandles() {

		Set<String> windowHandles = driver.getWindowHandles();

		return windowHandles;

	}

//	31 switch to child window

	public void switchWindow(int index) {

		Set<String> windowHandles = driver.getWindowHandles();

		List<String> list = new ArrayList<String>();

		list.addAll(windowHandles);

		driver.switchTo().window(list.get(index));
	}

//	32 switch to frame by index

	public void switchToFrame(int index) {

		driver.switchTo().frame(index);
	}

//	33 switch to frame by id

	public void switchToFrame(String name) {

		driver.switchTo().frame(name);
	}

//	34 get first selected option

	public WebElement getFirstSelectedOption(WebElement element) {

		Select select = new Select(element);
		WebElement element2 = select.getFirstSelectedOption();

		return element2;

	}
//	35 drop down is multiple or not

	public boolean isMultiple(WebElement element) {

		Select select = new Select(element);
		boolean multiple = select.isMultiple();
		return multiple;
	}

//	36 deselect by index

	public void deselectByIndex(WebElement element, int index) {

		Select select = new Select(element);
		select.deselectByIndex(index);
	}

//	37 deselect by text
	public void deselectByText(WebElement element, String text) {
		Select select = new Select(element);
		select.deselectByVisibleText(text);

	}

//	38 drag and drop

	public void dragAndDrop(WebElement source, WebElement target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target);

	}

//	39 clear textbox

	public void clear(WebElement element) {

		element.clear();

	}

//	40 navigate to

	public void navigateTo(String url) {
		driver.navigate().to(url);

	}

//	41 navigate back
	public void navigateBack() {

		driver.navigate().back();
	}

//	42 navigate forward

	public void navigateForward() {

		driver.navigate().forward();
	}

//	43 navigate refresh

	public void navigateRefresh() {

		driver.navigate().refresh();
	}

//	44 element screenshot

	public static void screenshotElement(WebElement element, String pathname) throws IOException {

		File s = element.getScreenshotAs(OutputType.FILE);
		File d = new File(pathname);
		FileUtils.copyFile(s, d);

	}

//	45 switch to frame by name

	public void framByName(String nameOrId) {

		driver.switchTo().frame(nameOrId);
	}
//	46 keysdown using action class

	public void keysDown(String key) {

		Actions actions = new Actions(driver);
		Actions keyDown = actions.keyDown(key);
		keyDown.perform();
	}

//	47 keyupusing action class

	public void keyUp(String key) {

		Actions actions = new Actions(driver);
		actions.keyUp(key).perform();
	}

//	48 scrolldown using js

	public void javaScriptScrollDown(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].ScrollIntoView(true)", element);
	}

//	49 scrollup using js

	public void javaScriptScrollUp(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].ScrollIntoView(false)", element);
	}

//	50 frame switch to immediate parent frame

	public void switchImmediateFrameParent() {
		driver.switchTo().parentFrame();

	}

//	51 takescreenshot
	public static void takeScreenshot(String pathname) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File s = screenshot.getScreenshotAs(OutputType.FILE);
		File d = new File(pathname);
		FileUtils.copyFile(s, d);

	}

//	52 getdatafromexcel

	public static String getDataFromExcel(String pathname, String sheetname, int rowno, int cellno) throws IOException {

		String data = null;

		File file = new File(pathname);

		FileInputStream fileInputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(fileInputStream);

		Sheet sheet = workbook.getSheet(sheetname);

		Row row = sheet.getRow(rowno);

		org.apache.poi.ss.usermodel.Cell cell = row.getCell(cellno);

		CellType cellType = cell.getCellType();

		if (cellType.toString().equals("STRING")) {

			data = cell.getStringCellValue();

		}

		if (cellType.toString().equals("NUMERIC")) {

			if (DateUtil.isCellDateFormatted(cell)) {

				Date dateCellValue = cell.getDateCellValue();

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");

				data = dateFormat.format(dateCellValue);
			}

			else {
				double numericCellValue = cell.getNumericCellValue();

				long l = (long) numericCellValue;
				data = String.valueOf(l);
			}

		}
		return data;

	}

//	53 write data in excel
	public static void createExcel(String excelpathname, String sheetname, int rowno, int cellno, String valuetowrite)
			throws Exception {

		File file = new File(excelpathname);
		Workbook workbook = new XSSFWorkbook();
		Sheet createSheet = workbook.createSheet(sheetname);
		Row createRow = createSheet.createRow(rowno);
		org.apache.poi.ss.usermodel.Cell createCell = createRow.createCell(cellno);
		createCell.setCellValue(valuetowrite);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);

	}

//	54 write data in previous excel

	public static String writeDataInExistingExcel(String excelpathname, String sheetname, int rowno, int cellno,
			String valuetowrite) throws IOException {

		File file = new File(excelpathname);

		FileInputStream fileInputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(fileInputStream);

		Sheet sheet = workbook.getSheet(sheetname);

		if (sheet.getRow(rowno) == null) {
			Row row = sheet.createRow(rowno);
			Cell createCell = row.createCell(cellno);
			createCell.setCellValue(valuetowrite);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			workbook.write(fileOutputStream);
			workbook.close();
		} else {
			Row row = sheet.getRow(rowno);
			Cell createCell = row.createCell(cellno);
			createCell.setCellValue(valuetowrite);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			workbook.write(fileOutputStream);
			workbook.close();
		}

		return valuetowrite;

	}

	public static void setCellGreenColour(String excelpathname, String sheetname, int rowno, int cellno,
			String valueToWrite) throws Exception {

		File file = new File(excelpathname);

		FileInputStream fileInputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(fileInputStream);

		Sheet sheet = workbook.getSheet(sheetname);

		Row row = sheet.getRow(rowno);

		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFillBackgroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		cellStyle.setFillPattern(FillPatternType.LESS_DOTS);
		Cell cell = row.createCell(cellno);
		cell.setCellValue(valueToWrite);
		cell.setCellStyle(cellStyle);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);

	}

	public static void setCellRedColour(String excelpathname, String sheetname, int rowno, int cellno,
			String valueToWrite) throws Exception {

		File file = new File(excelpathname);

		FileInputStream fileInputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(fileInputStream);

		Sheet sheet = workbook.getSheet(sheetname);

		Row row = sheet.getRow(rowno);

		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFillBackgroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		cellStyle.setFillPattern(FillPatternType.LESS_DOTS);
		Cell cell = row.createCell(cellno);
		cell.setCellValue(valueToWrite);
		cell.setCellStyle(cellStyle);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
	}

	public static void setCellPinkColour(String excelpathname, String sheetname, int rowno, int cellno,
			String valueToWrite) throws Exception {

		File file = new File(excelpathname);

		FileInputStream fileInputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(fileInputStream);

		Sheet sheet = workbook.getSheet(sheetname);

		Row row = sheet.getRow(rowno);

		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFillBackgroundColor(IndexedColors.PINK.getIndex());
		cellStyle.setFillPattern(FillPatternType.LESS_DOTS);
		Cell cell = row.createCell(cellno);
		cell.setCellValue(valueToWrite);
		cell.setCellStyle(cellStyle);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
	}
	
}
