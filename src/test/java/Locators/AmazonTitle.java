package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Driver;

import java.util.Arrays;
import java.util.List;

public class AmazonTitle {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver("chrome");
    }

    @Test
    public void amazon() throws InterruptedException {
        driver.get("https://www.amazon.com/");
        WebElement searchB = driver.findElement(By.id("twotabsearchtextbox"));
        String term="wooden spoon";
        searchB.sendKeys(term);

        Thread.sleep(1000);
        searchB.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        String title=driver.getTitle();

        Assert.assertTrue(title.contains(term));
        Thread.sleep(2000);



    }

    @Test
    public void wikipedia() throws InterruptedException {
        driver.get("https://www.wikipedia.org/");
        WebElement search = driver.findElement(By.id("searchInput"));
        search.sendKeys("selenium webdriver");
        search.sendKeys(Keys.ENTER);

        Thread.sleep(1000);
        WebElement result=driver.findElement(By.xpath("//a[@title='Selenium (software)']"));
        result.click();
        Thread.sleep(1000);
        String url=driver.getCurrentUrl();
        Assert.assertTrue(url.endsWith("Selenium_(software)"));


    }

    @Test
    public void google() throws InterruptedException {
        List<String> searchStrs = Arrays.asList("Java", "cucumber bdd", "Selenium web browser automation" );
        driver.get("http://www.gooogle.com");
        for (String s: searchStrs)
        {
            WebElement searchbar=driver.findElement(By.xpath("//input[@title='Search']"));
            searchbar.sendKeys(s);
            Thread.sleep(1000);
            searchbar.sendKeys(Keys.ENTER);
            String exp=driver.findElement(By.xpath("//cite[1]")).getText();
            System.out.println(exp);
            Thread.sleep(1000);
            WebElement link=driver.findElement(By.xpath(("(//div[@class='rc']/div/a )[1]")));
            link.click();
            Thread.sleep(1000);
            String actual=driver.getCurrentUrl();
            System.out.println();
            Assert.assertTrue(actual.contains(exp));
            driver.navigate().back();
            driver.navigate().back();
            Thread.sleep(1000);


        }

    }

    @Test
    public void ebay() throws InterruptedException {
        driver.get("https://www.ebay.com/");

        WebElement searchbox=driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        searchbox.sendKeys("wooden spoon");
        searchbox.sendKeys(Keys.ENTER);

        String totalResult=driver.findElement(By.xpath("(//h1/span)[1]")).getText().replace(",","");
        System.out.println(totalResult);

        WebElement allCategories=driver.findElement(By.xpath("//li[@class='srp-refine__category__item srp-refine__category__list--flush']/a"));
        allCategories.click();
        Thread.sleep(1000);

        Thread.sleep(1000);
        driver.navigate().back();
        searchbox=driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        Thread.sleep(1000);

        String actual=searchbox.getAttribute("value");
        System.out.println(actual);

        Assert.assertEquals(actual, "wooden spoon");
        Thread.sleep(1000);
        driver.navigate().back();
        searchbox=driver.findElement(By.xpath("(//input[@type='text'])[1]"));

        Assert.assertEquals("" ,searchbox.getText());

    }

    @Test
    public void vytrackTitleTest() throws InterruptedException {
        driver.get("https://qa3.vytrack.com/");
        String user="user1";
        String pswd="UserUser123";
        WebElement userInput=driver.findElement(By.id("prependedInput"));
        WebElement pswInput=driver.findElement(By.id("prependedInput2"));
        userInput.sendKeys(user);
        pswInput.sendKeys(pswd);
        pswInput.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        WebElement userName=driver.findElement(By.xpath("//*[@id='user-menu']/a"));
        String uName= userName.getText();
        System.out.println(uName);
        userName.click();
        Thread.sleep(1000);
        WebElement myConfig= driver.findElement(By.linkText("My Configuration"));
        myConfig.click();
        Thread.sleep(3000);
        String titleH=driver.getTitle();
        System.out.println(titleH);
        Assert.assertTrue(titleH.startsWith(uName));
    }


    @AfterMethod
    public void tearDown()
    {
        Driver.closeDriver();
    }
}
