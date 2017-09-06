package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import framewrk.frme_wrk;

public class test extends frme_wrk{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\zain\\git\\Selenium\\Sele_frme\\addl_req\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://www.google.com");
		sleep(10000);
		driver.close();
	}

}
