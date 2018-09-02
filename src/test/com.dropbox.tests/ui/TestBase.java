package ui;

import logger.TALogger;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestBase {
    public WebDriver driver;
    public WebDriverWait wait;
    public Logger Log;

    @Parameters({"browser"})
    @BeforeClass
    public void openBrowser(String browser){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TALogger.class);
        TALogger taLogger = context.getBean(TALogger.class);
        Log = taLogger.getLogger(getClass());

        if(browser.equalsIgnoreCase("chrome")){
            Log.info("Opening the Chrome Browser.");
            driver = new ChromeDriver();
        }
        else if( browser.equalsIgnoreCase("firefox")){
            Log.info("Opening the Firefox Browser.");
            driver = new FirefoxDriver();
        }
        else {
            Log.info("Browser is not specified.");
        }
        //Create a wait. All the TestClasses & PageClasses have to use this wait object.
        wait = new WebDriverWait(driver,15);

        //Maximize Browser Window
        driver.manage().window().maximize();
    }

    @AfterClass
    public void closeBrowser() {
        Log.info("Quiting the Browser.");
        driver.quit();
    }

    @BeforeMethod
    public void openWebApp(){
        Log.info("Opening the Dropbox Web Application Login Page.");
        driver.get("https://www.dropbox.com/login");
    }

}
