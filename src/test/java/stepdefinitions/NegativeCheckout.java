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
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;


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
    public void i_am_on_the_current_home_page() {

        WebDriverManager.chromedriver().setup();


        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");

        // Initialize the explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I click on desired product type 1")
    public void i_click_on_desired_product_type() {
        findAndClick(By.cssSelector("a[href=\"#\"][onclick=\"byCat('notebook')\"]"));

    }

    @And("I click on desired laptop 1")
    public void I_click_on_desired_laptop() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='" + "Sony vaio i5" + "']")));
        findAndClick(By.cssSelector("#tbodyid .card-title"));

    }

    @And("I click add to cart 1")
    public void i_click_add_to_cart() {
        findAndClick(By.cssSelector(".row .col-md-7 .row a[href=\"#\"].btn"));

    }

    @And("I navigate to the basket 1")
    public void i_navigate_to_the_basket() {
        findAndClick(By.cssSelector("#cartur"));

    }

    @And("I navigate to place order 1")
    public void i_navigate_to_place_order_1() {
        findAndClick(By.cssSelector("#page-wrapper .col-lg-1 .btn"));

    }

    @And("I enter invalid checkout details")
    public void i_enter_invalid_checkout_details(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            String name = row.get("name");
            String country = row.get("country");
            String city = row.get("city");
            String card = row.get("card");
            String month = row.get("month");
            String year = row.get("year");

            findAndType(By.cssSelector("#name"), name != null ? name : "");
            findAndType(By.cssSelector("#country"), country != null ? country : "");
            findAndType(By.cssSelector("#city"), city != null ? city : "");
            findAndType(By.cssSelector("#card"), card != null ? card : "");
            findAndType(By.cssSelector("#month"), month != null ? month : "");
            findAndType(By.cssSelector("#year"), year != null ? year : "");
        }
    }


    @And("I click purchase 1")
    public void i_click_purchase_1() {
        findAndClick(By.cssSelector("#orderModal .modal-content .modal-footer button:nth-child(2)"));

    }

    @Then("Item is not purchased")
    public void item_is_not_purchased_1() {
        boolean isElementPresent = isElementPresent(By.cssSelector(".sa-button-container .confirm"));
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
