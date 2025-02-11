package com.ideasoft.pages;


import com.ideasoft.utility.ConfigReader;
import com.ideasoft.utility.Driver;
import com.ideasoft.utility.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends Utility {

    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath ="//a[@title='Sepet']")
    public WebElement cart;
    @FindBy(xpath = "//input[@data-stocktype='Piece']")
    public WebElement cartItemCount;

  public void goToHomePage(){
      Driver.getDriver().get(ConfigReader.getProperty("homepageUrl"));

  }




}
