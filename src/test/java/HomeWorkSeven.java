import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by olga on 02.04.17.
 */
public class HomeWorkSeven extends MainMethod {

    @Test
    public void clickMenuItems() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> listSize = driver.findElements(By.id("app-"));
        for(int i=0; i < listSize.size(); i++)
        {
            List<WebElement> elements = driver.findElements(By.id("app-"));
            elements.get(i).click();
            List<WebElement> subListSize = driver.findElements(By.xpath(".//*[@id='app-']/ul/li"));

            for (int k = 0; k < subListSize.size(); k++) {
                List<WebElement> subElements = driver.findElements(By.xpath(".//*[@id='app-']/ul/li"));
                subElements.get(k).click();

                Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='content']/h1")).isDisplayed());
            }

            Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='content']/h1")).isDisplayed());
        }
    }

}
