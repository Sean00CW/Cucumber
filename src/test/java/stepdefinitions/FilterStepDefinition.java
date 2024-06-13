package stepdefinitions;

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

public class FilterStepDefinition {

    WebDriver driver;

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        // Use WebDriverManager to manage the ChromeDriver binary
        WebDriverManager.chromedriver().setup();

        // Initialize the Chrome driver
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/ "); // Replace with the actual URL
    }

    @When("I click on a product type")
    public void i_click_on_a_product_type() {
        driver.findElement(By.cssSelector("a[href=\"#\"][onclick=\"byCat('notebook')\"]")).click();
    }

    @Then("I should be redirected to that product type")
    public void i_should_be_redirected_to_that_product_type() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        WebElement laptopProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid > div:nth-child(2) > div > div > h4 > a")));
        assertTrue("Laptop products are not displayed", laptopProduct.isDisplayed());
        driver.quit();
    }
}