package com.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {

	public WebDriver driver;

	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@id='paging']/nav/ul/li/a[contains(@aria-label,'last')]")
	WebElement lastpage;
	@FindBy(xpath = "//div[@id='product_listing']/div/div/a/span")
	List<WebElement> productTitles;
	@FindBy(xpath = "//input[@value='Add to Cart']")
	List<WebElement> addToCartButtons;
	@FindBy(xpath = "//p[@class='msg']/button")
	WebElement popUpCloseButton;

	public int GetLastPageNumber() {

		return Integer.parseInt(lastpage.getText());

	}

	public List<WebElement> getProductTitles() {
		return productTitles;
	}

	public List<WebElement> getAddToCartButtons() {
		return addToCartButtons;
	}

	public void clickOnPopUpCloseButton() {
		popUpCloseButton.click();
		
	}
}
