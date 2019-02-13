package com.automationpractice.pageObject;

import com.automationpractice.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {
    @FindBy(xpath = "//a[@title='Log in to your customer account']")
    private WebElement singInButton;
    @FindBy(css = "ul[id='homefeatured'] li")
    private List<WebElement> allProductsAvailableInMainPage;
    @FindBy(css = "ul[id='homefeatured'] div[class='right-block'] span[class='price product-price']")
    private List<WebElement> priceStringFromProductFrame;
    @FindBy(css = "ul[id='homefeatured'] div[class='right-block'] a[class='button ajax_add_to_cart_button btn btn-default']")
    private List<WebElement> addButtonFromProductFrame;
    @FindBy(css = "div[id='layer_cart'] span[class='continue btn btn-default button exclusive-medium']")
    private WebElement continueButtonFromPopUpWindowAfterAddingProduct;
    @FindBy(css = "div[class='shopping_cart'] a[title='View my shopping cart']")
    private WebElement dropDownMenuCart;
    @FindBy(xpath = "//span[@class='price cart_block_total ajax_block_cart_total']")
    private WebElement totalPriceInDropDownMenuCart;


    private WebDriver driver;
    private Waits waits;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);

        PageFactory.initElements(driver, this);
    }

    public void clickOnSingInButton() {
        waits.waitForElementToBeClickable(singInButton);
        singInButton.click();
    }

    public Double addingAllClothesAndReturnPrice() throws InterruptedException {
        int numberOfAllProducts = allProductsAvailableInMainPage.size();
        Actions action = new Actions(driver);
        //for totalPrice for assertion
        Double totalPrice = 0.00;
        //loop that add all all clothes, check its price and add the Price to totalPrice
        for (int i = 0; i < numberOfAllProducts; i++) {
            totalPrice = totalPrice + Double.parseDouble(priceStringFromProductFrame.get(i).getText().substring(1));
            action.moveToElement(allProductsAvailableInMainPage.get(i)).build().perform();
            Thread.sleep(1000);
            addButtonFromProductFrame.get(i).click();
            Thread.sleep(3000);
            continueButtonFromPopUpWindowAfterAddingProduct.click();
        }
        if (totalPrice > 0.00) {
            totalPrice += 2.00;
        }
        return totalPrice;
    }
    public Double priceFromCartDropDownMenu() throws InterruptedException {
        //steps to get the total value from dropDown Cart Menu
        Actions action = new Actions(driver);
        action.moveToElement(dropDownMenuCart).build().perform();
        Thread.sleep(5000);
        Double totalPriceInCart = Double.parseDouble(totalPriceInDropDownMenuCart.getText().substring(1));
        return totalPriceInCart;
    }
}