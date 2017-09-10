package framewrk;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.io.Files;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;

public class frme_wrk {

	WebDriver driver;
	Logger logger;  

	public static long stepno=-2;
	
	public frme_wrk(String testcase) {
		super();
		startlogging(testcase);
		logger("Setting up and launching Chrome browser...", false);
		this.driver = getdriver();
		logger("Beginning TestCase: " + testcase, false);

	}

	public void startlogging(String testcase){
		logger = Logger.getLogger(testcase);
	    FileHandler fh;  
	    try {  

	    	Path pathToFile = Paths.get("C:/Users/zain/desktop/test/MyLogFile.log");
	        //Files.createDirectories(pathToFile.getParent());
	        File parentDir = new File(pathToFile.getParent().toString());
          if(!parentDir.exists()) parentDir.mkdirs();
	        
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("C:/Users/zain/desktop/test/MyLogFile.log"); 
	        logger.addHandler(fh);
	        
	        SimpleFormatter formatter = new SimpleFormatter();
	        fh.setFormatter(formatter);  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	public WebDriver getdriver(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\zain\\git\\Selenium\\Sele_frme\\addl_req\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	public void closebrowser(){
		driver.close();
	}

	public void gotourl(String url){
		driver.navigate().to(url);
	}
	
	public void sleep(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mouseHover(String xpath) {
		WebElement HoverElement = isElementPresent(xpath);
			if (HoverElement != null) {
				String mouseHoverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseHoverScript,HoverElement);
			} else {
				System.out.println("Element was not visible \n");
			}
	}

	public void mouseHoveract(String xpath) {
		WebElement HoverElement = isElementPresent(xpath);
			if (HoverElement != null) {
				Actions action = new Actions(driver);
				try {
					new Robot().mouseMove(0, 0);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		        action.moveToElement(HoverElement).build().perform();
			} else {
				System.out.println("Element was not visible \n");
			}
	}
	
	public WebElement isElementPresent(String xpath) {
		boolean present = false;
		Wait<WebDriver> wait = new FluentWait<WebDriver> (driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class,ElementNotVisibleException.class);
		WebElement findElement = null;
		try {
			findElement = wait.until(new Function<WebDriver, WebElement>() {
		        public WebElement apply(WebDriver webDriver) {
		            return driver.findElement(By.xpath(xpath));}
		    });
			if (findElement.isDisplayed()
					|| findElement.isEnabled())
				present = true;
		} catch (StaleElementReferenceException e) {
			present = false;
			System.out.println("Element with " + xpath
				+ "is not attached to the page document"
				+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			present = false;
			System.out.println("Element " + xpath + " was not found on page"
				+ e.getStackTrace());
		} catch (Exception e) {
			present = false;
			e.printStackTrace();
			System.out.println("Error occurred while hovering " + xpath 
				+ e.getStackTrace());
		}
		return present?findElement:null;
	}
	
	public void click(String xpath){
		WebElement testElement = isElementPresent(xpath);
		if (testElement != null) {
			testElement.click();
			//
		} else {
			System.out.println("Element was not visible \n");

		}
	}
	
	public void logger(String stepname, boolean takeshot){
		stepno++;
		
	    logger.info("Step" + (stepno+2) + ": " + stepname);
	    if (takeshot)
	    {
	    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	try {
	    		Files.copy(scrFile, new File("C:/Users/zain/desktop/test/" + "Step" + stepno + ".png"));
	    	} catch (IOException e) {
	    	// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    }
	}
	
	public void senddata(String xpath, String data){
		WebElement testElement = isElementPresent(xpath);
		if (testElement != null) {
			testElement.sendKeys(data);
		} else {
			System.out.println("Element was not visible \n");

		}
	}
	
	public void boxselect(String xpath, String value){
		Select ss = new Select(isElementPresent(xpath));
		ss.selectByVisibleText(value);
	}
	public void framewrk_driver(String[] values){
		
		switch (values[0].toLowerCase()){
		
		case "gotourl":
			this.gotourl(values[2]);
			break;
		case "boxselect":
			this.boxselect(values[1], values[2]);
			break;
		case "mousehoveract":
			this.mouseHoveract(values[1]);
			break;
		case "click":
			this.click(values[1]);
			break;
		case "senddata":
			this.senddata(values[1], values[2]);
			break;
		case "logger":
			this.logger(values[2], (values[3].compareToIgnoreCase("y") == 0 || values[3].compareToIgnoreCase("yes")==0)?true:false);
			break;
		case "sleep":
			this.sleep(Integer.parseInt(values[2]));
			break;
		case "closebrowser":
			this.closebrowser();
			break;
		default:
			this.logger("Error - no such command", false);
			break;
		}
		
	}
}
