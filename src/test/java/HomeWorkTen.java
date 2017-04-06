import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by olga on 06.04.17.
 */
public class HomeWorkTen {

    public WebDriver driver;
    public WebDriverWait wait;

    public boolean colorGrey (WebElement reg){
        String[] rgb = reg.getCssValue("color").replace("rgba(", "").replace(")", "").split(", ");
        if (rgb[0].equals(rgb[1])&&rgb[0].equals(rgb[2])){
            return true;
        }else {
            return false;
        }
    }

    public boolean colorRed (WebElement sale){
        String[] rgb = sale.getCssValue("color").replace("rgba(", "").replace(")", "").split(", ");
        if (rgb[1].equals("0")&&rgb[2].equals("0")){
            return true;
        }else {
            System.out.println(rgb[2] + rgb[1]);
            return false;

        }
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
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

        System.out.println(priceRegValExp + priceSaleValExp);
        System.out.println(priceRegExp.getCssValue("color"));
        System.out.println(Float.parseFloat(priceRegExp.getCssValue("font-size").replace("px", "")));
        System.out.println(priceRegExp.getCssValue("font-weight"));
        System.out.println(priceSaleExp.getCssValue("color"));
        System.out.println(Float.parseFloat(priceSaleExp.getCssValue("font-size").replace("px", "")));
        System.out.println(priceSaleExp.getCssValue("font-weight"));
        System.out.println(priceRegExp.getTagName());
        System.out.println(priceSaleExp.getTagName());

        ///Assert that regular price font is crossed and sale price font is bold
        Assert.assertTrue(priceRegExp.getTagName().equals("s")&&priceSaleExp.getCssValue("font-weight").equals("bold"));

        ///Assert that regular price font is smaller than sale one
        Assert.assertTrue(Float.parseFloat(priceRegExp.getCssValue("font-size").replace("px", "")) < Float.parseFloat(priceSaleExp.getCssValue("font-size").replace("px", "")));

        ///Assert that regular price value is bigger than sale one
        Assert.assertTrue(Integer.parseInt(priceRegValExp.replace("$", "")) > Integer.parseInt(priceSaleValExp.replace("$", "")));

        ///Assert that regular price font is grey and sale price font is red
        Assert.assertTrue(colorGrey(priceRegExp)&&colorRed(priceSaleExp));

        ///Navigate to product page
        driver.findElement(By.xpath(".//*[@id='box-campaigns']/div/ul/li")).click();

        ///Get home page values
        WebElement priceSaleAct = driver.findElement(By.xpath(".//*[@id='box-product']/div[contains(@class, 'content')]/div[contains(@class, 'information')]/div[contains(@class, 'price-wrapper')]/*[@class='campaign-price']"));
        WebElement priceRegAct = driver.findElement(By.xpath(".//*[@id='box-product']/div[contains(@class, 'content')]/div[contains(@class, 'information')]/div[contains(@class, 'price-wrapper')]/*[@class='regular-price']"));

        String nameAct = driver.findElement(By.xpath(".//*[@id='box-product']/div/h1")).getText();
        String priceSaleValAct = priceSaleAct.getText();
        String priceRegValAct = priceRegAct.getText();

        System.out.println(priceRegValAct + priceSaleValAct);
        System.out.println(priceRegAct.getCssValue("color"));
        System.out.println(Float.parseFloat(priceRegAct.getCssValue("font-size").replace("px", "")));
        System.out.println(priceRegAct.getCssValue("font-weight"));
        System.out.println(priceSaleAct.getCssValue("color"));
        System.out.println(Float.parseFloat(priceSaleAct.getCssValue("font-size").replace("px", "")));
        System.out.println(priceSaleAct.getCssValue("font-weight"));

        ///Assert names are same
        Assert.assertTrue(nameExp.equals(nameAct));

        ///Assert prices are same
        Assert.assertTrue(priceRegValExp.equals(priceRegValAct)&&priceSaleValExp.equals(priceSaleValAct));

        ///Assert that regular price font is crossed and sale price font is bold
        Assert.assertTrue(priceRegAct.getTagName().equals("s")&&priceSaleAct.getCssValue("font-weight").equals("bold"));

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
