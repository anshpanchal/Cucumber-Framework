package com.vTiger.StepDefinitions;

import com.codoid.products.exception.FilloException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class Login extends BaseDefinitions {

    @Before
    public void getScenarioName(Scenario scenario) throws FilloException, IOException {
        if(extentReports == null){
            initiation();
        }

        TC_Name = scenario.getName();
        extentTestLogger = extentReports.createTest(TC_Name);
    }
    @After
    public void saveReport(){
        extentReports.flush();
    }

    //    public WebDriver driver;
    @Given("user should be on login page")
    public void user_should_be_on_login_page() throws FilloException, IOException {
        launchApplication();
        System.out.println("Excel data initialized: " + excelIterator_dt);
    }

    @When("user enter valid credentials and click on login page")
    public void user_enter_valid_credentials_and_click_on_login_page() {
        System.out.println(excelIterator_dt);
       loginPage.login(excelIterator_dt.get(TC_Name).get("user_id"),excelIterator_dt.get(TC_Name).get("password"));
        System.out.println("User id: " +excelIterator_dt.get(TC_Name).get("user_id"));
        System.out.println("Password: " +excelIterator_dt.get(TC_Name).get("password"));
    }

    @Then("user should be navigated to home page")
    public void user_should_be_navigated_to_home_page() {
        leadPage.validateLogout();
    }

    @When("user enter invalid credentials and click on login page")
    public void user_enter_invalid_credentials_and_click_on_login_page() {
        loginPage.login("admin123","admin123");
    }

    @Then("user should be navigated to login page")
    public void user_should_be_navigated_to_login_page() {
        loginPage.validateUserId();
    }

    @Then("user can see the error message")
    public void user_can_see_the_error_message() {
        loginPage.validateErrorMsg();
    }

    @And("close the browser")
    public void close_the_browser() {
        driver.quit();
    }

    @When("user enters user_id as {string} and password as {string} and click on login page")
    public void user_enters_user_id_as_and_password_as_and_click_on_login_page(String userid, String password) {
//        driver.findElement(By.name("user_name")).sendKeys(userid);
//        driver.findElement(By.name("user_password")).sendKeys(password);
//        driver.findElement(By.name("Login")).click();
        loginPage.login(userid,password);
    }
}
