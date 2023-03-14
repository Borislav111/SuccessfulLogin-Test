package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    private String logInUrl = "http://training.skillo-bg.com/users/login";
    @FindBy(id = "defaultLoginFormUsername")
    WebElement usernameField;

    @FindBy(id = "defaultLoginFormPassword")
    WebElement passwordField;

    @FindBy(css = "*[formcontrolname='rememberMe']")
    WebElement rememberMeBtn;

    @FindBy(id = "sign-in-button")
    WebElement signInBtn;

    @FindBy(css = "*.h4")
    WebElement signInHeader;

    @FindBy(css = "#toast-container .toast-message")
    WebElement toastMsg;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getSignInHeader(){
        wait.until(ExpectedConditions.visibilityOf(signInHeader));
        String signInHeaderText = signInHeader.getText();
        return signInHeaderText;
    }

    public void enterUsername(String username){
        enterText(usernameField, username);
    }
    public void enterPassword(String password){
        enterText(passwordField, password);
    }
    public void markRememberMe(){
        clickElement(rememberMeBtn);
    }
    public void logInBtn(){
        clickElement(signInBtn);
    }
    public void verifyUrl(){
        wait.until(ExpectedConditions.urlToBe(logInUrl));
    }
    public String checkToastMsg(){
        wait.until(ExpectedConditions.visibilityOf(toastMsg));
        String toastMsgText = toastMsg.getText();
        return toastMsgText;
    }
}
