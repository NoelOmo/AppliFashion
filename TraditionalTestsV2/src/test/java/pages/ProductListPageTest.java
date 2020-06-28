package pages;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Initializer;
import utils.Report;

public class ProductListPageTest extends BaseTest {

    private ProductPage mProductPage;

    @BeforeClass(groups = {"desktop","tablet","mobile"})
    public void setupClass(){
        mProductPage = new ProductPage(webDriver);
        webDriver.get(Initializer.getInstance().getBaseUrl("V1"));
    }

    @Test(groups = {"desktop"})
    @Report(task = 1, domId = "DIV__mainmenu__15")
    public void test_MainMenuIsDisplayedOnDesktop() {
        Assert.assertTrue(mProductPage.isDesktopMenuVisible());
    }

    @Test(groups = {"desktop"})
    @Report(task = 1, domId = "filter_col")
    public void test_FilterMenuIsDisplayedOnDesktop() {
        Assert.assertTrue(mProductPage.isFilterColumnVisible());
    }

    @Test(groups = {"desktop"})
    @Report(task = 2, domId = "LI____103|filterBtn|product_grid")
    public void test_filterForBlackShoes() {
        mProductPage.filterForBlackShoes();
        Assert.assertTrue(mProductPage.areTwoBlackShoesDisplayed());
    }

    @Test(groups = {"mobile", "tablet"})
    @Report(task = 2, domId = "LI____205|LI____103|filterBtn|product_grid")
    public void test_filterForBlackShoesOnMobileAndTablets() {
        mProductPage.openFilterMenu();
        mProductPage.filterForBlackShoes();
        Assert.assertTrue(mProductPage.areTwoBlackShoesDisplayed());
    }

    @Test(groups = {"mobile"})
    @Report(task = 1, domId = "A__wishlist__52")
    public void test_WishlistIconIsHiddenOnMobile() {
        Assert.assertFalse(mProductPage.isWishlistIconVisible());
    }

    @Test(groups = {"desktop", "tablet"})
    @Report(task = 1, domId = "INPUTtext____42")
    public void test_SearchBarIsDisplayedOnDesktopAndTablet() {
        Assert.assertTrue(mProductPage.isDesktopSearchBarVisible()
                && !mProductPage.isMobileSearchBarVisible());
    }

    @Test(groups = {"mobile", "tablet"})
    @Report(task = 1, domId = "DIV__mainmenu__15")
    public void test_MainMenuIsNotDisplayedOnMobileAndTablets() {
        Assert.assertFalse(mProductPage.isDesktopMenuVisible());
    }

    @Test(groups = {"tablet","mobile"})
    @Report(task = 1, domId = "LI____201")
    public void test_ChangeViewButtonsAreNotVisibleOnTabletAndMobile() {
        Assert.assertFalse(mProductPage.areChangeViewButtonsVisible());
    }

    @Test(groups = {"desktop","tablet","mobile"})
    @Report(task = 1, domId = "grid_item")
    public void test_TooltipDisplayedOnHover() {
        WebElement element = mProductPage.getFirstProductGridElement();
        mProductPage.performHover(element);
        Assert.assertTrue(mProductPage.isTooltipVisible(element));
    }

    @Test(groups = {"desktop","tablet","mobile"})
    @Report(task = 1, domId = "old_price")
    public void test_SalePriceStrikeThrough() {
        WebElement element = mProductPage.getFirstSaleElement();
        Assert.assertTrue(mProductPage.isPriceStrikedThrough(element));
    }
}
