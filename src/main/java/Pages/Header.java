package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header extends BasePage {

    @FindBy(linkText = "Login")
    WebElement loginLink;

    @FindBy(id = "nav-link-profile")
    WebElement profileLink;

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickLogin() {
        clickElement(loginLink);
    }

    public void clickProfile() {
        clickElement(profileLink);
    }
}
