import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

/**
 * Created by Olga Cherniak on 4/19/2017.
 */
public class HomeWorkFourteen extends MainMethod {

    public boolean windowsOpened(int win){
        try{
            wait.until(numberOfWindowsToBe(win));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Test
    public void checkLinksInNewWindow () {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        ///Login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        ///add new country
        driver.findElement(By.cssSelector(".button[href*='edit_country']")).click();

        ///Open links in new window
        List<WebElement> fafa = driver.findElements(By.cssSelector(".fa.fa-external-link"));
        System.out.println();
        for (int i = 0; i<fafa.size(); i++){
            List<WebElement> outerLinks = driver.findElements(By.cssSelector(".fa.fa-external-link"));
            outerLinks.get(i).click();
            wait.until(numberOfWindowsToBe(i+2));
        }

        Assert.assertTrue(windowsOpened(fafa.size()+1));

        ///Switch and close new windows
        String mainWindow = driver.getWindowHandle();
        Set<String> newWindow =  driver.getWindowHandles();

        for(String windowHandle  : newWindow)
        {
            if(!windowHandle.equals(mainWindow))
            {
                driver.switchTo().window(windowHandle);

                    driver.close();
                driver.switchTo().window(mainWindow);
            }
        }
    }
}
