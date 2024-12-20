package Domaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class AmazonKorpa {
    public static void main(String[] args) throws InterruptedException {



        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.navigate().to("https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");

        WebElement addToCart = driver.findElement(By.id("add-to-cart-button"));
        addToCart.click();

        WebElement goToCart = driver.findElement(By.id("nav-cart-count"));
        Thread.sleep(3000);
        goToCart.click();

        String itemInCart = "https://www.amazon.com/gp/cart/view.html?ref_=nav_cart";

        WebElement book = driver.findElement(By.id("sc-subtotal-label-activecart"));
        String text = book.getText();

        Assert.assertEquals(text, "Subtotal (1 item):");

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        Thread.sleep(3000);

        goToCart = driver.findElement(By.id("nav-cart-count"));
        goToCart.click();

        Assert.assertEquals(driver.getCurrentUrl(), itemInCart);

        WebElement empty = driver.findElement(By.xpath("//*[@id=\"sc-empty-cart\"]/div[2]/h3"));
        String emptyCart = empty.getText();

        Assert.assertEquals(emptyCart, "Your Amazon Cart is empty");








    }
}
