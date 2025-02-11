package com.ideasoft.tests;

import com.ideasoft.pages.AdresDefterElements;
import com.ideasoft.utility.Utility;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SepetinizeEklenmiştitTC extends Utility {
    @Test
    public void test(){

        AdresDefterElements loginPageElements=new AdresDefterElements();//Locatarler almak için
        loginPageElements.homePage();//driver anasayfama gitmesi için

      sendKeyToElement(loginPageElements.search,"ürün"); // search alanına gider aradığım kelimeyi yazar
      loginPageElements.search.sendKeys(Keys.ENTER);// sonuçları göstermek için enter basma işlemi yapar
      scrollToCenter(loginPageElements.product);

      loginPageElements.product2.click(); // bulduğu ürün detayına girer
      clickElementWithWait(loginPageElements.sepetEkle); // ürünü sepete ekler
      waits(2);
      Assert.assertEquals(loginPageElements.sepetinizeEklenmistirText.getText(),"SEPETİNİZE EKLENMİŞTİR");
        clickElementWithWait(loginPageElements.sepetEkle);
        waits(2);
        clickElementWithWait(loginPageElements.sepetEkle);
        waits(2);
        clickElementWithWait(loginPageElements.sepetEkle);
        waits(2);
        clickElementWithWait(loginPageElements.sepetEkle);






        clickElement(loginPageElements.sepet);
        Assert.assertEquals(loginPageElements.sepet5.getAttribute("value"),"5");
    }


}
