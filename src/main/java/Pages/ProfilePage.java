package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage{

    @FindBy(css = ".row h2")
    WebElement usernameProfile;
    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public String getUsernameText(){
        wait.until(ExpectedConditions.visibilityOf(usernameProfile));
        String profileName = usernameProfile.getText();
        return profileName;
    }
}
