package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import framewrk.frme_wrk;

public class test{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\zain\\git\\Selenium\\Sele_frme\\addl_req\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		frme_wrk zzz = new frme_wrk(driver);
		driver.navigate().to("http://www.amazon.in");
		zzz.mouseHover("//*[@id='nav-link-yourAccount']");
		zzz.sleep(10000);
		//zzz.click("//*[@id='nav-flyout-ya-signin']/a/span");
		zzz.click("//*[@id='nav-flyout-ya-newCust']/a");
		zzz.sleep(10000);
		driver.close();
		//*[@id="nav-flyout-ya-signin"]/a/span
	}

}