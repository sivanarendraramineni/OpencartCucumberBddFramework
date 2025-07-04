package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="input-firstname")
    private WebElement firstNameField;
    @FindBy(id="input-lastname")
    private WebElement lastNameField;
    @FindBy(id="input-email")
    private WebElement emailField;
    @FindBy(id="input-telephone")
    private WebElement telephoneField;
    @FindBy(id="input-password")
    private WebElement passwordField;
    @FindBy(id="input-confirm")
    private WebElement confirmPasswordField;
    @FindBy(name="agree")
    private WebElement privacyPolicyCheckbox;
    @FindBy(xpath="//input[@value='Continue']")
    private WebElement continueButton;
    @FindBy(xpath="//div[@class=\"alert alert-danger alert-dismissible\"]")
    private WebElement warningMessage;
    @FindBy(xpath="//div[@class=\"alert alert-danger alert-dismissible\"]")
    private WebElement duplicateEmailWarning;

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }
    public void enterTelephone(String telephone) {
        telephoneField.sendKeys(telephone);
    }
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }
    public void checkPrivacyPolicy() {
        if (!privacyPolicyCheckbox.isSelected()) {
            privacyPolicyCheckbox.click();
        }
    }
    public void clickContinue() {
        continueButton.click();
    }

    public String getWarningMessageText() {
        return warningMessage.getText();
    }

    public String getDuplicateEmailWarningText() {
        return duplicateEmailWarning.getText();
    }
}
