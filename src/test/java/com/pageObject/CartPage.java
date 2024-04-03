package com.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

	public WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[text()='Empty Cart']")
	WebElement emptyCartButton;
	@FindBy(xpath = "//button[text()='Empty']")
	WebElement emptyCartPopUpButton;

	public void clickOnEmptyCartButton() {
		emptyCartButton.click();
	}

	public void clickOnEmptyCartPopUpButton() {
		emptyCartPopUpButton.click();
	}

}
