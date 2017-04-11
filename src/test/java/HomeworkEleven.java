import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by olga on 11.04.17.
 */
public class HomeworkEleven extends MainMethod {

    DateFormat dateFormat = new SimpleDateFormat("MMddHHmm");
    Date date = new Date();
    public String uniqueAccount = dateFormat.format(date);

    @Test
    public void createAccount(){
        String email = "test_" + uniqueAccount + "@yopmail.com";

        driver.navigate().to("http://localhost/litecart/en/");
        driver.findElement(By.linkText("New customers click here")).click();

        driver.findElement(By.name("tax_id")).clear();
        driver.findElement(By.name("tax_id")).sendKeys(uniqueAccount);

        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("Company");

        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("India");
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("Tarlton");

        driver.findElement(By.name("address1")).clear();
        driver.findElement(By.name("address1")).sendKeys("Peachtree Street 13");
        driver.findElement(By.name("address2")).clear();
        driver.findElement(By.name("address2")).sendKeys("Apt 13");

        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys("30303");

        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Atlanta");

        WebElement dropDown = wait.until(presenceOfElementLocated(By.name("country_code")));
        Select select = new Select(dropDown);
        select.selectByVisibleText("United States");


        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(email);

        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys("4045557856");

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Azerty12");
        driver.findElement(By.name("confirmed_password")).clear();
        driver.findElement(By.name("confirmed_password")).sendKeys("Azerty12");

        driver.findElement(By.name("create_account")).click();

        driver.findElement(By.linkText("Logout")).click();

        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Azerty12");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.linkText("Logout")).click();
    }

}
