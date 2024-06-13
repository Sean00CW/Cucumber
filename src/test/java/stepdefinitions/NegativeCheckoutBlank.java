package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NegativeCheckoutBlank {
    WebDriver driver;
    WebDriverWait wait;

    public void findAndType(By selector, String someText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selector));
        element.click();
        element.sendKeys(someText);

    }

    public void findAndClick(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selector));
        element.click();
    }

    @Given("I am on the current home page 2")
    public void i_am_on_the_current_home_page_2() {

        WebDriverManager.chromedriver().setup();


        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");

        // Initialize the explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I click on desired product type 2")
    public void i_click_on_desired_product_type_2() {
        findAndClick(By.cssSelector("a[href=\"#\"][onclick=\"byCat('notebook')\"]"));

    }

    @And("I click on desired laptop 2")
    public void I_click_on_desired_laptop_2() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='" + "Sony vaio i5" + "']")));
        findAndClick(By.cssSelector("#tbodyid > div:nth-child(1) > div > div > h4 > a"));

    }

    @And("I click add to cart 2")
    public void i_click_add_to_cart_2() {
        findAndClick(By.cssSelector("#tbodyid > div.row > div > a"));

    }

    @And("I navigate to the basket 2")
    public void i_navigate_to_the_basket_2() {
        findAndClick(By.cssSelector("#cartur"));

    }

    @And("I navigate to place order 2")
    public void i_navigate_to_place_order_2() {
        findAndClick(By.cssSelector("#page-wrapper > div > div.col-lg-1 > button"));

    }


    @Then("Item should not be purchased")
    public void item_should_not_be_purchased() {
        boolean isElementPresent = isElementPresent(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible > div.sa-button-container > div > button"));
        Assert.assertFalse("Could progress, should not be possible.", isElementPresent);

    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    private boolean isElementPresent(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
