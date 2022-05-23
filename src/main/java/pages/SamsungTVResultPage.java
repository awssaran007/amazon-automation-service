package pages;

import org.testng.Assert;

public class SamsungTVResultPage extends BasePage {




    String dd_sort_results = "//span[@class='a-button-text a-declarative']";
    String dd_lst_option = "//span[@class='a-button-inner']";
    String dd_srted_high_to_low = "//a[@id='s-result-sort-select_2']";
    String prmpt_hhg_to_low = "//span[@class='a-dropdown-prompt']";

    String indx_second_result = "//div[@data-cel-widget='search_result_2']";
    String txt_highest_price = "//div[@data-cel-widget='search_result_1']//child::span[@class='a-price-whole']";
    String txt_secnd_highest_price = "//div[@data-cel-widget='search_result_2']//child::span[@class='a-price-whole']";
    String txt_second_result = "//div[@data-cel-widget='search_result_2']//child::a[@class='a-link-normal s-no-outline']";
    String lbl_abt = "//h1[contains(text(),'About this item')]";

    public String readResultElement(){
       return pageWebDriver.getLinkText(txt_second_result);
    }


    public String sortResultPriceHighToLow() throws Exception {

        pageWebDriver.clickWebElementIfDisplayed(dd_sort_results);
        pageWebDriver.clickWebElementIfDisplayed(dd_lst_option);
        pageWebDriver.clickWebElementIfDisplayed(dd_srted_high_to_low);
        return  pageWebDriver.getText(prmpt_hhg_to_low);

    }

    public boolean compareTopTwoTVPrices(){
        int highestPrice = Integer.parseInt(pageWebDriver.getText(txt_highest_price).replaceAll(",",""));
        int secondHighetPrice = Integer.parseInt(pageWebDriver.getText(txt_secnd_highest_price).replaceAll(",",""));
        return highestPrice>secondHighetPrice?true:false;
    }


   public void selectSecondTVResult(){
       pageWebDriver.clickWebElementIfDisplayed(indx_second_result);
       pageWebDriver.switchToChildWindow();
       pageWebDriver.scrollToVisibleElement(lbl_abt);

   }

}
