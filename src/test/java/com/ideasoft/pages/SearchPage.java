package com.ideasoft.pages;

import com.ideasoft.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {public SearchPage() {
    PageFactory.initElements(Driver.getDriver(), this);
};
    @FindBy(xpath ="//input[@aria-label='Search']" )
    public WebElement searchInput;

}
