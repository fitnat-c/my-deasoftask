package com.ideasoft.pages;

import com.ideasoft.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "(//div[@class='showcase-buttons'])[1]")
    public WebElement productItem;

    @FindBy(xpath = "(//a[@href='/urun/yeniurun'])[2]")
    public WebElement productDetail;

    @FindBy(xpath = "//span[contains(text(), 'SEPETE EKLE')]")
    public WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='shopping-information-cart-inside']")
    public WebElement sepetinizeEklenmistirText;

}
