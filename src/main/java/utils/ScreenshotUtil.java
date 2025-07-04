package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Save screenshots under test-output/screenshots/
        String destDir = System.getProperty("user.dir") + "/test-output/screenshots/";
        String screenshotName = testName.replaceAll(" ", "_") + "_" + timestamp + ".png";
        String absolutePath = destDir + screenshotName;

        // Create directory if not exists
        File dir = new File(destDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(absolutePath);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return relative path for Extent Report (relative to ExtentReport.html)
        return "./screenshots/" + screenshotName;
    }
}
