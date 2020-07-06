package utils;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Initializer {

        private static Initializer SINGLE_INSTANCE = null;
    private WebDriver driver;
    private final int viewPortWidth = 800;
    private final int viewPortHeight = 600;
    String myEyesServer = "https://eyes.applitools.com/"; //set to your server/cloud URL
    String appName = "EKB Example : VG app";
    String batchName = "EKB Example : VG";
    private String apiKey = "KILYZ0DJADVRpML1m40Vc8R6h8JGJDw98ox3mB3lQmI8110";
    private EyesRunner runner;
    private Configuration suiteConfig;
    private Eyes eyes;

    /*
    * Private constructor to avoid creating multiple instances of this class
    */
    private Initializer() {
        suiteConfig = new Configuration();
        runner = new VisualGridRunner(6);
        eyes = new Eyes(runner);
    }

    public static Initializer getInstance() {
        if (SINGLE_INSTANCE == null) {
            synchronized (Initializer.class) {
                SINGLE_INSTANCE = new Initializer();
            }
        }
        return SINGLE_INSTANCE;
    }

    public WebDriver initializeBrowser() {
        System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public String getBaseUrl(String version) {
        switch (version) {
            case "V1":
                return "https://demo.applitools.com/gridHackathonV1.html";
            case "V2":
                return "https://demo.applitools.com/gridHackathonV2.html";
            default:
                return "https://demo.applitools.com/gridHackathonV1.html";
        }
    }

    public Eyes getEyes() {
        return eyes;
    }
    public Configuration getEyesSuiteConfiguration() {

        suiteConfig
                .addBrowser(900, 600, BrowserType.CHROME)
                .addBrowser(1024, 786, BrowserType.FIREFOX)
                .addBrowser(900, 600, BrowserType.FIREFOX)
                .addBrowser(900, 600, BrowserType.IE_10)
                .addBrowser(1024, 786, BrowserType.IE_11)
                .addBrowser(900, 600, BrowserType.EDGE_CHROMIUM_ONE_VERSION_BACK)
                .addDeviceEmulation(DeviceName.iPhone_4, ScreenOrientation.PORTRAIT)
                .addDeviceEmulation(DeviceName.Galaxy_S5, ScreenOrientation.LANDSCAPE)
                .setViewportSize( new RectangleSize(viewPortWidth, viewPortHeight))
                .setApiKey(apiKey)
                .setServerUrl(myEyesServer)
                .setAppName(appName)
                .setBatch(new BatchInfo(batchName));
        return suiteConfig;
    }
}
