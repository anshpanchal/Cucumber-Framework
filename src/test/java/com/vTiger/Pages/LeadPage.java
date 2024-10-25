package com.vTiger.Pages;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadPage extends HeaderPage {

    public LeadPage(WebDriver driver, ExtentTest extentTestLogger){
        super(driver,extentTestLogger);

        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@name='lastname']")
    WebElement tb_lastName;

    @FindBy(xpath = "//input[@name='company']")
    WebElement tb_company;

    @FindBy(xpath = "//input[@name='button123']")
    WebElement btn_save;


    public void createLeadWithMandatoryParameter(String lastname, String company){
        setTb_lastName(lastname);
        setTb_company(company);
        clickSave();
    }

    public void setTb_lastName(String lastName){
        setInput(tb_lastName, lastName,lastName+" :lastname has been entered into lastName field");
    }
    public void setTb_company(String company){
        setInput(tb_company,company,company+" :company has entered into company field");
    }
    public void clickSave(){
//        btn_save.click();
        clickElement(btn_save,"Save button clicked");
    }
}
