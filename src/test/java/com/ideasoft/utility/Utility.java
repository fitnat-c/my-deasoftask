package com.ideasoft.utility;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.IntStream;

public class Utility  {
    public static boolean isClickable(WebElement element, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(waitTime));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementVisible(WebElement element, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(waitTime));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void scrollTo(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView()", element);
    }

    public static void scrollToCenter(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView({block:\"center\"})", element);
    }

    public static void waits(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void hoverOver(WebElement element, int waitTime) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).pause(Duration.ofSeconds(waitTime)).build().perform();
        // actions.moveToElement(element).build().perform();
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void clickElementWithWait(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }
    public void clickElementMultipleTimes(WebElement element, int times, int waitTime) {
        IntStream.range(0, times).forEach(i -> {
            clickElementWithWait(element);
            waits(waitTime);
        });
    }
    public void sendKeyToElement(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }


    public void clearElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
    }
    public void deleteElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    }



    public void sendKeyToElementWithKeys(WebElement element, String text, Keys keys) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
        waits(1);
        element.sendKeys(keys);
    }
    public boolean justSendKeysToElement(WebElement element, String text){
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    public void selectElementByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void selectElementByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public String getTextElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public void clickWithJSExecutor(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public void sendKeyWithJSExecutor(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript(("arguments[0].value=" + text + ";"), element);
    }


    public static void alertAccept() {

        Alert alert = Driver.getDriver().switchTo().alert();

         alert.accept();
    }

}
