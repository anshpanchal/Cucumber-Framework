package com.vTiger.Pages;

import com.aventstack.extentreports.ExtentTest;
import com.vTiger.Utilities.PageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends PageActions {

    public LoginPage(WebDriver driver, ExtentTest extentTestLogger){
        super(driver,extentTestLogger);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="input[name='user_name']")
    WebElement user_name;

    @FindBy(css="input[name='user_password']")
    WebElement user_password;

    @FindBy(css = "input[name='Login']")
    WebElement btn_login;

    @FindBy(xpath = "//*[contains(text(),'You must specify a valid username and password')]")
    WebElement errorMsg;

//    String userName = "input[name='user_name']";
//    By username = By.cssSelector("input[name='user_name']");

    public void login(String userId, String password){
//        driver.findElement(By.cssSelector(userName)).sendKeys("admin");
//        driver.findElement(username).sendKeys("admin");
       setUserId(userId);
       setUser_password(password);
       clickLogin();
    }

    public void setUserId(String userId){
        setInput(user_name,userId, userId+":userName has been entered into the username field.");
    }

    public void validateUserId(){
        elementExist(user_name, "Element exist on the login page");
    }

    public void setUser_password(String password){
        setInput(user_password,password,password + ":password has been entered into the password field");
    }
    public void clickLogin(){
//        btn_login.click();
        clickElement(btn_login,"Login button clicked");
    }
    public void validateErrorMsg(){
        elementExist(errorMsg,"Error message validated successfully");
    }
}
