import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver.Options;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Anastasiia_Zhyrnova on 26.02.2018.
 */
public class Main {
    public static void main (String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        final ChromeOptions chromeOptions = options.addArguments("start-maximized");
        WebDriver driver = initChromeDriver();
        WebDriver driver = new ChromeDriver(options);
        //WebDriver.Options options;
        //options = driver.manage().window().maximize();
        driver.get("https://www.bing.com");

        WebElement fieldSearch = driver.findElement(By.id("sb_form_q"));

        WebElement button = driver.findElement(By.name("go"));
        fieldSearch.sendKeys("Slayer");
        button.click();

        List<WebElement> searchResults = driver.findElements(By.className("b_algo"));
        System.out.println(searchResults.size());

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        //driver.wait(1);
        driver.close();
    }



    public static WebDriver initChromeDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        return new ChromeDriver();
    }


}
