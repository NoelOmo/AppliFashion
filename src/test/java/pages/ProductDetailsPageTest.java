package pages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductDetailsPageTest extends BaseTest {

    private ProductDetailsPage mProductDetailsPage;

    @BeforeClass(groups = {"desktop","tablet","mobile"})
    public void setupClass(){
        mProductDetailsPage = new ProductDetailsPage(webDriver);
        webDriver.get("https://demo.applitools.com/gridHackathonV1.html");
        mProductDetailsPage.navigateToProductDetails();
        browserOperations.waitForPageToLoad();
    }

    @Test(groups = {"desktop","tablet","mobile"})
    public void test_VerifySalePriceIsProperlyStyled() {
        Assert.assertTrue(mProductDetailsPage.isSalePriceStyled());
    }
}
