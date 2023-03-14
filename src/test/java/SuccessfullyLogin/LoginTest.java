package SuccessfullyLogin;

import Pages.Header;
import Pages.LoginPage;
import Pages.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private String homeUrl = "http://training.skillo-bg.com/posts/all";

    @BeforeSuite
    private void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void driverSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(homeUrl);
    }

    @Parameters({"username", "password"})
    @Test
    public void logIn(String username, String password) {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, homeUrl);

        System.out.println("1. Navigate to Login page");
        Header header = new Header(driver);
        header.clickLogin();

        System.out.println("2. Verify that the login url is displayed");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();

        System.out.println("3. Verify that the header is correct");
        String logInHeader = loginPage.getSignInHeader();
        Assert.assertEquals(logInHeader, "Sign in", "Incorrect login header!");

        System.out.println("4. Enter username");
        loginPage.enterUsername(username);

        System.out.println("5. Enter password");
        loginPage.enterPassword(password);

        System.out.println("6. Mark remember me checkbox");
        loginPage.markRememberMe();

        System.out.println("7. Click Sign In button");
        loginPage.logInBtn();

        System.out.println("8. Verify that the correct url is opened");
        Assert.assertTrue(homeUrl.matches(homeUrl));

        System.out.println("9. Verify that the correct toast message is displayed");
        String toastMsgText = loginPage.checkToastMsg().trim();
        Assert.assertEquals(toastMsgText, "Successful login!", "Incorrect toast message");

        System.out.println("10. Open profile page");
        header.clickProfile();

        System.out.println("11. Verify that the correct user is displayed");
        ProfilePage profilePage = new ProfilePage(driver);
        String user = profilePage.getUsernameText();
        Assert.assertEquals(user, username, "Incorrect username");
    }

    @AfterMethod
    public void closeDriver() {
        if (driver != null) {
            driver.close();
        }
    }
}
