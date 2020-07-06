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
    private String type;

    @Parameters({"type"})
    @BeforeClass(alwaysRun = true)
    public void testSetup(String type) {
        this.type = type;
        eyes = initializer.getEyes();
        setUp(eyes, type);
        webDriver = initializer.initializeBrowser();
        browserOperations = new BrowserOperations(webDriver);
        browserOperations.resizeBrowser(type);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestContext ctx,Method method) throws NoSuchMethodException {
        Method m = this.getClass().getMethod(method.getName());
        if (m.isAnnotationPresent(UFGEyes.class)) {
            UFGEyes ufgEyes = m.getAnnotation(UFGEyes.class);
            ctx.setAttribute("doCheckAfterTest", ufgEyes.checkAfter());
            ctx.setAttribute("testTitle", ufgEyes.testName());
            boolean doCheckBeforeTest = ufgEyes.checkBefore();
            if (doCheckBeforeTest) {
                performUfgEyeCheck(ufgEyes.testName());
            }
        }
    }

    private void performUfgEyeCheck(String testName) {
        eyes.check(Target.window().fully().withName(testName));
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result, ITestContext ctx) {
        if ((boolean)ctx.getAttribute("doCheckAfterTest")) {
            performUfgEyeCheck((String)ctx.getAttribute("testTitle"));
        }
    }

    @AfterClass(alwaysRun = true, timeOut = 60000)
    public void tearDown() {
        webDriver.quit();
        eyes.close();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
    }

    public static void setUp(Eyes eyes, String type) {
        Configuration config = new Configuration();
        config.setApiKey(Constants.APPLITOOLS_API_KEY);
        config.setBatch(new BatchInfo("AppliFashion Modern Tests V2"));
        eyes.setConfiguration(setConfigs(type, config));

    }

    private static Configuration setConfigs(String type, Configuration config) {
        switch (type) {
            case "Desktop":
                config.addBrowser(1200, 700, BrowserType.CHROME);
                config.addBrowser(1200, 700, BrowserType.FIREFOX);
                config.addBrowser(1200, 700, BrowserType.EDGE_CHROMIUM);
                break;
            case "Tablet" :
                config.addBrowser(768, 700, BrowserType.CHROME);
                config.addBrowser(768, 700, BrowserType.FIREFOX);
                config.addBrowser(768, 700, BrowserType.EDGE_CHROMIUM);
                break;
            case "Mobile":
                config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
                break;
        }
        return config;
    }
}
