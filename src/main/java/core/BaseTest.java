package core;

import data.DataFactory;
import driver.BrowserFactory;
import driver.DriverManager;
import exceptions.BroswerInfoNotValidException;
import exceptions.RunProvisionNotValidException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import utils.ReadYaml;

import java.net.MalformedURLException;
import java.net.URL;


@Listeners(utils.TestListenerReporter.class)

public class BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    public static ReadYaml rx;
    public Controller pageController = null;
    protected WebDriver initialDriverInstance = null;
    DataFactory dataFactory;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    //what if i want to send using CLI
    @Parameters({"env", "browser", "runProvision", "gotoUrl"})
    @BeforeTest(alwaysRun = true)
    public void setUp(@Optional String env, @Optional String browser, @Optional String runProvision, @Optional String gotoUrl) throws Exception {

        runProvisionSetup(env, browser, runProvision, gotoUrl);

    }


    @AfterTest()
    public void postCondition() {
        DriverManager.removeDriver();
    }



    //initiate PAGE controller
    void initiateController(String gotoUrl, String env) throws Exception {
        pageController = new Controller();
        rx = new ReadYaml(env);
        dataFactory = new DataFactory(gotoUrl);
        pageController.gotoLandingPage(dataFactory.getUrl());
    }


    //setup run provision to local or remote
    private void runProvisionSetup(String env, String browser, String runProvision, String gotoUrl) throws Exception {
        RunProvision provision = RunProvision.valueOf(runProvision.toUpperCase());

        switch(provision) {
            case REMOTE:
                try {
                    System.out.println("running remote");
                    capabilities.setBrowserName(browser);
                    capabilities.setPlatform(Platform.LINUX);
                    String hubUrl = "http://localhost:4444/wd/hub";

                    initialDriverInstance = new RemoteWebDriver(new URL(hubUrl), capabilities);
                    DriverManager.setDriverManager(initialDriverInstance);
                    logger.info(DriverManager.getInfo());
                    initiateController(gotoUrl, env);
                }
                catch (MalformedURLException e) {
                    logger.error("Grid URL is invalid or Grid is not available");
                    logger.error(String.format("Browser: %s", capabilities.getBrowserName()), e);
                }
                catch (BroswerInfoNotValidException e) {
                    throw new BroswerInfoNotValidException(browser);
                }

                break;
            case LOCAL:
                browserSetup(browser);
                initiateController(gotoUrl, env);

                break;
            default:
                throw new RunProvisionNotValidException(runProvision);
        }
    }


    //setup browser
    void browserSetup(String browser) {
        //create object of Browsers Enum
        BrowserFactory br = BrowserFactory.valueOf(browser.toUpperCase());
        switch(br) {
            case CHROME:
            case FIREFOX:
            case SAFARI:
                initialDriverInstance = br.createDriver();
                break;

            default:
                logger.info("Incorrect browser value provided");
        }

        DriverManager.setDriverManager(initialDriverInstance);
        logger.info(DriverManager.getInfo());
    }

    enum RunProvision {LOCAL, REMOTE}

}
