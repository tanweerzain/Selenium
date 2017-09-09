package driver;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import framewrk.frme_wrk;

public class test{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		frme_wrk zzz = new frme_wrk("Amazon");
		zzz.gotourl("http://www.amazon.in");
		zzz.logger("first page", true);
        //zzz.sleep(10000);
        /*List<WebElement> elements = driver.findElements(By.cssSelector("*"));
        System.out.println("ghjhgkj "+elements.size());
        for(WebElement ele:elements)
        {

                System.out.println(ele.getAttribute("id"));// + " - " + ele.getText());              //for getting text of each element

        }
        System.exit(0);*/
		//zzz.mouseHover("//*[@id='nav-link-accountList']");
		zzz.mouseHoveract("//*[@id='nav-tools']/a[1]");
		zzz.logger("menu", true);
		zzz.click("//*[@id='nav-flyout-ya-newCust']/a");
		zzz.logger("blank registration page", true);
		zzz.senddata("//*[@id='ap_customer_name']", "zain341");
		zzz.click("//*[@id='auth-country-picker-container']/span");
		//zzz.click("//*[@id='auth-country-picker_90']");
		zzz.click("//*[@id='auth-country-picker_89']");
		zzz.senddata("//*[@id='ap_phone_number']", "89874983453");
		zzz.senddata("//*[@id='ap_email']","assddadsf@gmail.com");
		zzz.senddata("//*[@id='ap_password']", "Qwerty123");
		zzz.logger("filled registration page", true);
		//zzz.click("//*[@id='continue']");
		
		zzz.sleep(5000);
		zzz.closebrowser();
	}

}