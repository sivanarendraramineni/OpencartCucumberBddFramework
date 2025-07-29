package manager;

import factory.DriverSetup;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.HomePage;
import pages.AccountPage;
import pages.RegisterPage;

public class PageObjectManager {

    WebDriver driver = DriverSetup.getDriver();

    private LoginPage loginPage;
    private HomePage homePage;
    private AccountPage accountPage;
    private RegisterPage registerPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginpage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public HomePage homepage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public AccountPage accountpage() {
        if (accountPage == null) {
            accountPage = new AccountPage(driver);
        }
        return accountPage;
    }
    public RegisterPage registerpage() {
        if (registerPage == null) {
            registerPage = new RegisterPage(driver);
        }
        return registerPage;
    }
}
