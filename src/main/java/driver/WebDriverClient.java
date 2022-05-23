package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class WebDriverClient {

    final WebDriver driver;
    final long timeOut = 30;
    Actions action;
    WebElement element = null;
    String attributeValue;


    public WebDriverClient() {
        driver = DriverManager.getWebDriverClient();
    }


    public String title() { return driver.getTitle(); }


    public boolean switchToChildWindow() {
        try {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            for (String actual : handles) {
                if (!actual.equalsIgnoreCase(currentHandle)) {
                    //Switch to the opened tab
                    driver.switchTo().window(actual);
                    return true;
                  }
                }
            }
        catch (Exception e) { e.printStackTrace();  }
        return false;
    }


    public String getText(String locator) {
        attributeValue = waitForPresence(locator).getText();
        return attributeValue;
    }


    public String getLinkText(String locator) {
        attributeValue = waitForPresence(locator).getAttribute("href");
        return attributeValue;
    }


    public void launchURL(String url) {
        try {
            System.out.println("Navigating to url: " + url);
            driver.get(url);
        }
        catch (Exception e) { e.printStackTrace();  }
    }


    public void type(String locator, String value) {
        try {
            if (waitForPresence(locator).isDisplayed()) {
                driver.findElement(By.xpath(locator)).clear();
                waitForPresence(locator).sendKeys(value);
            }
        }
        catch (Exception e) { e.printStackTrace();  }
    }


    public boolean isWebElementDisplayed(String locator) {
        try {
            if (waitForPresence(locator).isDisplayed()) {  return true;  }
            }
        catch (Exception e) { e.printStackTrace();  }
        return false;
    }


    public void clickWebElementIfDisplayed(String locator) {
        try {  waitForPresence(locator).click(); }
        catch (Exception e) { e.printStackTrace();  }    }



    public WebElement waitForPresence(String locator) {
        try {
            element = new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(timeOut))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            }
        catch (Exception e) { e.printStackTrace();  }
        return element;
    }


    public WebElement scrollToVisibleElement(String locator) {
        try {
            element = new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(timeOut))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            Actions actions = new Actions(driver);
            if (element.isEnabled()) {
                actions.moveToElement(element).perform();
                }
            }
          catch (Exception e) { e.printStackTrace();  }
        return element;
    }


    public WebElement hoverOverElement(String locator) {
        try {
            element = new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(timeOut))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
            action = new Actions(driver);
            action.moveToElement(element).perform();
            }
        catch (Exception e) { e.printStackTrace();  }
        return element;
    }


    public void hoverToVisibleElementAndClick(String hoverLocator, String clickLocator) {
        try {
            List<WebElement> elements =
                    new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(timeOut))
                            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(hoverLocator)));
            action = new Actions(driver);
            elements.forEach(element -> action.moveToElement(element).perform());
            new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(timeOut))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(clickLocator))).click();
            }
        catch (Exception e) { e.printStackTrace();  }
    }


    public void clickUsingActionClass(String locator) {
        try {
            if (waitForPresence(locator).isDisplayed())
                action = new Actions(driver);
            action.click(waitForPresence(locator)).build().perform();
           }
        catch (Exception e) { e.printStackTrace();  }
    }


}
