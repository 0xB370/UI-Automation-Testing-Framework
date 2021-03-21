package StepDefinitions;

import Functions.CreateDriver;
import Functions.SeleniumFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StepDefinitions {
    WebDriver driver;
    SeleniumFunctions functions = new SeleniumFunctions();

    /******** Log Attribute ********/
    Logger log = Logger.getLogger(StepDefinitions.class);

    public StepDefinitions() {
        driver = Hooks.driver;
    }

    @Given("^I am in App main site")
    public void iAmInAppMainSite() throws IOException {
        String url = functions.readProperties("MainAppUrlBase");
        log.info("Navigate to: " + url);
        driver.get(url);
    }

    @Given("^I navigate to (.*)$")
    public void navigateTo(String url) {
        log.info("Navigate to: " + url);
        driver.get(url);
    }

    @Then("^I load the DOM Information (.*)$")
    public void iLoadTheDOMInformation(String file) throws Exception {
        SeleniumFunctions.FileName = file;
        SeleniumFunctions.readJson();
        log.info("initialize file: " + file );
    }

    @And("I click on element (.*)")
    public void iClickOnElement(String locator) throws Exception {
        By SeleniumElem = SeleniumFunctions.getCompleteElement(locator);
        driver.findElement(SeleniumElem).click();
        log.info("Element " + locator + " clicked.");
    }

    @And("^I set (.*) with text (.*)$")
    public void iSetWithText(String element, String text) throws Exception {
        By SeleniumElem = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElem).sendKeys(text);
        log.info("Sended " + text + "to element " + element);
    }

    @Given("I set (.*) value in Data Scenario")
    public void iSetUserEmailValueInDataScenario(String parameter) throws IOException {
        functions.RetrieveTestData(parameter);
    }

    @And("^I Save text of (.*?) as Scenario Context$")
    public void iSaveTextOfElementAsScenarioContext(String element) throws Exception {
        By SeleniumElem = SeleniumFunctions.getCompleteElement(element);
        String ScenarioElementText = driver.findElement(SeleniumElem).getText();
        functions.SaveInScenario(element+".text", ScenarioElementText);
    }

    @And("^I set (.*?) with key value (.*?)$")
    public void iSetWithKeyValue(String element, String key) throws Exception {
        functions.iSetElementWithKeyValue(element, key);
    }
}