package driver;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public enum BrowserFactory {
    CHROME {
        public ChromeOptions getOptions() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("−−incognito");
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments(START_MAXIMIZED);
            //chromeOptions.setHeadless(configuration().headless());
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            return chromeOptions;
        }

        @Override
        public WebDriver createDriver() {
            return WebDriverManager.chromedriver().capabilities(getOptions()).create();
        }

    }, FIREFOX {
        public FirefoxOptions getOptions() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments();
            firefoxOptions.addArguments("--disable-infobars");
            firefoxOptions.addArguments("--disable-notifications");
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            firefoxOptions.addArguments(START_MAXIMIZED);
            //firefoxOptions.setHeadless(configuration().headless());
            return firefoxOptions;
        }

        @Override
        public WebDriver createDriver() {
            return WebDriverManager.firefoxdriver().capabilities(getOptions()).create();
        }

    }, SAFARI {
        public SafariOptions getOptions() {
            SafariOptions safariOptions = new SafariOptions();
            safariOptions.getAutomaticInspection();
            return safariOptions;
        }

        @Override
        public WebDriver createDriver() {
            WebDriverManager.safaridriver().capabilities(getOptions()).create();
            return new SafariDriver(getOptions());


        }
    };


    private static final String START_MAXIMIZED = " --start-maximized";

    public abstract WebDriver createDriver();

    public abstract AbstractDriverOptions getOptions();


}


