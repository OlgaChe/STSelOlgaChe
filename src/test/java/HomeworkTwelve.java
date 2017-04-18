import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by olga on 17.04.17.
 */
public class HomeworkTwelve extends MainMethod {

    public String product_name = "duck for test";

    @Test
    public void addProduct() {
        driver.get("http://localhost/litecart/admin/");

        ///Login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        ///Go to add product page
        driver.findElement(By.xpath(".//*[@id='app-']/a[contains(@href, 'catalog')]")).click();

        driver.findElement(By.cssSelector("#content>div>a[href*='edit_product']")).click();

        ///General Tab
        driver.findElement(By.name("name[en]")).clear();
        driver.findElement(By.name("name[en]")).sendKeys(product_name);

        driver.findElement(By.name("code")).clear();
        driver.findElement(By.name("code")).sendKeys("12345");

        driver.findElement(By.cssSelector(".input-wrapper>table>tbody>tr>td>input[data-priority='1']")).click();
        driver.findElement(By.cssSelector(".input-wrapper>table>tbody>tr>td>input[value='1-2']")).click();

        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("10");

        WebElement quantity = driver.findElement(By.name("quantity_unit_id"));
        Select select = new Select(quantity);
        select.selectByVisibleText("pcs");

        WebElement delivery = driver.findElement(By.name("delivery_status_id"));
        select = new Select(delivery);
        select.selectByValue("1");

        WebElement soldout = driver.findElement(By.name("sold_out_status_id"));
        select = new Select(soldout);
        select.selectByValue("2");

        File dir = new File("src");
        File pic = new File(dir.getAbsolutePath(), "duck.png");

        driver.findElement(By.name("new_images[]")).sendKeys(String.valueOf(pic));

        driver.findElement(By.name("date_valid_from")).sendKeys("04-01-2017");
        driver.findElement(By.name("date_valid_to")).sendKeys("04-21-2017");

        ///Info Tab
        driver.findElement(By.cssSelector(".index>li>a[href*='#tab-information']")).click();

        WebElement manufacturer = driver.findElement(By.name("manufacturer_id"));
        select = new Select(manufacturer);
        select.selectByValue("1");

        WebElement supplier = driver.findElement(By.name("supplier_id"));
        select = new Select(supplier);
        select.selectByValue("");

        driver.findElement(By.name("keywords")).clear();
        driver.findElement(By.name("keywords")).sendKeys("ducks");

        driver.findElement(By.xpath("(//*[contains(text(),'Short')])/following-sibling::span/input")).clear();
        driver.findElement(By.xpath("(//*[contains(text(),'Short')])/following-sibling::span/input")).sendKeys("test duck");

        driver.findElement(By.cssSelector(".trumbowyg-editor")).clear();
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("This is a duck");

        driver.findElement(By.xpath("(//*[contains(text(),'Head')])/following-sibling::span/input")).clear();
        driver.findElement(By.xpath("(//*[contains(text(),'Head')])/following-sibling::span/input")).sendKeys("Head Duck");

        driver.findElement(By.xpath("(//*[contains(text(),'Meta')])/following-sibling::span/input")).clear();
        driver.findElement(By.xpath("(//*[contains(text(),'Meta')])/following-sibling::span/input")).sendKeys("Meta Duck");

        ///Price Tab
        driver.findElement(By.cssSelector(".index>li>a[href*='#tab-prices']")).click();

        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("2");

        WebElement currency = driver.findElement(By.name("purchase_price_currency_code"));
        select = new Select(currency);
        select.selectByValue("USD");

        WebElement tax_class = driver.findElement(By.name("tax_class_id"));
        select = new Select(tax_class);
        select.selectByValue("");

        driver.findElement(By.name("prices[USD]")).clear();
        driver.findElement(By.name("prices[USD]")).sendKeys("10");

        driver.findElement(By.name("prices[EUR]")).clear();
        driver.findElement(By.name("prices[EUR]")).sendKeys("9");

        driver.findElement(By.name("save")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'" + product_name + "')]")).isDisplayed());

    }
}
