package DemoWebShop.Test;

import DemoWebShop.Core.TestBase;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class RegistrationTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(RegistrationTest.class);
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registrationPositiveTest(){
        String emailGenerator = System.currentTimeMillis()+"@email.com";
        registrateAccount(emailGenerator);

        try {
            Assert.assertTrue(isElementPresent(By.xpath("//a[contains(text(),'Log out')]")));
            logger.info("PASSED");
        } catch (Exception e) {
            logger.error("FAILED, Screenshot: " + takeScreenshot());;
        }
    }

    @Test
    public void registrationNegativeTest(){
        String emailGenerator = System.currentTimeMillis()+"email.com";
        registrateAccount(emailGenerator);

        boolean elementPresent = isElementPresent(By.xpath("//a[contains(text(),'Log out')]"));
        softAssert.assertTrue(elementPresent);
        if (elementPresent) {
            System.out.println("1");
            logger.info("PASSED");
        }else {
            System.out.println("2");
            logger.error("FAILED, Screenshot: " + takeScreenshot());
            softAssert.assertAll();
            driver.quit();
        }
    }
}
