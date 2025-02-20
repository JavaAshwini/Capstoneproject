
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

    public class ScreenshotUtil {
        public static void captureScreenshot(WebDriver driver, String screenshotName) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("./screenshots/" + screenshotName + ".png");
            try {
                FileUtils.copyFile(srcFile, destFile);
                System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


