package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductDetailsPage extends BasePage {

    @FindBy(id = "old_price")
    private WebElement salePrice;

    public ProductDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public Boolean isSalePriceStyled() {
        System.out.println(salePrice.getCssValue("color"));
        return  salePrice.getCssValue("text-decoration").contains("line-through");
    }

}