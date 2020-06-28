package pages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Initializer;
import utils.Report;

public class ProductDetailsPageTest extends BaseTest {

    private ProductDetailsPage mProductDetailsPage;

    @BeforeClass(groups = {"desktop","tablet","mobile"})
    public void setupClass(){
        mProductDetailsPage = new ProductDetailsPage(webDriver);
        webDriver.get(Initializer.getInstance().getBaseUrl("V2"));
        mProductDetailsPage.navigateToProductDetails();
        browserOperations.waitForPageToLoad();
    }

    @Test(groups = {"desktop","tablet","mobile"})
    @Report(task = 3, domId = "old_price")
    public void test_VerifySalePriceIsProperlyStyled() {
        Assert.assertTrue(mProductDetailsPage.isSalePriceStyled());
    }

    @Test(groups = {"desktop","tablet","mobile"})
    @Report(task = 3, domId = "nice-select")
    public void test_VerifyDefaultSizeIsSmall() {
        Assert.assertEquals("Small (S)", mProductDetailsPage.getCurrentSize());
    }

    @Test(groups = {"desktop","tablet","mobile"})
    @Report(task = 3, domId = "SMALL____84")
    public void test_VerifyProductSKUIsDisplayed() {
        Assert.assertTrue(mProductDetailsPage.isProductSKUDisplayed());
    }
}
