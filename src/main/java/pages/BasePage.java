package pages;

import driver.WebDriverClient;



public class BasePage {

    WebDriverClient pageWebDriver;

    public BasePage() { pageWebDriver = new  WebDriverClient();  }

    public void gotoLandingPage(String url) {
            pageWebDriver.launchURL(url);

    }

    public String getTitleOfPage() throws Exception {
        String titleOfPage = null;
        try{ titleOfPage = pageWebDriver.title(); }
        catch (Exception e) { e.printStackTrace();}
        return titleOfPage;
    }
}
