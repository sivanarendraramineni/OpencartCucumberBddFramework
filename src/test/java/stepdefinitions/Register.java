package stepdefinitions;

import factory.DriverSetup;
import hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AccountPage;
import pages.HomePage;
import pages.RegisterPage;
import utils.LoggerHelper;
import utils.Utils;
import utils.ExcelReader;

import java.util.List;
import java.util.Map;

public class Register {
    WebDriver driver = DriverSetup.getDriver();;
    HomePage homePage = new HomePage(driver);
    RegisterPage registerPage = new RegisterPage(driver);
    AccountPage accountPage = new AccountPage(driver);
    private static final Logger log = LoggerHelper.getLogger(Register.class);

    @Given("user navigate to registration page")
    public void user_navigate_to_registration_page() {
        log.info("Navigating to registration page");
        homePage.clickOnMyAccount();
        homePage.clickOnRegister();
        log.info("User is on the registration page.");

    }

    @When("user enter the details into fields")
    public void user_enter_the_details_into_fields(DataTable dataTable) {
        log.info("Entering user details from data table");
        Map<String, String> datamap = dataTable.asMap(String.class,String.class);

        registerPage.enterFirstName(datamap.get("firstname"));
        registerPage.enterLastName(datamap.get("lastname"));
        registerPage.enterEmail(Utils.generateRandomEmail());
        registerPage.enterTelephone(datamap.get("telephone"));
        registerPage.enterPassword(datamap.get("password"));
        registerPage.enterConfirmPassword(datamap.get("confirmpassword"));
        log.info(" user entered email: "+Utils.generateRandomEmail());
        log.info("User details entered successfully.");
    }

    @When("user select privacy policy")
    public void user_select_privacy_policy() {
        registerPage.checkPrivacyPolicy();
        log.info("User has selected the privacy policy checkbox.");

    }

    @Then("user clicks on continue button")
    public void user_clicks_on_continue_button() {
        registerPage.clickContinue();
        log.info("User clicked on continue button to submit registration form.");
    }

    @Then("user account created successfully")
    public void user_account_created_successfully() {
        Assert.assertTrue(accountPage.isAccountCreatedHeaderDisplayed());
        log.info("User account created successfully, account creation header is displayed.");
    }

    @When("user enter the details into fields using util class")
    public void user_enter_the_details_into_fields_using_util_class() {
        log.info(" Entering user details using utility class");
        String firstName = Utils.generateRandomString(8);
        String lastName = Utils.generateRandomString(8);
        String email = Utils.generateRandomEmail();
        String phone = Utils.generateRandomNumber(10);
        String password = Utils.generateRandomString(8);
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(email);
        registerPage.enterTelephone(phone);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(password);
        log.info("User entered details:");
        log.info("First Name: " + firstName);
        log.info("Last Name: " + lastName);
        log.info("Email: " + email);
        log.info("Phone: " + phone);
        log.info("Password: " + password);

    }

    @When("user not enter any details")
    public void user_not_enter_any_details() {
        log.info("User is not entering any details in the registration form.");
        registerPage.enterFirstName("");
        registerPage.enterLastName("");
        registerPage.enterEmail("");
        registerPage.enterTelephone("");
        registerPage.enterPassword("");
        registerPage.enterConfirmPassword("");
        log.info("User has left all fields empty.");

    }

    @Then("user get warning message for mandatory fields")
    public void user_get_warning_message_for_mandatory_fields() {
        log.info("Checking for warning message for mandatory fields");
        String warningMessage = registerPage.getWarningMessageText();
        Assert.assertTrue(warningMessage.contains("Warning: You must agree to the Privacy Policy!"));
        log.info("User received warning message for mandatory fields: " + warningMessage);

    }

    @When("user registers using data from {string} sheet at row {string}")
    public void user_registers_using_data_from_excel(String sheetName, String rowNumber) {
        log.info("Registering user using data from Excel sheet: " + sheetName + " at row: " + rowNumber);

        List<Map<String, String>> dataList = ExcelReader.getDataAsList("src/test/resources/testdata/TestData.xlsx", sheetName);

        if (dataList.isEmpty()) {
            throw new RuntimeException("No data found in Excel sheet: " + sheetName);
        }

        Map<String, String> data = dataList.get(Integer.parseInt(rowNumber) - 1);

        registerPage.enterFirstName(data.get("FirstName"));
        registerPage.enterLastName(data.get("LastName").trim());
        registerPage.enterEmail(data.get("Email"));
        registerPage.enterTelephone(data.get("Telephone"));
        registerPage.enterPassword(data.get("Password"));
        registerPage.enterConfirmPassword(data.get("ConfirmPassword"));

        log.info("User details entered from Excel:");
        log.info("First Name: " + data.get("FirstName"));
        log.info("Last Name: " + data.get("LastName"));
        log.info("Email: " + data.get("Email"));
        log.info("Telephone: " + data.get("Telephone"));
        log.info("Password: " + data.get("Password"));
        log.info("Confirm Password: " + data.get("ConfirmPassword"));
    }
    @Then("user get warning message for duplicate email address")
    public void user_get_warning_message_for_duplicate_email_address() {
        log.info("Checking for warning message for duplicate email address");
        String warningMessage = registerPage.getDuplicateEmailWarningText();
        Assert.assertTrue(warningMessage.contains("Warning: E-Mail Address is already registered!"));
        log.info("User received warning message for duplicate email address: " + warningMessage);
    }




}
