package base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.HomePage;
import java.util.List;

public class BaseTests {

    private WebDriver driver;

    /**
     * Protected so tests that extend this class will have access to it
     */
    protected HomePage homePage;
    
    /**
     * Set up method for to be used as a base for other tests
     */
    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();

        // Load browser, visit a URL, use a sample app for automation
        driver.get("https://the-internet.herokuapp.com/");

        homePage = new HomePage(driver);

    }

    /**
     * Tear down method to clean up anything after tests are run
     */
    @AfterClass
    public void tearDown () {
        // Make sure to close the session, it will close the Window
        driver.quit();
    }

    public static void main(String args[]) {
        BaseTests test = new BaseTests();
        test.setUp();
        test.beginnerUnitTest();
    }

    public void beginnerUnitTest() {
        
        /* Changing sizes of window */

        // Example iPhone dimensions
        // driver.manage().window().setSize(new Dimension(375, 812));
        // driver.manage().window().maximize();
        // driver.manage().window().fullscreen();

        // Find all links
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Found " + links.size() + " links.");

        WebElement inputsLink = driver.findElement(By.linkText("Inputs"));
        inputsLink.click();

        System.out.println("=== Visited: " + driver.getTitle() + "===");

        String searchText = "Angellic";
        try {
            inputsLink = driver.findElement(By.linkText(searchText));    
        } catch (NoSuchElementException e) {
            System.err.println("No such link " + searchText + " found." );
        }
    }

}