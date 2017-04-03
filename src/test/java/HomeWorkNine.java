import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by olga on 04.04.17.
 */
public class HomeWorkNine extends MainMethod {

    @Test
    public void checkCountriesSort(){
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> countriesList = driver.findElements(By.xpath(".//*[@id='content']/form/table/tbody/tr/td[5]"));
        ArrayList<String> actualList = new ArrayList<>();
        ArrayList<String> expectedList = new ArrayList<>();

        for (int i = 0; i<countriesList.size(); i++ ){
            actualList.add(countriesList.get(i).getText());
        }

        for (int k = 0; k<countriesList.size(); k++ ){
            expectedList.add(countriesList.get(k).getText());
        }

        Collections.sort(expectedList);

        Assert.assertTrue(actualList.equals(expectedList));
    }

    @Test
    public void checkCountriesZonesSort() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();

        List<WebElement> countriesListSize = driver.findElements(By.xpath(".//*[@id='content']/form/table/tbody/tr/td[6]"));
        System.out.println(countriesListSize.size());
        for (int i = 0; i <countriesListSize.size(); i++) {
            List<WebElement> countriesList = driver.findElements(By.xpath(".//*[@id='content']/form/table/tbody/tr/td[6]"));

            if (!countriesList.get(i).getText().equals("0")){
                driver.findElement(By.xpath("(//*[@id='content']/form/table/tbody/tr/td[5])" + "[" + (i+1) + "]/a")).click();

                List<WebElement> zonesList = driver.findElements(By.xpath(".//*[@id='table-zones']/tbody/tr/td[3]"));
                ArrayList<String> actualList = new ArrayList<>();
                ArrayList<String> expectedList = new ArrayList<>();

                for (int j = 0; j<zonesList.size() - 1; j++ ){
                    actualList.add(zonesList.get(j).getText());
                }

                for (int k = 0; k<zonesList.size() - 1; k++ ){
                    expectedList.add(zonesList.get(k).getText());
                }

                Collections.sort(expectedList);

                Assert.assertTrue(actualList.equals(expectedList));

                driver.navigate().back();
            }
        }
    }

}
