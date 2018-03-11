import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Anastasiia_Zhyrnova on 11.03.2018.
 */
public class Main_3 {
    public static void main (String[] args) {
        File chromeDriverPath = new File("drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath.getAbsolutePath());
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        login(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subtab-AdminCatalog")));
        WebElement catalog = driver.findElement(By.id("subtab-AdminCatalog"));

        Actions builder = new Actions(driver);
        builder.moveToElement(catalog).build().perform();

        WebElement categories = driver.findElement(By.linkText("категории"));

        builder.moveToElement(categories).click(categories).perform();
        driver.findElement(By.className("process-icon-new")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("copy2friendlyUrl")));
        driver.findElement(By.className("copy2friendlyUrl")).sendKeys("My category name");
        driver.findElement(By.id("category_form_submit_btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));
        WebElement categoryAdded = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
        driver.findElement(By.id("desc-category-back")).click();
        WebElement filter = driver.findElement(By.name("categoryFilter_name"));
        builder.click(filter).sendKeys("My category name").build().perform();
        driver.findElement(By.id("submitFilterButtoncategory")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()[contains(.,'My category name')]]")));
        logout(driver);

    }
    private static void login(WebDriver driver) {
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.findElement(By.id("email")).sendKeys("webinar.test@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Xcg7299bnSmMuRLp9ITw");
        driver.findElement(By.name("submitLogin")).click();
    }

    private static void logout(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        By avatar = (By.className("employee_avatar_small"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(avatar)).click();
        driver.findElement(By.id("header_logout")).click();
    }


}
