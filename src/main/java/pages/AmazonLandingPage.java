package pages;



public class AmazonLandingPage extends BasePage {

    String nav_menu_ham = "//a[@id='nav-hamburger-menu']";


    public void selectHamburgerMenuItem() throws Exception {
        try {
            pageWebDriver.clickWebElementIfDisplayed(nav_menu_ham);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}
