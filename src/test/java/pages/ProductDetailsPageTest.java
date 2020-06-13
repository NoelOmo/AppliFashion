package pages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Initializer;

public class ProductDetailsPageTest extends BaseTest {

    private ProductDetailsPage mProductDetailsPage;

    @BeforeClass(groups = {"desktop","tablet","mobile"})
    public void setupClass(){
        mProductDetailsPage = new ProductDetailsPage(webDriver);
        webDriver.get(Initializer.getInstance().getBaseUrl("V1"));
        mProductDetailsPage.navigateToProductDetails();
        browserOperations.waitForPageToLoad();
    }

    @Test(groups = {"desktop","tablet","mobile"})
    public void test_VerifySalePriceIsProperlyStyled() {
        Assert.assertTrue(mProductDetailsPage.isSalePriceStyled());
    }

    @Test(groups = {"desktop","tablet","mobile"})
    public void test_VerifyDefaultSizeIsSmall() {
        Assert.assertEquals("Small (S)", mProductDetailsPage.getCurrentSize());
    }

    @Test(groups = {"desktop","tablet","mobile"})
    public void test_VerifyProductSKUIsDisplayed() {
        Assert.assertTrue(mProductDetailsPage.isProductSKUDisplayed());
    }
}
