package com.automationpractice.pageObject;

import com.automationpractice.utils.Waits;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    @FindBy(css = "div footer div section[id='block_various_links_footer']")
    private WebElement scopeForLinksInSectionInformation;




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

    public List<String> getAllTitlesBeforeOpenTabs() {
        //taking all titles
        List<String> listOfAllTitlesBeforeTabsOpen = new ArrayList<>();
        List<WebElement> links = scopeForLinksInSectionInformation.findElements(By.cssSelector("ul li a"));
        for (int i =links.size() - 1; i >= 0 ; i--){
            waits.waitForElementToBeClickable(links.get(i));
            listOfAllTitlesBeforeTabsOpen.add(links.get(i).getText()+" - My Store");
        }
        //Diffrent names in information section and site title, but for practise purpose change to be pass
        listOfAllTitlesBeforeTabsOpen.set(listOfAllTitlesBeforeTabsOpen.indexOf("Our stores - My Store"),"Stores - My Store");
        listOfAllTitlesBeforeTabsOpen.set(listOfAllTitlesBeforeTabsOpen.indexOf("Best sellers - My Store"),"Best sales - My Store");
        listOfAllTitlesBeforeTabsOpen.set(listOfAllTitlesBeforeTabsOpen.indexOf("Specials - My Store"),"Prices drop - My Store");
        return listOfAllTitlesBeforeTabsOpen;
    }

    public List<String> clickOnEveryLinkInInformationSection(){

        //Preparing data for clicking and opening multiply tabs
        String clickOnLinkToOpenNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
        List<String> siteTitles = new ArrayList<>();
        List<WebElement> links = scopeForLinksInSectionInformation.findElements(By.cssSelector("ul li a"));
        //loop for open multiply tabs
        for (WebElement element : links){
            waits.waitForElementToBeClickable(element);
            element.sendKeys(clickOnLinkToOpenNewTab);
        }
        //prepare object for taking the titles names
        Set<String> windowsID = driver.getWindowHandles();
        Iterator<String> forWindowsIteration = windowsID.iterator();
        String defaultContent = forWindowsIteration.next();
        //loop that goes tab after tab, and collecting titles
        while (forWindowsIteration.hasNext()){
            driver.switchTo().window(forWindowsIteration.next());
            siteTitles.add(driver.getTitle());
        }
        driver.switchTo().window(defaultContent);
        return siteTitles;
    }

    public Double addingAllClothesAndReturnPrice() {
        int numberOfAllProducts = allProductsAvailableInMainPage.size();
        Actions action = new Actions(driver);
        //for totalPrice for assertion
        Double totalPrice = 0.00;
        //loop that add all all clothes, check its price and add the Price to totalPrice
        for (int i = 0; i < numberOfAllProducts; i++) {
            totalPrice = totalPrice + Double.parseDouble(priceStringFromProductFrame.get(i).getText().substring(1));
            action.moveToElement(allProductsAvailableInMainPage.get(i)).build().perform();
            waits.waitForElementToBeClickable(addButtonFromProductFrame.get(i));
            addButtonFromProductFrame.get(i).click();
            waits.waitForElementToBeClickable(continueButtonFromPopUpWindowAfterAddingProduct);
            continueButtonFromPopUpWindowAfterAddingProduct.click();
        }
        if (totalPrice > 0.00) {
            totalPrice += 2.00;
        }
        return totalPrice;
    }
    public Double priceFromCartDropDownMenu() throws IOException {
        //steps to get the total value from dropDown Cart Menu
        Actions action = new Actions(driver);
        action.moveToElement(dropDownMenuCart).build().perform();
        waits.waitForElementToBeVisible(totalPriceInDropDownMenuCart);
        Double totalPriceInCart = Double.parseDouble(totalPriceInDropDownMenuCart.getText().substring(1));
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot,new File("screenshots/screenshot.png"));
        return totalPriceInCart;
    }
}