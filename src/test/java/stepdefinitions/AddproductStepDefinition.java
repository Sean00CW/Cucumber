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

public class AddproductStepDefinition {
    WebDriver driver;
    WebDriverWait wait;

    @Given("I am on the product home page")
    public void i_am_on_the_home_page() {

        WebDriverManager.chromedriver().setup();


        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");


        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I click on product laptops")
    public void i_click_on_a_product_type() {
        WebElement laptopsCategory = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href=\"#\"][onclick=\"byCat('notebook')\"]")));
        laptopsCategory.click();
    }

    @And("I click on laptop")
    public void i_click_on_a_product() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='" + "Sony vaio i5" + "']")));
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#tbodyid > div:nth-child(1) > div > div > h4 > a")));
        productLink.click();
    }

    @And("I click add to basket")
    public void i_click_add_to_basket() {
        WebElement addToBasketButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#tbodyid > div.row > div > a")));
        addToBasketButton.click();
    }

    @And("I go to the basket")
    public void i_go_to_the_basket() {
        WebElement basketLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cartur")));
        basketLink.click();
    }

    @Then("Item is added to basket")
    public void item_is_added_to_basket() {
        WebElement laptopProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid > tr > td:nth-child(2)")));
        assertTrue("Laptop not added to cart", laptopProduct.isDisplayed());

        if (driver != null) {
            driver.quit();
        }
    }
}