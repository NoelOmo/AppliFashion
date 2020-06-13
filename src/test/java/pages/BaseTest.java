package pages;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.BrowserOperations;
import utils.Initializer;

public class BaseTest {

    public WebDriver webDriver;
    public BrowserOperations browserOperations;

    @BeforeClass(groups = {"desktop","tablet","mobile"})
    @Parameters({"browser", "width", "height"})
    public void testSetup(String browser, String width, String height) {
        webDriver = Initializer.getInstance().initializeBrowser(browser);
        browserOperations = new BrowserOperations(webDriver);
        browserOperations.resizeBrowser(Integer.parseInt(width), Integer.parseInt(height));
    }

    @AfterClass(groups = {"desktop","tablet","mobile"})
    public void tearDown() {
        webDriver.quit();
    }
}
