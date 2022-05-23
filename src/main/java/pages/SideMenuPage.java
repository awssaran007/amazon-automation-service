package pages;

import org.testng.Assert;

public class SideMenuPage extends BasePage {


    String hdr_menu_items = "//div[@class='hmenu-item hmenu-title ' and contains(text(),'";
    String menu_items = "//a[@class='hmenu-item']/div[contains(text(),'";
    String menu_item_tv = "//a[@class='hmenu-item' and contains(text(),'";
    String chkbox_brand_item = "//*[text()='Brands']/parent::div/following-sibling::ul//span[text()='";
    String txt_second_result = "//div[@data-cel-widget='search_result_2']//child::a[@class='a-link-normal s-no-outline']";



    public void selectHeaderMenuItem(String menu) throws Exception {
        try {

            pageWebDriver.clickWebElementIfDisplayed(hdr_menu_items + menu.toLowerCase() + "')]");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void selectMenuItem(String menu) throws Exception {
        try {
            pageWebDriver.clickWebElementIfDisplayed(menu_items + menu + "')]");
            pageWebDriver.clickWebElementIfDisplayed(menu_items + menu + "')]/following-sibling::i");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void selectCommodityType(String menu) throws Exception {
        try {

            pageWebDriver.waitForPresence(menu_item_tv + menu + "')]").click();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String selectCommodityBrand(String brand) throws Exception {
        try {
            String chkbox_brand =chkbox_brand_item +brand+"']/parent::a" ;
            if (pageWebDriver.waitForPresence(chkbox_brand).isEnabled()) {
                pageWebDriver.scrollToVisibleElement(chkbox_brand);
        }

               if( pageWebDriver.scrollToVisibleElement(chkbox_brand).isDisplayed())
                pageWebDriver.clickUsingActionClass(chkbox_brand);

        }
        catch (Exception e) {
            e.printStackTrace();

        }

        return pageWebDriver.getLinkText(txt_second_result);
    }



}
