package pages;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Initializer;
import utils.UFGEyes;

public class ProductDetailsPageTest extends BaseTest {

    private ProductDetailsPage mProductDetailsPage;

    @BeforeClass(alwaysRun = true)
    public void setupClass(){
        mProductDetailsPage = new ProductDetailsPage(webDriver);
        webDriver.get(Initializer.getInstance().getBaseUrl("V1") + "?id=1");
        browserOperations.waitForPageToLoad();
        eyes.open(webDriver, "AppliFashion App V1", "Product details page test");
    }

    @Test(groups = {"desktop","tablet","mobile"})
    @UFGEyes(checkBefore = true, testName = "Ensure that Product details is correctly styled")
    public void test_VerifyThatProductDetailsIsCorrectlyStyled() {
        //        No steps required as this is a 100% UFG dependent test
    }
}
