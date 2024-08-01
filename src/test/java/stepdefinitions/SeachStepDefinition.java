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


public class SeachStepDefinition {
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

    @Given("I am on the home")
    public void i_am_on_the_current_home() {

        WebDriverManager.chromedriver().setup();


        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I click on product type monitor")
    public void i_click_on_product_type_monitor() {
        findAndClick(By.cssSelector("a[href=\"#\"][onclick=\"byCat('monitor')\"]"));
    }

    @And("I click on a monitor")
    public void I_click_on_a_monitor() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/div[2]/div/div/h4/a[text()='" + "ASUS Full HD" + "']")));
        findAndClick(By.cssSelector("#tbodyid > div:nth-child(2) > div > div > h4 > a"));
    }

    @Then("I should be shown the monitor")
    public void i_should_be_shown_the_monitor() {
        WebElement monitorProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/h2[text()='" + "ASUS Full HD" + "']")));
        assertTrue("Monitor not shown", monitorProduct.isDisplayed());
        if (driver != null) {
            driver.quit();
        }
    }



}
