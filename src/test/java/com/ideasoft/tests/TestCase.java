package com.ideasoft.tests;

import com.ideasoft.pages.CartPage;
import com.ideasoft.pages.ProductPage;
import com.ideasoft.pages.SearchPage;
import com.ideasoft.utility.Utility;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase extends Utility {

    @Test
    public void testAddToCartSuccess(){



        CartPage loginPageElements=new CartPage();

        SearchPage searchPage=new SearchPage();
        ProductPage productPage=new ProductPage();


        loginPageElements.goToHomePage();

        sendKeyToElement(searchPage.searchInput,"ürün");

        searchPage.searchInput.sendKeys(Keys.ENTER);

        scrollToCenter(productPage.productItem);

        productPage.productDetail.click();

        clickElementWithWait(productPage.addToCartButton);
           waits(2);

        Assert.assertEquals(productPage.sepetinizeEklenmistirText.getText(),"SEPETİNİZE EKLENMİŞTİR");

    }



    @Test
    public void testAddingFiveItemsToCart(){

        CartPage loginPageElements=new CartPage();
        SearchPage searchPage=new SearchPage();
        ProductPage productPage=new ProductPage();

        loginPageElements.goToHomePage();

       sendKeyToElement(searchPage.searchInput,"ürün");
       searchPage.searchInput.sendKeys(Keys.ENTER);

        scrollToCenter(productPage.productItem);

        productPage.productDetail.click();

        clickElementMultipleTimes(productPage.addToCartButton,5,2);

        clickElement(loginPageElements.cart);
        Assert.assertEquals(loginPageElements.cartItemCount.getAttribute("value"),"5");



}

}
