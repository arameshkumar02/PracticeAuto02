package org.example;

import javafx.scene.layout.Priority;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Automation2 {
    static WebDriver driver;

    public static void waitUntilElementIsClickable(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void sleep1(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Reusable method to click on element
    public static void clickOnElement(By by) {
        driver.findElement(by).click();
    }
    //Reusable method to type the text
    public static void typeText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }
    //Reusable method to get Text From Element
    public static String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    public static long timestamp() {
        return (System.currentTimeMillis());
    }
    //Reusable method to select from drop down by visible text
    public static void selectFromDropDownByVisibleText(By by, String text) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }
    //Reusable method to select from drop down by Index
    public static void selectFromDropDownByIndex(By by, int n) {
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(n);
    }
    //Reusable method to select from drop down by value
    public static void selectFromDropDownByValue(By by, String value) {
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value);
    }

    @BeforeMethod
    public static void setBrowser() {
        //Path to open the browser
        System.setProperty("webdriver.chrome.driver", "C:\\SOFT\\Chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        //To maximize the window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //the path to url
        driver.get("https://demo.nopcommerce.com/");
    }

    @AfterMethod
    public static void closeBrowser() {
        driver.quit();
    }

    @Test
    public void logIn() {
        //setting up the browser
        setBrowser();
        //clicking on register button
        clickOnElement(By.xpath("//a[@class=\"ico-register\"]"));
        waitUntilElementIsClickable(By.id("register-button"), 10);
        //selecting gender
        clickOnElement(By.xpath("//label[@for=\"gender-male\"]"));
        //typing first name
        typeText(By.xpath("//input[@id=\"FirstName\"]"), "Avi");
        //typing surname
        typeText(By.xpath("//input[@id=\"LastName\"]"), "R");
        //selecting the day of birth
        selectFromDropDownByVisibleText(By.xpath("//select[@name=\"DateOfBirthDay\"]"), "2");
        //selecting the month of birth
        selectFromDropDownByValue(By.xpath("//select[@name=\"DateOfBirthMonth\"]"), "3");
        //selecting the year of birth
        selectFromDropDownByValue(By.xpath("//select[@name=\"DateOfBirthYear\"]"), "1998");
        //typing the email
        typeText(By.xpath("//input[@id=\"Email\"]"), "arameshkumar02+22@gmail.com");
        //typing the company name
        typeText(By.id("Company"), "aLtd");
        //unchecked the newsletter box
        clickOnElement(By.id("Newsletter"));
        //entering the password
        typeText(By.id("Password"), "Test143");
        // entering the confirmed password
        typeText(By.id("ConfirmPassword"), "Test143");
        //clicking on register to register the user
        clickOnElement(By.id("register-button"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Priting the display message of user has been register successful
        System.out.println(getTextFromElement(By.xpath("//div[@class=\"result\"]")));

        //}

        //  @Test
        // public void Email() {
        //Clicking on nopCommerce tittle to navigate to home
        clickOnElement(By.xpath("//img[@alt=\"nopCommerce demo store\"]"));
        //selecting the apple MacBook pro item from home page featured products
        clickOnElement(By.xpath("//a[text()=\"Apple MacBook Pro 13-inch\"]"));
        //clicking on the 'Email a friend' button to refer the product
        clickOnElement(By.xpath("//input[@value=\"Email a friend\"]"));
        //Tying the email of a friend
        typeText(By.id("FriendEmail"), "Test121@gmail.com");
        //Typing the personal message to a friend about the item
        typeText(By.id("PersonalMessage"), "Hello xyz i'm recommending u the MacBook as you been looking to purchase");
        //clicking on send button to send the email
        clickOnElement(By.xpath("//input[@value=\"Send email\"]"));
        //Display message of successful message
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println(getTextFromElement(By.xpath("//div[@class=\"result\"]")));
        closeBrowser();
        // }
    }

    @Test
    public void basket() {
        setBrowser();
        //selecting the computer category
        clickOnElement(By.xpath("//div[@class=\"header-menu\"]/ul[1]/li[1]/a[1]"));
        //clicking on the desktop
        clickOnElement(By.xpath("//img[@title=\"Show products in category Desktops\"]"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //adding the item to the cart
        clickOnElement(By.xpath("//input[contains(@onclick,\"2/1/1\")]"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //adding the item to the cart
        clickOnElement(By.xpath("//input[contains(@onclick,\"3/1/1\")]"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //clicking on shopping cart to see the items on basket
        clickOnElement(By.xpath("//span[@class=\"cart-label\"]"));
        //printing the name of the item
        System.out.println(getTextFromElement(By.xpath("//tbody/tr/td[4]/a[text()=\"Digital Storm VANQUISH 3 Custom Performance PC\"]")));
        //printing the name of the item
        System.out.println(getTextFromElement(By.xpath("//tbody/tr/td[4]/a[text()=\"Lenovo IdeaCentre 600 All-in-One PC\"]")));

    }
}

