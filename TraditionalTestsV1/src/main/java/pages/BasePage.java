package pages;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class BasePage {

    public WebDriver webDriver;

    public WebDriverWait wait;

    @FindBy(className = "grid_item")
    public List<WebElement> dskProducts;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 30);
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

    public JSONArray getProductList() {
        String productList = ((JavascriptExecutor) webDriver)
                .executeScript("return JSON.stringify(products)")
                .toString();
        return new JSONArray(productList);
    }

    public ArrayList<String> getFilteredProductNamesByColor(String color) {
        JSONArray products = getProductList();
        ArrayList<String> productNames = new ArrayList<>();
        for (int i = 0; i < products.length(); i++){
            JSONObject product = (JSONObject) products.get(i);
            if (product.get("colors").toString().toLowerCase().contains(color.toLowerCase())){
                productNames.add(product.get("name").toString());
            }
        }
        return productNames;
    }

}
