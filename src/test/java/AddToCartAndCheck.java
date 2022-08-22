import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.LoginPage;

public class AddToCartAndCheck extends BaseTestClass{
	LoginPage loginPage;
	
	@BeforeTest
	public void beforeTest() {
		loginPage = new LoginPage();
	}
	
	@Test(description = "Skip the signin Page")
	public void addToCart() throws InterruptedException {
		System.out.println("Before click method call");	
		loginPage.clickSkipSignIn();
	}
}
