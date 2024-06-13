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

public class ProductSpecStepDefinition {
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

    @Given("I am on the product page")
    public void i_am_on_the_product_page() {

        WebDriverManager.chromedriver().setup();


        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/ ");
    }

    @When("I click on a product")
    public void i_click_on_a_product() {
        findAndClick(By.cssSelector("#tbodyid > div:nth-child(1) > div > div > h4 > a"));
    }

    @Then("Product specification displayed")
    public void product_specification_displayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        WebElement Product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid > div.row > div > a")));
        assertTrue("Item Specification not shown", Product.isDisplayed());
        driver.quit();
    }
}
