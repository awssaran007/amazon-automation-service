package pages;



public class SamsungTVDetailsPage extends BasePage {


    String lbl_abt = "//h1[contains(text(),'About this item')]";


   public boolean isDescriptionDiplayed(){
       pageWebDriver.scrollToVisibleElement(lbl_abt);
       return pageWebDriver.waitForPresence(lbl_abt).isDisplayed();
   }

}
