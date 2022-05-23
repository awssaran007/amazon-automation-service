package driver;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


//This class operates on the driver instance
public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static void setDriverManager(WebDriver driver)  {
       try{ DriverManager.driver.set(driver);
           System.out.println(driver.getCurrentUrl());}
       catch (Exception e) {e.printStackTrace(); } }


    public static WebDriver getWebDriverClient() { return driver.get(); }


    public static String getInfo() {
        Capabilities cap = ((RemoteWebDriver) DriverManager.getWebDriverClient()).getCapabilities();
        String browserName = cap.getBrowserName();
        String platform = cap.getPlatformName().toString();
        String version = cap.getBrowserVersion();
        return String.format("browser: %s v: %s platform: %s", browserName, version, platform);
    }

    public static void removeDriver(){
            DriverManager.driver.get().quit();
            driver.remove();
        }
    }

