package pages;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.*;

import java.lang.reflect.Method;

public class BaseTest {

    public WebDriver webDriver;
    public BrowserOperations browserOperations;
    public Initializer initializer = Initializer.getInstance();
    public Eyes eyes;

    @BeforeClass
    @Parameters()
    public void testSetup() {
        eyes = initializer.getEyes();
        setUp(eyes);
        webDriver = initializer.initializeBrowser();
        browserOperations = new BrowserOperations(webDriver);
        eyes.open(webDriver, "AppliFashion App", "Ultrafast grid hackerthon", new RectangleSize(800, 600));
    }

    @BeforeMethod
    public void beforeMethod(ITestContext ctx,Method method) throws NoSuchMethodException {
        Method m = this.getClass().getMethod(method.getName());
        if (m.isAnnotationPresent(Report.class)) {
            Report ta = m.getAnnotation(Report.class);
            ctx.setAttribute("task",ta.task());
            ctx.setAttribute("domId",ta.domId());
            ctx.setAttribute("testName", m.getName());
        }
        if (m.isAnnotationPresent(UFGEyes.class)) {
            UFGEyes ufgEyes = m.getAnnotation(UFGEyes.class);
            ctx.setAttribute("doCheckAfterTest", ufgEyes.checkAfter());
            boolean doCheckBeforeTest = ufgEyes.checkBefore();
            if (doCheckBeforeTest) {
                performUfgEyeCheck(m.getName());
            }
        }
    }

    private void performUfgEyeCheck(String testName) {
        eyes.check(Target.window().fully().withName(testName));
    }

    @AfterMethod(groups = {"desktop","tablet","mobile"})
    public void afterMethod(ITestResult result, ITestContext ctx) {
        boolean status = result.isSuccess();
        Reporter.getInstance().report(
                (int)ctx.getAttribute("task"),
                (String)ctx.getAttribute("testName"),
                (String)ctx.getAttribute("domId"),
                status);
        if ((boolean)ctx.getAttribute("doCheckAfterTest")) {
            performUfgEyeCheck((String)ctx.getAttribute("testName"));
        }
    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }

    @AfterSuite
    public void afterSuite() {
        TestResults t = eyes.close();
    }

    public static void setUp(Eyes eyes) {

        Configuration config = new Configuration();

        config.setApiKey("KILYZ0DJADVRpML1m40Vc8R6h8JGJDw98ox3mB3lQmI8110");

        config.setBatch(new BatchInfo("AppliFashion Hackerthon"));

        config.addBrowser(800, 600, BrowserType.CHROME);
        config.addBrowser(700, 500, BrowserType.FIREFOX);
        config.addBrowser(1600, 1200, BrowserType.IE_11);
        config.addBrowser(1024, 768, BrowserType.EDGE_CHROMIUM);
        config.addBrowser(800, 600, BrowserType.SAFARI);

        config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
        config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);

        eyes.setConfiguration(config);

    }
}
