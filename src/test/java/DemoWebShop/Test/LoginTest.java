package DemoWebShop.Test;

import DemoWebShop.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import DemoWebShop.Core.TestBase;
import org.testng.annotations.Test;

import java.util.logging.ErrorManager;


public class LoginTest extends TestBase {
    /*
    @BeforeMethod
    public void precondition() {
    }
     */
    Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Test
    public void registrationAndLoginTest(){

        clickLogin();
        //Assert.assertTrue(isClickLoginPresent());

        String emailGenerator = System.currentTimeMillis()+"@email.com";
        registrateAccount(emailGenerator);
        clickContinue();
        clickLogOut();

        clickLogin();
        returningCustomer(emailGenerator, "Password1");
        clickLoginAccount();

        WebElement element = driver.findElement(By.xpath("//a[@class = 'ico-logout']")); //в конце теста log out
        Assert.assertEquals( element.getText(), "Log out" );
    }

    @Test
    public void loginTest(){
        User user = new User()
                .setEmail("portishead@gmail.com")
                .setPassword("tuctuctuc")
                ;
        clickLogin();
        returningCustomerUserMethod(user);
        clickLoginAccount();

        WebElement element = driver.findElement(By.xpath("//a[@class = 'ico-logout']")); //в конце теста log out
        Assert.assertEquals( element.getText(), "Log out" );
    }
}
