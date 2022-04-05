package utilityClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util1 {
	public static void implicitWait(WebDriver driver, int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public static void moveToElement(WebDriver driver, WebElement element) {

		Actions act = new Actions(driver);
		act.moveToElement(element).pause(2000).build().perform();
	}

	public static void moveByOffset(WebDriver driver) {
		Actions act = new Actions(driver);
		act.moveByOffset(200, 0).release().build().perform();

	}

	public static boolean isElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

	}

	public static WebElement explicitWait(WebDriver driver, WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		WebElement elementReturn = wait.until(ExpectedConditions.visibilityOf(element));
		return elementReturn;
	}

	public static List<String> getMultipleDataFromExcel(int firstRow, int lastRow)
			throws EncryptedDocumentException, IOException {

		FileInputStream file = new FileInputStream("C:\\Users\\User\\Desktop\\flipk1.xlsx");
		List<String> dataList = new ArrayList<String>();
		Sheet sheet = WorkbookFactory.create(file).getSheet("Sheet2");

		for (int i = firstRow; i <= lastRow; i++) {
			try {
				String stringData = sheet.getRow(i).getCell(0).getStringCellValue();
				dataList.add(stringData);

			} catch (Exception e) {
				long intData = (long) sheet.getRow(i).getCell(0).getNumericCellValue();
				String string = String.valueOf(intData);
				dataList.add(string);
			}
		}

		return dataList;

	}

	public static void scrollIntoViewElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	public  String  screenCpature(WebDriver driver) throws IOException {
		Date date = new Date();
		String modifieddate=new SimpleDateFormat("MM-dd-hh-mm-ss").format(date);
		
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("screenshot"+modifieddate+".png");
		String path = dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, dest);
		return path;
	
	}
}
