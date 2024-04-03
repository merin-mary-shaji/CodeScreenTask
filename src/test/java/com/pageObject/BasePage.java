package com.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	public WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='searchval']")
	WebElement searchTextBox;
	@FindBy(xpath = "//ul[@id='awesomplete_list_1']/li/span[2]/mark")
	List<WebElement> dropDownOptions;
	@FindBy(xpath = "//span[@id='cartItemCountSpan']")
	WebElement cartButton;

	public void enterTextOnSearchTextBox(String searchText) {
		searchTextBox.sendKeys(searchText);

		for (WebElement option : dropDownOptions) {
			if (option.getText().equalsIgnoreCase(searchText)) {
				option.click();
				break;
			}
		}
	}

	public void clickOnCartButton() {
		cartButton.click();
	}

}
