package com.vTiger.Pages;

import com.aventstack.extentreports.ExtentTest;
import com.vTiger.Utilities.PageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends PageActions {

    public HeaderPage(WebDriver driver, ExtentTest extentTestLogger) {
        super(driver,extentTestLogger);
        PageFactory.initElements(driver,this);
    }

    @FindBy(linkText = "Logout")
    WebElement link_logout;

    @FindBy(linkText = "New Lead")
    WebElement link_newLead;

    @FindBy(linkText = "Leads")
    WebElement link_Leads;

    public void clickLogout(){
        clickElement(link_logout,"Link Logout button clicked");
    }
    public void clickNewLead(){
        clickElement(link_newLead,"Link New Lead clicked");
    }
    public void clickLead(){
        clickElement(link_Leads,"Link Leads clicked");
    }

    public void validateLogout(){
        elementExist(link_logout,"Logout exist on home page");
    }
}
