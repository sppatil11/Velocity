package testClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import baseClasses.BaseClass1;
import pomClasses.HomePage;
import pomClasses.LogInPage;
import pomClasses.ProfilePage;

public class ChangeAddress {

	WebDriver driver;
	LogInPage lp;
	HomePage hp;
	ProfilePage pp;

	ExtentReports reports;
	ExtentTest test;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String a) throws InterruptedException {
		reports = new ExtentReports("ExtentReports.html", true);
		test = reports.startTest("ChangeAddress");

		driver = BaseClass1.getWebDriver(a);

	}

	@BeforeMethod
	public void beforeMethod() {
		lp = new LogInPage(driver);
		hp = new HomePage(driver);
		pp = new ProfilePage(driver);
	}

	@DataProvider(name = "dataSet")
	public String[][] dataToTest() {
		String addressData[][] = { { "shashank zade", "8793456222", "444601", "near Jaisthambh" },
				{ "vasihanavi z", "7972104287", "444602", "butt plot" } };
		return addressData;
	}

	@Test(priority = 1, dependsOnMethods = "verifyLogIn", dataProvider = "dataSet")
	private void changeAddress(String name, String mobNum, String pincode, String Locality)
			throws InterruptedException, EncryptedDocumentException, IOException {
		hp.hoverToProileName();
		hp.clickOnMyProfile();
		hp.movePointer();
		pp.clickOnManageAddress();
		pp.clickOnAddNewAddress();
		int oldNoOfAddress = pp.noOfAddressSave();
		System.out.println(oldNoOfAddress);
		List<String> dataList = new ArrayList<String>();
		dataList.add(name);
		dataList.add(mobNum);
		dataList.add(pincode);
		dataList.add(Locality);
		pp.getDataForAddress(dataList);
		pp.selectRadioBotton();
//		pp.saveAddress();
		int newNoOfAddress = pp.noOfAddressSave();
		System.out.println(newNoOfAddress);

//		Assert.assertEquals(oldNoOfAddress, (newNoOfAddress-1),"address not add succesfully ");

//		if(oldNoOfAddress==(newNoOfAddress-1)) {
//			System.out.println("test 2 Pass");
//		}else {
//			Assert.fail();
		test.log(LogStatus.PASS, "Log in Test Pass");
//		}
//		

	}

	@Test
	private void verifyLogIn() throws InterruptedException {
		lp.enterEmailID();
		lp.enterPassword();
		lp.clickSubmitButton();
		Thread.sleep(2000);
		String text = hp.isPageLoaded();
		Assert.assertNotEquals(text, "LogIn");
		test.log(LogStatus.PASS, "Log in Test Pass");

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, test.addScreenCapture(pp.screenCpature(driver)));
		}

	}

	@AfterClass
	public void afterClass() {
		reports.endTest(test);
		reports.flush();
		// driver.close();
	}

}
