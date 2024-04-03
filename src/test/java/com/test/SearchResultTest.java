package com.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pageObject.CartPage;
import com.pageObject.SearchPage;
import com.utils.Baseutils;

public class SearchResultTest extends Baseutils {
	@DataProvider
	public Object[][] dataset() {

		return new Object[][] { { "stainless work table", "Table" } };

	}

	@Test(dataProvider = "dataset")
	public void searchTest(String searchProduct, String searchWord) throws Throwable {
		
		SoftAssert softassert = new SoftAssert();
        //2.Search for 'stainless work table'
		landingpage.enterTextOnSearchTextBox(searchProduct);
		SearchPage searchpage = new SearchPage(driver);
		int lastPage = searchpage.GetLastPageNumber();
		List<WebElement> productTitles = searchpage.getProductTitles();
		
		for (int p = 1; p <= lastPage; p++) {
			// Dynamic XPATH for navigating to next page
			WebElement pageclick = driver.findElement(By.xpath("//div[@id='paging']/nav/ul/li/a[text()='" + p + "']"));

			pageclick.click();

			for (int i = 0; i < productTitles.size(); i++) {
				String productTitle = productTitles.get(i).getText();
            //3.Check the search result ensuring every product has the word "Table" in its title.
				softassert.assertTrue(productTitle.contains(searchWord),
						"Search result is showing some product which dont have the word " + searchWord
								+ " in its title.");

			}

		}

		List<WebElement> addToCartButtons = searchpage.getAddToCartButtons();
		int lastItemWithAddToCart = addToCartButtons.size();
		//4.Add the last of found items to Cart.
		addToCartButtons.get(lastItemWithAddToCart - 1).click();
		searchpage.clickOnPopUpCloseButton();
		searchpage.clickOnCartButton();
		CartPage cartpage = new CartPage(driver);
		//5.Empty Cart.
		cartpage.clickOnEmptyCartButton();
		cartpage.clickOnEmptyCartPopUpButton();

		softassert.assertAll();

	}

}
