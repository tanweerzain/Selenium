package framewrk;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class frme_wrk {

	WebDriver driver;

	public frme_wrk(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public static void sleep(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mouseHover(String xpath) {
		WebElement HoverElement = driver.findElement(By.xpath(xpath));
		try {
			if (isElementPresent(HoverElement)) {
				
				String mouseHoverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				
				((JavascriptExecutor) driver).executeScript(mouseHoverScript,HoverElement);
				//
			} else {
				System.out.println("Element was not visible \n");

			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + HoverElement
					+ "is not attached to the page document"
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + HoverElement + " was not found on page"
					+ e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering"
					+ e.getStackTrace());
		}
	}

	public boolean isElementPresent(WebElement element) {
		boolean present = false;
		try {
			if (element.isDisplayed()
					|| element.isEnabled())
				present = true;
		} catch (NoSuchElementException e) {
			present = false;
		} catch (StaleElementReferenceException e) {
			present = false;
		}
		return present;
	}
	
	public void click(String xpath){
		WebElement testElement = driver.findElement(By.xpath(xpath));
		if (isElementPresent(testElement)) {
			testElement.click();
			//
		} else {
			System.out.println("Element was not visible \n");

		}
	}
}
