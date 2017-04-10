import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Created by olga on 06.04.17.
 */
public class HomeWorkTen {

    public WebDriver driver;
    public WebDriverWait wait;

    public boolean colorGrey (WebElement reg){
        String rgb[];
        String rgbval = reg.getCssValue("color");
        if (rgbval.startsWith("rgba")) {
            rgb = rgbval.replace("rgba(", "").replace(")", "").split(", ");
        } else {
            rgb = rgbval.replace("rgb(", "").replace(")", "").split(", ");
        }
        if (rgb[0].equals(rgb[1])&&rgb[0].equals(rgb[2])){
            return true;
        }else {
            return false;
        }
    }

    public boolean colorRed (WebElement sale){
        String rgb[];
        String rgbval = sale.getCssValue("color");
        if (rgbval.startsWith("rgba")) {
            rgb = rgbval.replace("rgba(", "").replace(")", "").split(", ");
        } else {
            rgb = rgbval.replace("rgb(", "").replace(")", "").split(", ");
        }
        if (rgb[1].equals("0")&&rgb[2].equals("0")){
            return true;
        }else {
            System.out.println(rgb[2] + rgb[1]);
            return false;

        }
    }

    @Before
    public void start() {

        //driver = new ChromeDriver();
        FirefoxOptions options = new FirefoxOptions().setLegacy(true);
        driver = new FirefoxDriver(options);
        //driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void productPage(){
        driver.navigate().to("http://localhost/litecart/");

        ///Get home page values
        WebElement priceSaleExp = driver.findElement(By.xpath(".//*[@id='box-campaigns']/div/ul/li/a/div[contains(@class, 'price-wrapper')]/*[@class='campaign-price']"));
        WebElement priceRegExp = driver.findElement(By.xpath(".//*[@id='box-campaigns']/div/ul/li/a/div[contains(@class, 'price-wrapper')]/*[@class='regular-price']"));

        String nameExp = driver.findElement(By.xpath(".//*[@id='box-campaigns']/div/ul/li/a/div[contains(@class, 'name')]")).getText();
        String priceSaleValExp = priceSaleExp.getText();
        String priceRegValExp = priceRegExp.getText();

        ///Assert that regular price font is crossed and sale price font is bold
        Assert.assertTrue(priceRegExp.getTagName().equals("s")&&priceSaleExp.getTagName().equals("strong"));
        System.out.println(priceRegExp.getCssValue("color"));

        ///Assert that regular price font is smaller than sale one
        Assert.assertTrue(Float.parseFloat(priceRegExp.getCssValue("font-size").replace("px", "")) < Float.parseFloat(priceSaleExp.getCssValue("font-size").replace("px", "")));

        ///Assert that regular price value is bigger than sale one
        Assert.assertTrue(Integer.parseInt(priceRegValExp.replace("$", "")) > Integer.parseInt(priceSaleValExp.replace("$", "")));

        ///Assert that regular price font is grey and sale price font is red
        Assert.assertTrue(colorGrey(priceRegExp)&&colorRed(priceSaleExp));

        ///Navigate to product page
        driver.findElement(By.xpath(".//*[@id='box-campaigns']/div/ul/li")).click();

        ///Get product page values
        WebElement priceSaleAct = driver.findElement(By.cssSelector(".campaign-price"));
        WebElement priceRegAct = driver.findElement(By.cssSelector(".regular-price"));

        String nameAct = driver.findElement(By.cssSelector("#box-product>div>h1")).getText();
        String priceSaleValAct = priceSaleAct.getText();
        String priceRegValAct = priceRegAct.getText();

        ///Assert names are same
        Assert.assertTrue(nameExp.equals(nameAct));

        ///Assert prices are same
        Assert.assertTrue(priceRegValExp.equals(priceRegValAct)&&priceSaleValExp.equals(priceSaleValAct));

        ///Assert that regular price font is crossed and sale price font is bold
        Assert.assertTrue(priceRegAct.getTagName().equals("s")&&priceSaleAct.getTagName().equals("strong"));

        ///Assert that regular price font is smaller than sale one
        Assert.assertTrue(Float.parseFloat(priceRegAct.getCssValue("font-size").replace("px", "")) < Float.parseFloat(priceSaleAct.getCssValue("font-size").replace("px", "")));

        ///Assert that regular price value is bigger than sale one
        Assert.assertTrue(Integer.parseInt(priceRegValAct.replace("$", "")) > Integer.parseInt(priceSaleValAct.replace("$", "")));

        ///Assert that regular price font is grey and sale price font is red
        Assert.assertTrue(colorGrey(priceRegAct)&&colorRed(priceSaleAct));
    }

    @After
    public void stop() {

        driver.quit();
    }
}
