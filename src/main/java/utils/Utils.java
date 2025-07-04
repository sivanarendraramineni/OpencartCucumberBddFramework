package utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Random;
import java.util.UUID;

public class Utils {

    private static final Logger log = LoggerHelper.getLogger(Utils.class);

    public static void WaitForVisibility(WebDriver driver, WebElement element, int timeoutInSeconds) {
        log.info("Waiting for element to be visible: " + element);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Element not visible within " + timeoutInSeconds + " seconds: " + e.getMessage());
        }
    }

    public static void WaitForElementClickble(WebDriver driver, WebElement element, int timeoutInSeconds) {
        log.info("Waiting for element to be clickable: " + element);

        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.elementToBeClickable(element));
        }catch(Exception e){

        }

    }
    public static void takeScreenshot(WebDriver driver, String filePath) {
        log.info("Taking screenshot and saving to: " + filePath);
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), Paths.get(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String generateRandomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@gmail.com";
    }
    public static String generateRandomString(int length) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, length);

    }
    public static String generateRandomNumber(int length) {
        return String.valueOf(Math.abs(new Random().nextLong()))
                .substring(0, Math.min(length, String.valueOf(Long.MAX_VALUE).length()));
    }
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}


