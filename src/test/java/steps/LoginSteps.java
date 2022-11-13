package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class LoginSteps {
    WebDriver driver;

    @Given("Navigate to Page Phone Book")
    public void navigateToLoginPage(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("Click on Login Tab")
    public void clickOnLoginTab(){
        click(By.xpath("//a[.='LOGIN']"));
    }

    @And("Enter the valid data")
    public void enterTheValidData(){
        type(By.cssSelector("[placeholder='Email']"), "monketester13@gmail.com");
        type(By.cssSelector("[placeholder='Password']"), "1q2W3e4R_");
    }

    @And("Click on Add Button")
    public void clickOnAddButton(){
        click(By.xpath("//a[contains(.,'ADD')]"));
    }

    @And("Enter Contact Data")
    public void enterContactData(DataTable table){
        List<Map<String, String>> dataTable = table.asMaps();
        String name = dataTable.get(0).get("name");
        String lastname = dataTable.get(0).get("lastname");
        String phone = dataTable.get(0).get("phone");
        String email = dataTable.get(0).get("email");
        String city = dataTable.get(0).get("city");
        String comment = dataTable.get(0).get("comment");
        type(By.cssSelector("input:nth-child(1)"), name);
        type(By.cssSelector("input:nth-child(2)"), lastname);
        type(By.cssSelector("input:nth-child(3)"), phone);
        type(By.cssSelector("input:nth-child(4)"), email);
        type(By.cssSelector("input:nth-child(5)"), city);
        type(By.cssSelector("input:nth-child(6)"), comment);
        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
    }




    @And("Enter a valid email and an invalid password")
    public void enterInvalidPassword(DataTable table){
        List<Map<String, String>> dataTable = table.asMaps();
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");
        type(By.cssSelector("[placeholder='Email']"), email);
        type(By.cssSelector("[placeholder='Password']"), password);
    }

    @And("Click on Login Button")
    public void clickOnTheLoginButton(){
        click(By.xpath("//button[contains(.,'Login')]"));
    }

    @Then("Alert appeared")
    public void isAlertAppeared(){
        Assert.assertTrue(isAlertPresent());
    }

    @Then("Sign Out Button displayed")
    public void isSignOutButtonPresent(){
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
    }

    @Then("Appears LoginRegistration Form")
    public void isLoginFormPresent(){
        Assert.assertTrue(isElementPresent(By.cssSelector(".login_login__3EHKB")));
    }

    @Then("Contact appeared in List")
    public void isContactAppeared(){
        Assert.assertTrue(sizeofContacts()>0);
    }
    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size()>0;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if(text!= null){
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, 20).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert();
            alert.accept();
            return true;
        }
    }

    public void pause(int value) {
        try {
            sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickWithAction(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
        element.click();
    }
    public int sizeofContacts() {
        return Math.max(driver.findElements(By.cssSelector("div.contact-item_card__2SOIM")).size(), 0);
    }

}
