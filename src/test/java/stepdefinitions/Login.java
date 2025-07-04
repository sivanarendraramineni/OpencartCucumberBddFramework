package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.LoggerHelper;

public class Login {


    WebDriver driver = Hooks.driver;
    LoginPage loginPage= new LoginPage(driver);
    HomePage homePage= new HomePage(driver);
    AccountPage accountPage= new AccountPage(driver);
    private static final Logger log = LoggerHelper.getLogger(Login.class);

    @Given("user navigate to login page")
    public void user_navigate_to_login_page() {
        log.info("Navigating to login page");
        homePage.clickOnMyAccount();
        homePage.clickOnLogin();

    }

    @When("user entered email address {string} into email field")
    public void user_entered_email_address_into_email_field(String email) {
        loginPage.enteremail(email);
        log.info("User entered email address: " + email);
    }

    @When("user enter password {string} into password field")
    public void user_enter_password_into_password_field(String password) {
        loginPage.enterpassword(password);
        log.info("User entered password: " + password);
    }


    @When("click on login button")
    public void click_on_login_button() {
        log.info(" Clicking on login button");
        loginPage.clickonLoginButton();

    }

    @Then("user loggedin successfully")
    public void user_loggedin_successfully() {
        Assert.assertTrue(accountPage.isMyAccountHeaderDisplayed());
        log.info("User logged in successfully, My Account header is displayed.");

    }

    @Then("user get warning massage for invalid credentials")
    public void user_Get_Warning_Massage_For_Invalid_Credentials() {
        String warningMessage = loginPage.getWarningMessageText();
        Assert.assertTrue(warningMessage.contains("Warning: No match for E-Mail Address and/or Password."));
        log.info("User received warning message for invalid credentials: " + warningMessage);

    }


    @Then("user get {string} message")
    public void user_get_message(String expectedMessage) {
        if (expectedMessage.equalsIgnoreCase("True")) {
            boolean status=accountPage.isMyAccountHeaderDisplayed();
            Assert.assertTrue(status);
            log.info("User logged in successfully, My Account header is displayed.");
        } else if (expectedMessage.equalsIgnoreCase("False")) {
            boolean status = accountPage.isMyAccountHeaderDisplayed();
            Assert.assertFalse(status);
            log.info("User is not logged in, My Account header is not displayed.");
        } else if (expectedMessage.equalsIgnoreCase("Warning: No match for E-Mail Address and/or Password.")) {
            String warningMessage = loginPage.getWarningMessageText();
            Assert.assertTrue(warningMessage.contains("Warning: No match for E-Mail Address and/or Password."));
            log.info("User received warning message for invalid credentials: " + warningMessage);
        }
    }


}
