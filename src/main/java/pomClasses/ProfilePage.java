package pomClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityClasses.Util1;

public class ProfilePage extends Util1 {

	WebDriver driver;
	@FindBy(xpath = "//div[text()='Manage Addresses']")
	private WebElement manageAddress;

	@FindBy(xpath = "//*[@class='_1QhEVk']")
	private WebElement addNewAddress;

	@FindBy(xpath = "//textarea")
	private WebElement textarea;

	@FindBy(xpath = "//*[@class='_1XFPmK']")
	private WebElement homeRadio;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveButton;

//	@FindBy(xpath = "//*[@class='_1CeZIA']")
//	private WebElement noOfAddress;

	public ProfilePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void clickOnManageAddress() {
		isElementVisible(driver, manageAddress);
		manageAddress.click();

	}

	public void clickOnAddNewAddress() {
		isElementVisible(driver, addNewAddress);
		addNewAddress.click();
//		Reporter.log("Click on Add new Address", true);
	}

	public void getDataForAddress(List<String> dataList) throws EncryptedDocumentException, IOException {
//		List<String> list = getMultipleDataFromExcel(0, 3);

		for (int i = 1; i <= 4; i++) {
			WebElement element = driver.findElement(By.xpath("((//form)[2]//input)[" + i + "]"));
			element.sendKeys(dataList.get(i - 1));
		}
		textarea.sendKeys("C/o Dr. Pramod Zade, Buty Plot");
//		Reporter.log("Entered Address Details", true);
	}

	public void selectRadioBotton() {
		// scrollIntoViewElement(driver, homeRadio);
		isElementVisible(driver, homeRadio);
		homeRadio.click();
	}

//	public void saveAddress() {
//		// scrollIntoViewElement(driver, saveButton);
//		isElementVisible(driver, saveButton);
//
//		saveButton.click();
//
//	}

	public int noOfAddressSave() throws InterruptedException {
		Thread.sleep(500);
		List<WebElement> list = new ArrayList<WebElement>();
		list = driver.findElements(By.xpath("//*[@class='_1CeZIA']"));
		return list.size();
	}
}
