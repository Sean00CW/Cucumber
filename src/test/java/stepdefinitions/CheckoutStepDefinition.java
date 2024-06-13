package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class CheckoutStepDefinition {
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

    @Given("I am on the current home page")
    public void i_am_on_the_current_home_page() {

        WebDriverManager.chromedriver().setup();


        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");

        // Initialize the explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I click on desired product type")
    public void i_click_on_desired_product_type() {
        findAndClick(By.cssSelector("a[href=\"#\"][onclick=\"byCat('notebook')\"]"));

    }

    @And("I click on desired laptop")
    public void I_click_on_desired_laptop() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='" + "Sony vaio i5" + "']")));
        findAndClick(By.cssSelector("#tbodyid > div:nth-child(1) > div > div > h4 > a"));

    }

    @And("I click add to cart")
    public void i_click_add_to_cart() {
        findAndClick(By.cssSelector("#tbodyid > div.row > div > a"));

    }

    @And("I navigate to the basket")
    public void i_navigate_to_the_basket() {
        findAndClick(By.cssSelector("#cartur"));

    }

    @And("I navigate to place order")
    public void i_navigate_to_place_order() {
        findAndClick(By.cssSelector("#page-wrapper > div > div.col-lg-1 > button"));

    }

    @And("I enter my name")
    public void i_enter_my_name() {
        findAndType(By.cssSelector("#name"), "Sean");

    }

    @And("I enter my country")
    public void i_enter_my_country() {
        findAndType(By.cssSelector("#country"), "United Kingdom");

    }

    @And("I enter my city")
    public void i_enter_my_city() {
        findAndType(By.cssSelector("#city"), "Peterborough");

    }

    @And("I enter my card")
    public void i_enter_my_card() {
        findAndType(By.cssSelector("#card"), "1111111111111111");

    }

    @And("I enter my month")
    public void i_enter_my_month() {
        findAndType(By.cssSelector("#month"), "08");

    }

    @And("I enter my year")
    public void i_enter_my_year() {
        findAndType(By.cssSelector("#year"), "28");

    }

    @And("I click purchase")
    public void i_click_purchase() {
        findAndClick(By.cssSelector("#orderModal > div > div > div.modal-footer > button.btn.btn-primary"));

    }

    @Then("Item is purchased")
    public void item_is_purchased() {
        WebElement laptopProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible > div.sa-button-container > div > button")));
        assertTrue("Item not purchased", laptopProduct.isDisplayed());

        if (driver != null) {
            driver.quit();
        }
    }
}