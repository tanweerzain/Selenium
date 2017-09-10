package driver;

import framewrk.frme_wrk;

public class test{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Excelfile aa = new Excelfile();
		
		aa.openfile();
		aa.selectsheet(0);
		String[] values = aa.getrow();
		System.out.println(values[0] + " " + values[1] + " " + values[2] +  " " + values[3]);
		frme_wrk zzz = new frme_wrk("Amazon");

		while (aa.hasnextrow()){
			values = aa.getrow();
			if (values[0] != "") 
				System.out.println(values[0] + " " + values[1] + " " + values[2] +  " " + values[3]);
				zzz.framewrk_driver(values);
		}
		aa.fileclose();
		System.exit(0);
/*		zzz.gotourl("http://www.amazon.in");
		zzz.logger("first page", true);
        //zzz.sleep(10000);
        List<WebElement> elements = driver.findElements(By.cssSelector("*"));
        System.out.println("ghjhgkj "+elements.size());
        for(WebElement ele:elements)
        {

                System.out.println(ele.getAttribute("id"));// + " - " + ele.getText());              //for getting text of each element

        }
        System.exit(0);
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
		zzz.closebrowser();*/
	}


}