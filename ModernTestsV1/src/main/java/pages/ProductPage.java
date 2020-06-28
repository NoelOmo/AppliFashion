package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
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

    @FindBy(id = "LI____103")
    private WebElement blackColorFilter;

    @FindBy(id = "filterBtn")
    private WebElement filterButton;

    //    Mobile page objects
    @FindBy(id = "INPUTtext__formcontro__62")
    private WebElement mblSearchField;

    @FindBy(id = "A__wishlist__52")
    private WebElement mblWishlistIcon;

    @FindBy(id = "LI____201")
    private WebElement mblChangeViewButtons;

    @FindBy(id = "LI____205")
    private WebElement openFilter;


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

    public Boolean isWishlistIconVisible() {
        try {
            return mblWishlistIcon.isDisplayed() && mblWishlistIcon.isEnabled();
        }catch (NoSuchElementException ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public Boolean areChangeViewButtonsVisible() {
        try {
            return mblChangeViewButtons.isDisplayed() && mblChangeViewButtons.isEnabled();
        }catch (NoSuchElementException ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public Boolean isTooltipVisible(WebElement element) {
        WebElement el = element.findElement(By.tagName("ul"));
        return el.isDisplayed() && el.isEnabled();
    }

    public Boolean areTwoBlackShoesDisplayed(){
        ArrayList<String> filteredProducts = getFilteredProductNamesByColor("black");
        for (WebElement product : dskProducts) {
            String productTitle = product.findElement(By.tagName("h3")).getText();
            if (!filteredProducts.contains(productTitle))
                return false;
        }
        return true;
    }

    public void filterForBlackShoes() {
        blackColorFilter.click();
        filterButton.click();
    }

    public void openFilterMenu() {
        openFilter.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filter_col")));
    }
    public void performHover(WebElement element) {
        scrollToView(element);
        action.moveToElement(element).build().perform();
    }

    public WebElement getFirstSaleElement() {
        return dskOldPrices.get(0);
    }


}
