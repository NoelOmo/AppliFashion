package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {

    private Actions action = new Actions(webDriver);

    //     Desktop page objects
    @FindBy(id = "INPUTtext____42")
    private WebElement dskSearchField;

    @FindBy(id = "product_grid")
    private WebElement dskProductGrid;

    @FindBy(className = "old_price")
    private List<WebElement> dskOldPrices;

    @FindBy(id = "DIV__mainmenu__15")
    private WebElement dskMainMenu;

    @FindBy(id = "filter_col")
    private WebElement dskFilterColumn;

    //    Mobile page objects
    @FindBy(id = "INPUTtext__formcontro__62")
    private WebElement mblSearchField;


    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    public Boolean isDesktopSearchBarVisible() {
        return dskSearchField.isDisplayed() && dskSearchField.isEnabled();
    }

    public Boolean isMobileSearchBarVisible() {
        return mblSearchField.isEnabled() && mblSearchField.isDisplayed();
    }

    public Boolean isDesktopMenuVisible() {
        return  dskMainMenu.isEnabled() && dskMainMenu.isDisplayed();
    }

    public Boolean isFilterColumnVisible() {
        return dskFilterColumn.isEnabled() && dskFilterColumn.isDisplayed();
    }

    public Boolean isTooltipVisible(WebElement element) {
        WebElement el = element.findElement(By.tagName("ul"));
        return el.isDisplayed() && el.isEnabled();
    }

    public void performHover(WebElement element) {
        scrollToView(element);
        action.moveToElement(element).build().perform();
    }

    public WebElement getFirstSaleElement() {
        return dskOldPrices.get(0);
    }


}
