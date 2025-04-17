package DemoWebShop.Test;

import DemoWebShop.Core.TestBase;
import DemoWebShop.model.User;
import DemoWebShop.utils.MyDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    //! работает разово: контактные данные из Csv зарегистрированы после первого запуска теста
    @Test(dataProvider = "addNewAccountFromCsv",dataProviderClass = MyDataProvider.class)
    public void regisrationTestwithCsv(User user){

        registrateAccountWithUser(user);

        WebElement element = driver.findElement(By.xpath("//a[@class = 'ico-logout']")); //в конце теста log out
        Assert.assertEquals( element.getText(), "Log out" );

    }
}
