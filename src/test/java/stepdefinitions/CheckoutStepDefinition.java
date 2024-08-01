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
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;

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
        findAndClick(By.cssSelector(".row .col-lg-3 .list-group a.list-group-item[onclick=\"byCat('notebook')\"]"));

    }

    @And("I click on desired laptop")
    public void I_click_on_desired_laptop() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='" + "Sony vaio i5" + "']")));
        findAndClick(By.cssSelector(".card-title a.hrefch"));

    }

    @And("I click add to cart")
    public void i_click_add_to_cart() {
        findAndClick(By.cssSelector("a.btn.btn-success.btn-lg"));

    }

    @And("I navigate to the basket")
    public void i_navigate_to_the_basket() {
        findAndClick(By.cssSelector("#cartur"));

    }

    @And("I navigate to place order")
    public void i_navigate_to_place_order() {
        findAndClick(By.cssSelector(".col-lg-1 button.btn.btn-success"));

    }

    @And("I enter the following details")
    public void i_enter_the_following_details(DataTable dataTable) {
        List<Map<String, String>> details = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> detail : details) {
            String name = detail.get("Name");
            String country = detail.get("Country");
            String city = detail.get("City");
            String cardNumber = detail.get("Card Number");
            String expiryMonth = detail.get("Expiry Month");
            String expiryYear = detail.get("Expiry Year");

            findAndType(By.cssSelector("#name"), name);
            findAndType(By.cssSelector("#country"), country);
            findAndType(By.cssSelector("#city"), city);
            findAndType(By.cssSelector("#card"), cardNumber);
            findAndType(By.cssSelector("#month"), expiryMonth);
            findAndType(By.cssSelector("#year"), expiryYear);
        }
    }

    @And("I click purchase")
    public void i_click_purchase() {
        findAndClick(By.cssSelector("#orderModal .modal-footer .btn.btn-primary"));

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