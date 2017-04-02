import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by olga on 03.04.17.
 */
public class HomeWorkEight extends MainMethod {

    @Test
    public void checkStickers(){
        driver.navigate().to("http://localhost/litecart/en/");

        List<WebElement> ducks = driver.findElements(By.cssSelector(".link[title]"));
        System.out.println(ducks.size());
        for (int i = 0; i < ducks.size(); i++){
            Assert.assertTrue(driver.findElement(By.xpath("(//a[contains(@class, 'link')]/div[1])[" + (i+1) + "]")).isDisplayed());
        }
    }

}
