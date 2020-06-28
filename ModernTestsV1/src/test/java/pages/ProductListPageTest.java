package pages;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Initializer;
import utils.Report;
import utils.UFGEyes;

public class ProductListPageTest extends BaseTest {

    private ProductPage mProductPage;

    @BeforeClass
    public void setupClass(){
        mProductPage = new ProductPage(webDriver);
        webDriver.get(Initializer.getInstance().getBaseUrl("V1"));
    }

    @Test
    @Report(task = 1, domId = "DIV__mainmenu__15")
    @UFGEyes(checkBefore = true)
    public void test_MainMenuIsDisplayedOnDesktop() {
        Assert.assertTrue(mProductPage.isDesktopMenuVisible());
    }

    @Test
    @Report(task = 1, domId = "filter_col")
    @UFGEyes(checkBefore = true)
    public void test_FilterMenuIsDisplayedOnDesktop() {
        Assert.assertTrue(mProductPage.isFilterColumnVisible());
    }

    @Test
    @Report(task = 2, domId = "LI____103|filterBtn|product_grid")
    @UFGEyes(checkAfter = true)
    public void test_filterForBlackShoes() {
        mProductPage.filterForBlackShoes();
        Assert.assertTrue(mProductPage.areTwoBlackShoesDisplayed());
    }

    @Test
    @Report(task = 2, domId = "LI____205|LI____103|filterBtn|product_grid")
    @UFGEyes(checkAfter = true)
    public void test_filterForBlackShoesOnMobileAndTablets() {
        mProductPage.openFilterMenu();
        mProductPage.filterForBlackShoes();
        Assert.assertTrue(mProductPage.areTwoBlackShoesDisplayed());
    }

    @Test
    @Report(task = 1, domId = "A__wishlist__52")
    @UFGEyes(checkBefore = true)
    public void test_WishlistIconIsHiddenOnMobile() {
        Assert.assertFalse(mProductPage.isWishlistIconVisible());
    }

    @Test
    @Report(task = 1, domId = "INPUTtext____42")
    @UFGEyes(checkBefore = true)
    public void test_SearchBarIsDisplayedOnDesktopAndTablet() {
        Assert.assertTrue(mProductPage.isDesktopSearchBarVisible()
                && !mProductPage.isMobileSearchBarVisible());
    }

    @Test
    @Report(task = 1, domId = "DIV__mainmenu__15")
    @UFGEyes(checkBefore = true)
    public void test_MainMenuIsNotDisplayedOnMobileAndTablets() {
        Assert.assertFalse(mProductPage.isDesktopMenuVisible());
    }

    @Test
    @Report(task = 1, domId = "LI____201")
    @UFGEyes(checkBefore = true)
    public void test_ChangeViewButtonsAreNotVisibleOnTabletAndMobile() {
        Assert.assertFalse(mProductPage.areChangeViewButtonsVisible());
    }

    @Test
    @Report(task = 1, domId = "grid_item")
    @UFGEyes(checkBefore = true)
    public void test_TooltipDisplayedOnHover() {
        WebElement element = mProductPage.getFirstProductGridElement();
        mProductPage.performHover(element);
        Assert.assertTrue(mProductPage.isTooltipVisible(element));
    }

    @Test
    @Report(task = 1, domId = "old_price")
    @UFGEyes(checkBefore = true)
    public void test_SalePriceStrikeThrough() {
        WebElement element = mProductPage.getFirstSaleElement();
        Assert.assertTrue(mProductPage.isPriceStrikedThrough(element));
    }
}
