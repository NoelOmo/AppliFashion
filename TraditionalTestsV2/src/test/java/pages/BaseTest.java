package pages;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.BrowserOperations;
import utils.Initializer;
import utils.Report;
import utils.Reporter;

import java.lang.reflect.Method;

public class BaseTest {

    public WebDriver webDriver;
    public BrowserOperations browserOperations;

    @BeforeClass(groups = {"desktop","tablet","mobile"})
    @Parameters({"browser", "width", "height", "device"})
    public void testSetup(String browser, String width, String height, String device) {
        System.setProperty("browser", browser);
        System.setProperty("viewport", width + " X " + height);
        System.setProperty("device", device);
        webDriver = Initializer.getInstance().initializeBrowser();
        browserOperations = new BrowserOperations(webDriver);
        browserOperations.resizeBrowser(Integer.parseInt(width), Integer.parseInt(height));
    }

    @BeforeMethod(groups = {"desktop","tablet","mobile"})
    public void beforeMethod(ITestContext ctx,Method method) throws NoSuchMethodException {
        Method m = this.getClass().getMethod(method.getName());
        if (m.isAnnotationPresent(Report.class)) {
            Report ta = m.getAnnotation(Report.class);
            ctx.setAttribute("task",ta.task());
            ctx.setAttribute("domId",ta.domId());
            ctx.setAttribute("testName", m.getName());
        }
    }

    @AfterMethod(groups = {"desktop","tablet","mobile"})
    public void afterMethod(ITestResult result, ITestContext ctx) {
        boolean status = result.isSuccess();
        Reporter.getInstance().report(
                (int)ctx.getAttribute("task"),
                (String)ctx.getAttribute("testName"),
                (String)ctx.getAttribute("domId"),
                status);
    }

    @AfterClass(groups = {"desktop","tablet","mobile"})
    public void tearDown() {
        webDriver.quit();
    }
}
