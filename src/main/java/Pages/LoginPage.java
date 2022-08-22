package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import common.AutomationConstants;
import common.utilityFunctions.Page;
import common.utilityFunctions.ReadProperties;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import static common.AppiumManager.moblieDriver;

public class LoginPage extends Page{
	private static Logger log = Logger.getLogger(LoginPage.class.getName());
	ReadProperties readProperties = new ReadProperties();
	
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
	public WebElement skipSignIn;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.TextView")
	public WebElement inStoreCode;
	
	
	public LoginPage() {
        PageFactory.initElements(new AppiumFieldDecorator(moblieDriver), this);
    }
	
	public void clickSkipSignIn() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("Method called and now click the button");
		clickBy(skipSignIn,"Skip login Button");
//		moblieDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.TextView"))
//		.click();
		System.out.println("Button click done");
	}
}

//public class LoginPage{
//	private static Logger log = Logger.getLogger(LoginPage.class.getName());
//	ReadProperties readProperties = new ReadProperties();
//	
//	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
//	public WebElement skipSignIn;
//	
//	public void clickSkipSignIn() throws InterruptedException {
//		System.out.println("Inside clickSkipSignIn() method.");
//		
////		new WebDriverWait(moblieDriver, AutomationConstants.WAIT_UNTIL_ELEMENT)
////		.until(ExpectedConditions.elementToBeClickable(skipSignIn));
//		
//		System.out.println("SkipSignin button is visible now clicking the button");
////		skipSignIn.click();
//		
//		System.out.println("Method called and now click the button");
//		moblieDriver.findElement(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button")).click();
////		skipSignIn.click();
//		System.out.println("Button click done");
//	}
//}
