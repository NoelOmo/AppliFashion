package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Initializer {

    private static Initializer SINGLE_INSTANCE = null;
    private WebDriver driver;

    /*
    * Private constructor to avoid creating multiple instances of this class
    */
    private Initializer() {}

    public static Initializer getInstance() {
        if (SINGLE_INSTANCE == null) {
            synchronized (Initializer.class) {
                SINGLE_INSTANCE = new Initializer();
            }
        }
        return SINGLE_INSTANCE;
    }

    public WebDriver initializeBrowser(String browser) {
        switch (browser){
            case Constants.CHROME_BROWSER:
                System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case Constants.FIREFOX_BROWSER:
                System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
        }
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
}
