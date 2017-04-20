import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

/**
 * Created by Olga Cherniak on 4/19/2017.
 */
public class HomeworkSeventeen extends MainMethod {

    @Test
    public void checkLinksInNewWindow () {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        ///Login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        ///Open links
        List<WebElement> ducks = driver.findElements(By.cssSelector(".row>td>a"));

        for (int i = 0; i < ducks.size(); i++) {
            List<WebElement> products = driver.findElements(By.cssSelector(".row>td>a"));
            products.get(i).click();
            System.out.println(driver.manage().logs().getAvailableLogTypes());
            driver.manage().logs().get("browser").getAll().forEach(l -> System.out.println(l));
            driver.manage().logs().get("driver").getAll().forEach(l -> System.out.println(l));
            driver.manage().logs().get("client").getAll().forEach(l -> System.out.println(l));
            driver.navigate().back();
        }
    }
}
