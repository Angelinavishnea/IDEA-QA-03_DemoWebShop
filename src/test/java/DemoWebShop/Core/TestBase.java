package DemoWebShop.Core;

import DemoWebShop.model.User;
import com.google.common.io.Files;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestBase {
    protected WebDriver driver;
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //* basic methods

    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    protected boolean isClickLoginPresent() {
        return isElementPresent(By.xpath("//a[@class = 'ico-login']"));
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public String takeScreenshot() {
        // временный файл
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // постоянный файл
        File screen = new File("screenshots/screen_" + System.currentTimeMillis() + ".png");

        try {
            Files.copy(tmp, screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return screen.getAbsolutePath();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //* methods with strings

    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void clickLoginAccount() {
        click(By.xpath("//input[@value='Log in']"));
    }

    protected void returningCustomer(String Email, String Password) {
        click(By.id("Email"));
        driver.findElement(By.id("Email")).clear();
        type(By.id("Email"), Email);
        click(By.cssSelector("#Password"));
        driver.findElement(By.cssSelector("#Password")).clear();
        type(By.cssSelector("#Password"), Password);
    }

    protected void clickLogin() {
        click(By.xpath("//a[@class = 'ico-login']"));
    }

    protected void clickLogOut() {
        click(By.xpath("//a[contains(text(),'Log out')]"));
    }

    protected void clickContinue() {
        click(By.xpath("//input[@value='Continue']"));
    }

    protected void clickStartRegister() {
        click(By.xpath("//input[contains(@class, 'button-1') and @value = 'Register'] "));
    }

    protected void clickFinishRegister() {
        click(By.xpath("//input[@id='register-button']"));
    }

    protected void personalDetails(String firstName, String lastName, String email, String Password, String ConfirmPassword) {
        click(By.xpath("//input[@id='gender-female']"));

        click(By.xpath("//input[@id='FirstName']"));
        driver.findElement(By.xpath("//input[@id='FirstName']")).clear();
        type(By.xpath("//input[@id='FirstName']"), firstName);

        click(By.xpath("//input[@id='LastName']"));
        driver.findElement(By.xpath("//input[@id='LastName']")).clear();
        type(By.xpath("//input[@id='LastName']"), lastName);

        click(By.xpath("//input[@id='Email']"));
        driver.findElement(By.xpath("//input[@id='Email']")).clear();
        type(By.xpath("//input[@id='Email']"), email);

        click(By.xpath("//input[@id='Password']"));
        driver.findElement(By.xpath("//input[@id='Password']")).clear();
        type(By.xpath("//input[@id='Password']"), Password);

        click(By.xpath("//input[@id='ConfirmPassword']"));
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).clear();
        type(By.xpath("//input[@id='ConfirmPassword']"), ConfirmPassword);

    }

    protected void registrateAccount(String emailGenerator) {
        clickLogin();
        clickStartRegister();
        personalDetails("Anna", "Mustermann", emailGenerator, "Password1", "Password1");
        clickFinishRegister();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //* methods with user

    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void returningCustomerUserMethod(User user) {
        click(By.id("Email"));
        driver.findElement(By.id("Email")).clear();
        type(By.id("Email"), user.getEmail());
        click(By.cssSelector("#Password"));
        driver.findElement(By.cssSelector("#Password")).clear();
        type(By.cssSelector("#Password"), user.getPassword());

    }

    protected void registrateAccountWithUser(User user) {
        clickLogin();
        clickStartRegister();
        personalDetailsWithUser(user);
        clickFinishRegister();
    }

    protected void personalDetailsWithUser(User user) {
        click(By.xpath("//input[@id='gender-female']"));

        click(By.xpath("//input[@id='FirstName']"));
        driver.findElement(By.xpath("//input[@id='FirstName']")).clear();
        type(By.xpath("//input[@id='FirstName']"), user.getName());

        click(By.xpath("//input[@id='LastName']"));
        driver.findElement(By.xpath("//input[@id='LastName']")).clear();
        type(By.xpath("//input[@id='LastName']"), user.getLastName());

        click(By.xpath("//input[@id='Email']"));
        driver.findElement(By.xpath("//input[@id='Email']")).clear();
        type(By.xpath("//input[@id='Email']"), user.getEmail());

        click(By.xpath("//input[@id='Password']"));
        driver.findElement(By.xpath("//input[@id='Password']")).clear();
        type(By.xpath("//input[@id='Password']"), user.getPassword());

        click(By.xpath("//input[@id='ConfirmPassword']"));
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).clear();
        type(By.xpath("//input[@id='ConfirmPassword']"), user.getPassword());

    }

}