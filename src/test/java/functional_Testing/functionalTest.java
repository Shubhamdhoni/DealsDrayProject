package functional_Testing;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class functionalTest {

	public static void main(String[] args) throws InterruptedException, IOException {
     
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demo.dealsdray.com/");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		driver.findElement(By.xpath("//span[text()='Orders']")).click();
		driver.findElement(By.xpath("//button[@align='right']")).click();
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\Admin\\Downloads\\demo-data.xlsx");
		driver.findElement(By.xpath("//button[text()='Import']")).click();
		driver.findElement(By.xpath("//button[text()='Validate Data']")).click();
		
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		Random rand = new Random();
		int rand_int1 = rand.nextInt(1000);
		
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File("./image"+rand_int1+".png"));
		//driver.quit();

	}

}
