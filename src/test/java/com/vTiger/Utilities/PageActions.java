package com.vTiger.Utilities;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class PageActions {
    public WebDriver driver;
    public WebDriverWait wait;
    public ExtentTest extentTestLogger;

    public PageActions(WebDriver driver, ExtentTest extentTestLogger) {
        this.driver = driver;
        this.extentTestLogger = extentTestLogger;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /*
        public void webDriverExplicitWait(WebElement element){
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    */
    public void visibilityOfElementLocated(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void setInput(WebElement element, String value, String loggerMsg) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(value);
            extentTestLogger.pass(loggerMsg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            extentTestLogger.fail(e.getMessage());
            extentTestLogger.fail("unable to click on element due to error "+e.getMessage()+"<a href='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
        }
    }
/*
    public void setInput(By by, String value) {
        try {
            visibilityOfElementLocated(by);
            WebElement ele = driver.findElement(by);
            ele.clear();
            ele.sendKeys(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setInput(String locator, String value) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
//            locator = "input[name='user_name']";
            WebElement ele = driver.findElement(By.cssSelector(locator));
            ele.clear();
            ele.sendKeys(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
*/
    public void clickElement(WebElement element, String loggerMsg) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            extentTestLogger.pass(loggerMsg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            extentTestLogger.fail(e.getMessage());
            extentTestLogger.fail("unable to click on element due to error "+e.getMessage()+"<a href='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
        }
    }

    public void elementExist(WebElement element, String loggerMsg) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.isDisplayed();
            extentTestLogger.pass(loggerMsg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            extentTestLogger.fail(e.getMessage());
            extentTestLogger.fail("Element does not exist due to error "+e.getMessage()+"<a href='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");

        }
    }
    public String getScreenshot(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date = new Date();
        String str = dateFormat.format(date);
        TakesScreenshot takesScreenshot = ((TakesScreenshot)driver);

        //Call getScreenshot as method to create image file
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/src/test/java/com/vTiger/Reports/Screenshots/image_"+str+"_.png";

        //Move image file to new destination
        File destinationFile = new File(path);

        //copy file at destination
        try{
            FileUtils.copyFile(sourceFile,destinationFile);
        } catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }
}
