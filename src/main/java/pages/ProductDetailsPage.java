package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductDetailsPage extends BasePage {

    @FindBy(id = "old_price")
    private WebElement salePrice;

    @FindBy(className = "nice-select")
    private WebElement sizeDropDown;

    @FindBy(id = "SMALL____84")
    private WebElement productSku;

    public ProductDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public Boolean isSalePriceStyled() {
        System.out.println(salePrice.getCssValue("color"));
        return  salePrice.getCssValue("text-decoration").contains("line-through");
    }

    public String getCurrentSize() {
        return sizeDropDown.findElement(By.className("current")).getText();
    }

    public Boolean isProductSKUDisplayed() {
        try {
            return productSku.isDisplayed();
        }catch (NoSuchElementException ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

}