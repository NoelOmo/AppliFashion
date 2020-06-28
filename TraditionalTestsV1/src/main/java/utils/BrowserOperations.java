package utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserOperations {

    private WebDriver webDriver;

    public BrowserOperations(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void resizeBrowser(int width, int height) {
        Dimension d = new Dimension(scrollBarPurge(width),height);
        webDriver.manage().window().setSize(d);
    }

    /*
    * Fix to remove scrollbar from width as it is causing unexpected results
    * This fix also helps since selenium is resizing the browser width so the viewport width is less than actual width
    */
    private int scrollBarPurge(int size) {
        return size + 16;
    }

    public void waitForPageToLoad() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println("Timeout waiting for Page Load Request to complete.");
        }
    }

}
