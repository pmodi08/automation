package testCases;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.ReadConfig;


public class BaseClass {

    ReadConfig rc=new ReadConfig();

    public String baseURL=rc.getApplicationURL();
    public String username=rc.getUsername();
    public String password=rc.getPassword();
    public static WebDriver driver;



    @BeforeClass
    public void setup() {

        System.setProperty("webDriver.chrome.driver", rc.getChromePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();



    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }


}
