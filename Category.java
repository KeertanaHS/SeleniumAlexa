package com.test.google;

/*
 * Using Selenium to extra top websites from Alexa[ http://www.alexa.com/topsites] by Category and country
 * Keertana HS
 */
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Category {

	public static void main(String[] args) throws InterruptedException {
		List<String> siteByCountry = new ArrayList<String>();
		// Create a new instance of the Firefox driver

		WebDriver driver = new FirefoxDriver();

		driver.get("http://www.alexa.com/topsites/");
		// Added inspect element sites to driver
		WebElement element = driver.findElement(By.xpath("//li[@id = 'topsites-category']/a"));
		element.click();
		// It will display the page title
		System.out.println("Page title is: " + driver.getTitle());
		// It selects the sub category of category page
		List<WebElement> titles = driver.findElements(By.cssSelector("ul[class='subcategories span3'] li a"));

		System.out.println(" =============== Top Sites ================= ");
		for (int j = 0; j < titles.size(); j++) {
			int count = 0;
			System.out.println(" --------- ");
			System.out.println("|" + titles.get(j).getText() + "|");
			System.out.println(" --------- ");
			titles.get(j).click();
			List<WebElement> siteNames = driver
					.findElements(By.xpath("//li[@class = 'site-listing']//div[@class = 'desc-container']/p/a"));

			for (WebElement site : siteNames) {
				if (count <= 10) {
					System.out.println(site.getText());
					siteByCountry.add(site.getText());
					count++;
				}
			}
			System.out.println("-------------------------------------------------------------------------");
			Thread.sleep(1000);
			driver.navigate().back();
			// Continue the sub categories
			titles = driver.findElements(By.cssSelector("ul[class='subcategories span3'] li a"));

		}

		// Close the browser
		driver.quit();

	}

}
