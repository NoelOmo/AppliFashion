package pages;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductListPageTest extends BaseTest {

    private ProductPage mProductPage;

    @BeforeClass(groups = {"desktop","tablet","mobile"})
    public void setupClass(){
        mProductPage = new ProductPage(webDriver);
        webDriver.get("https://demo.applitools.com/gridHackathonV1.html");
    }

    @Test(groups = {"desktop", "tablet"})
    public void test_SearchBarIsDisplayedOnDesktopAndTablet() {
        Assert.assertTrue(mProductPage.isDesktopSearchBarVisible()
                && !mProductPage.isMobileSearchBarVisible());
    }

    @Test(groups = {"desktop"})
    public void test_MainMenuIsDisplayedOnDesktop() {
        Assert.assertTrue(mProductPage.isDesktopMenuVisible());
    }

    @Test(groups = {"desktop"})
    public void test_FilterMenuIsDisplayedOnDesktop() {
        Assert.assertTrue(mProductPage.isFilterColumnVisible());
    }

    @Test(groups = {"desktop","tablet","mobile"})
    public void test_TooltipDisplayedOnHover() {
        WebElement element = mProductPage.getFirstProductGridElement();
        mProductPage.performHover(element);
        Assert.assertTrue(mProductPage.isTooltipVisible(element));
    }

    @Test(groups = {"desktop","tablet","mobile"})
    public void test_SalePriceStrikeThrough() {
        WebElement element = mProductPage.getFirstSaleElement();
        Assert.assertTrue(mProductPage.isPriceStrikedThrough(element));
    }
}