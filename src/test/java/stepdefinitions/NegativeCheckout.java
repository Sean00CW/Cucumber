package stepdefinitions;

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
import io.cucumber.java.After;


import java.time.Duration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NegativeCheckout {
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

    @Given("I am on the current home page 1")
    public void i_am_on_the_current_home_page_1() {

        WebDriverManager.chromedriver().setup();


        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");

        // Initialize the explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I click on desired product type 1")
    public void i_click_on_desired_product_type_1() {
        findAndClick(By.cssSelector("a[href=\"#\"][onclick=\"byCat('notebook')\"]"));

    }

    @And("I click on desired laptop 1")
    public void I_click_on_desired_laptop_1() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='" + "Sony vaio i5" + "']")));
        findAndClick(By.cssSelector("#tbodyid > div:nth-child(1) > div > div > h4 > a"));

    }

    @And("I click add to cart 1")
    public void i_click_add_to_cart_1() {
        findAndClick(By.cssSelector("#tbodyid > div.row > div > a"));

    }

    @And("I navigate to the basket 1")
    public void i_navigate_to_the_basket_1() {
        findAndClick(By.cssSelector("#cartur"));

    }

    @And("I navigate to place order 1")
    public void i_navigate_to_place_order_1() {
        findAndClick(By.cssSelector("#page-wrapper > div > div.col-lg-1 > button"));

    }

    @And("I enter my name 1")
    public void i_enter_my_name_1() {
        findAndType(By.cssSelector("#name"), " ");

    }

    @And("I enter my country 1")
    public void i_enter_my_country_1() {
        findAndType(By.cssSelector("#country"), " ");

    }

    @And("I enter my city 1")
    public void i_enter_my_city_1() {
        findAndType(By.cssSelector("#city"), " ");

    }

    @And("I enter my card 1")
    public void i_enter_my_card_1() {
        findAndType(By.cssSelector("#card"), " ");

    }

    @And("I enter my month 1")
    public void i_enter_my_month_1() {
        findAndType(By.cssSelector("#month"), " ");

    }

    @And("I enter my year 1")
    public void i_enter_my_year_1() {
        findAndType(By.cssSelector("#year"), " ");

    }

    @And("I click purchase 1")
    public void i_click_purchase_1() {
        findAndClick(By.cssSelector("#orderModal > div > div > div.modal-footer > button.btn.btn-primary"));

    }

    @Then("Item is purchased 1")
    public void item_is_purchased_1() {
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
