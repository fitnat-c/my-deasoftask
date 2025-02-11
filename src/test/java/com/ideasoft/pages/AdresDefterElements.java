package com.ideasoft.pages;


import com.ideasoft.utility.ConfigReader;
import com.ideasoft.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdresDefterElements {
    public AdresDefterElements() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath ="//input[@aria-label='Search']" )
    public WebElement search;

    @FindBy(xpath = "(//div[@class='showcase-buttons'])[1]")
    public WebElement product;

    @FindBy(xpath = "(//a[@href='/urun/yeniurun'])[2]")
    public WebElement product2;

    @FindBy(xpath = "//span[contains(text(), 'SEPETE EKLE')]")
    public WebElement sepetEkle;

    @FindBy(xpath = "//div[@class='shopping-information-cart-inside']")
    public WebElement sepetinizeEklenmistirText;

    @FindBy(xpath ="//a[@title='Sepet']")
    public WebElement sepet;
    @FindBy(xpath = "//input[@data-stocktype='Piece']")
    public WebElement sepet5;
    @FindBy(xpath = "(//img[@class='lazyload'])[1]")
    public WebElement act;

  public void homePage(){
      Driver.getDriver().get(ConfigReader.getProperty("homepageUrl"));
  }
}
