package task;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test4 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver cdriver = new ChromeDriver();
        WebDriver fdriver = new FirefoxDriver();

        cdriver.manage().window().maximize();
        fdriver.manage().window().maximize();

        Dimension[] resolutions = { new Dimension(1920, 1080), new Dimension(1366, 768), new Dimension(1536, 864),
                new Dimension(360, 640), new Dimension(414, 896), new Dimension(375, 667) };

        File screenshotsFolder = new File("screenshots4");
        screenshotsFolder.mkdirs();

        for (Dimension resolution : resolutions) {
            cdriver.manage().window().setSize(resolution);
            fdriver.manage().window().setSize(resolution);

            cdriver.get("https://www.getcalley.com/page-sitemap.xml");
            cdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            cdriver.findElement(By.xpath("//*[@id=\"sitemap\"]/tbody/tr[4]/td[1]/a")).click();
            captureScreenshot(cdriver, resolution);

            fdriver.get("https://www.getcalley.com/page-sitemap.xml");
            fdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            fdriver.findElement(By.xpath("//*[@id=\"sitemap\"]/tbody/tr[4]/td[1]/a")).click();
            captureScreenshot(fdriver, resolution);

        }

        cdriver.quit();
        fdriver.quit();
    }

    public static void captureScreenshot(WebDriver driver, Dimension resolution) {
        File screenshot4 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String formattedDateTime = currentTime.format(formatter);

            FileUtils.copyFile(screenshot4,
                    new File("screenshots4/screenshot_" + resolution.getWidth() + "x" + resolution.getHeight() + "_"
                            + formattedDateTime + ".png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
