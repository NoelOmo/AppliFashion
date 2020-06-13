package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {

    public WebDriver webDriver;

    @FindBy(className = "grid_item")
    private List<WebElement> dskProducts;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void scrollToView(WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public WebElement getFirstProductGridElement() {
        return dskProducts.get(0);
    }

    public void navigateToProductDetails() {
        getFirstProductGridElement().click();
    }

    public Boolean isPriceStrikedThrough(WebElement element) {
        return  element.getCssValue("text-decoration").contains("line-through");
    }

}
