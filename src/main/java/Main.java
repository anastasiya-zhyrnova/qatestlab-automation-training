import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.List;

/**
 * Created by Anastasiia_Zhyrnova on 04.03.2018.
 */
public class Main {

    public static void main (String[] args) {
        File chromeDriverPath = new File("drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath.getAbsolutePath());
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //login
        login(driver);
        //logout
        logout(driver);

        //Part2
        login(driver);
        //Dashboard
        driver.findElement(By.id("tab-AdminDashboard")).click();
        checkTitles(driver);

        //Заказы
        driver.findElement(By.id("subtab-AdminParentOrders")).click();
        checkTitles(driver);

        //Каталог
        driver.findElement(By.id("subtab-AdminCatalog")).click();
        checkTitles(driver);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //System.out.println(driver.getPageSource());

        //WebDriverWait wait = new WebDriverWait(driver,20);
        //WebElement clients;
        //clients = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subtab-AdminParentCustomer")));
        //System.out.println(driver.getPageSource());

        //Клиенты
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.findElement(By.id("subtab-AdminParentCustomer")).click();
        checkTitles(driver);

        //Служба поддержки
        driver.findElement(By.id("subtab-AdminParentCustomerThreads")).click();
        checkTitles(driver);

        //Статистика
        driver.findElement(By.id("subtab-AdminStats")).click();
        checkTitles(driver);

        //driver.quit();*/

    }

    private static void login(WebDriver driver) {
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.findElement(By.id("email")).sendKeys("webinar.test@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Xcg7299bnSmMuRLp9ITw");
        driver.findElement(By.name("submitLogin")).click();
    }

    private static void logout(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.findElement(By.className("employee_avatar_small")).click();
        driver.findElement(By.id("header_logout")).click();
    }

    private static void checkTitles (WebDriver driver) {
        String expectedTitle = driver.getTitle();
        System.out.println(expectedTitle);
        driver.navigate().refresh();
        String actualTitle = driver.getTitle();
        assertEqualTitles(expectedTitle, actualTitle);
    }

    private static void assertEqualTitles(String title1, String title2) {
        if (!title1.equals(title2)) {
            throw new IllegalArgumentException("Titles are not equal!");
        }
    }
}
