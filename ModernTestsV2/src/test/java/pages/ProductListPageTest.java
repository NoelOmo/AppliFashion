package pages;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Initializer;
import utils.UFGEyes;

public class ProductListPageTest extends BaseTest {

    private ProductPage mProductPage;

    @BeforeClass(alwaysRun = true)
    public void setupClass(){
        mProductPage = new ProductPage(webDriver);
        webDriver.get(Initializer.getInstance().getBaseUrl("V2"));
        eyes.open(webDriver, "AppliFashion App V2", "Product list page test");
    }


    @Test(groups = {"desktop"})
    @UFGEyes(checkAfter = true)
    public void test_filterForBlackShoes() {
        mProductPage.filterForBlackShoes();
        Assert.assertTrue(mProductPage.areTwoBlackShoesDisplayed());
    }

    @Test(groups = {"desktop","tablet","mobile"})
    @UFGEyes(checkBefore = true, testName = "Ensure that Product list page is correctly styled")
    public void test_VerifyThatProductListIsCorrectlyStyled() {
//        No steps required as this is a 100% UFG dependent test
    }

    @Test(groups = {"tablet","mobile"})
    @UFGEyes(checkAfter = true, testName = "Ensure that users can filter for black shoes")
    public void test_filterForBlackShoesOnMobileAndTablets() {
        mProductPage.openFilterMenu();
        mProductPage.filterForBlackShoes();
        Assert.assertTrue(mProductPage.areTwoBlackShoesDisplayed());
    }

    @Test(groups = {"desktop","tablet","mobile"})
    @UFGEyes(checkAfter = true, testName = "Ensure that the tooltip is displayed on hover")
    public void test_TooltipDisplayedOnHover() {
        WebElement element = mProductPage.getFirstProductGridElement();
        mProductPage.performHover(element);
        Assert.assertTrue(mProductPage.isTooltipVisible(element));
    }

}
