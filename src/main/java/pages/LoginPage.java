package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(id="input-email")
    private WebElement emailField;

    @FindBy(id="input-password")
    private WebElement passwordField;

    @FindBy(xpath="//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
    private WebElement warningMessage;

    public void enteremail(String email) {
        emailField.sendKeys(email);

    }

    public void enterpassword(String password) {
        passwordField.sendKeys(password);

    }

    public void clickonLoginButton() {
        loginButton.click();

    }

    public String getWarningMessageText() {
        return warningMessage.getText();
    }

}
