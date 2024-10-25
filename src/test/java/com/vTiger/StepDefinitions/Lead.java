package com.vTiger.StepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;

public class Lead extends BaseDefinitions {
    @Given("user should be on lead creation form")
    public void user_should_be_on_lead_creation_form() {
//        driver.findElement(By.linkText("Leads")).click();
//        System.out.println("Leads clicked");
        driver.findElement(By.linkText("New Lead")).click();
        System.out.println("New Lead clicked");
    }
    @When("user enters all the mandatory fields and click on save button")
    public void user_enters_all_the_mandatory_fields_and_click_on_save_button() {
        driver.findElement(By.name("lastname")).sendKeys("Modi");
//        driver.findElement(By.name("company")).sendKeys("BJP");
//        driver.findElement(By.name("button")).click();
        System.out.println(TC_Name);
        System.out.println(excelIterator_dt);
        System.out.println(excelIterator_dt.get(TC_Name).get("lastname"));
        System.out.println(excelIterator_dt.get(TC_Name).get("company"));
        System.out.println(excelIterator_dt);
        leadPage.createLeadWithMandatoryParameter(excelIterator_dt.get(TC_Name).get("lastname"), excelIterator_dt.get(TC_Name).get("company"));

    }
    @Then("lead should be created successfully")
    public void lead_should_be_created_successfully() {
        driver.findElement(By.xpath("//td[text()='Last Name:']/following::td[1]")).getText().equals("Modi");
        driver.findElement(By.xpath("//td[text()='Company:']/following::td[1]")).getText().equals("BJP");
    }
}
