package com.vTiger.StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.vTiger.Pages.LeadPage;
import com.vTiger.Pages.LoginPage;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.DatabindException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class BaseDefinitions {

    public static Map<String, Map<String, String>> excelIterator_dt;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;

    public static WebDriver driver;
    public static LoginPage loginPage;
    public static LeadPage leadPage;
    public static Properties prop;
    public static String TC_Name;
    public static ExtentTest extentTestLogger;

    public void initiation() throws IOException, FilloException {
        createExtentReport();
        readProperties();
        readExcelData(System.getProperty("user.dir")+"/src/test/resources/TestData/data.xlsx","Sheet1");
    }

    public void launchApplication (){
        if(prop.getProperty("browser").equals("chrome")){
            driver = new ChromeDriver();
        }
        else if (prop.getProperty("browser").equals("firefox")){
            driver = new FirefoxDriver();
        }
        else {
            driver = new EdgeDriver();
        }
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        loginPage = new LoginPage(driver,extentTestLogger);
        leadPage = new LeadPage(driver,extentTestLogger);
    }
    public void readProperties() throws IOException {
         prop = new Properties();

        FileInputStream fis  = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Configuration/config.properties");
        prop.load(fis);
    }
    public void readExcelData(String workbook, String excelSheet) throws FilloException {
        Fillo fillo=new Fillo();
        Connection connection=fillo.getConnection(workbook);
        String strQuery="Select * from "+excelSheet;
        Recordset recordset=connection.executeQuery(strQuery);
        List<String> list = recordset.getFieldNames();
        int columnCount =list.size();
        excelIterator_dt = new HashMap<>();

        while(recordset.next()){
            Map<String,String> rowData = new HashMap<>();
            for (int i = 1; i < columnCount; i++)
            {
                String columnName = list.get(i);
                String columnData = recordset.getField(columnName);
                rowData.put(columnName,columnData);
            }
            excelIterator_dt.put(recordset.getField("TC_Name"),rowData);
//            System.out.println(recordset.getField("Details"));
        }
        System.out.println("Line 79:");
        System.out.println(excelIterator_dt);
        recordset.close();
        connection.close();
    }
    public void createExtentReport(){
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
            String filename= dateFormat.format(date);
            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/src/test/java/com/vTiger/Reports/ExtentReports_"+ filename +"_.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(htmlReporter);
            extentReports.setSystemInfo("Host Name","Automation Test Hub");
            extentReports.setSystemInfo("Environment","Test");
            extentReports.setSystemInfo("User Name","Ansh Panchal");
            //Name of the report
            htmlReporter.config().setReportName("Name of the report comes here ");
            // Report theme
            htmlReporter.config().setTheme(Theme.STANDARD);
    }
}
