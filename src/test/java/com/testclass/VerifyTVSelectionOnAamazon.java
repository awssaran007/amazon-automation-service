package com.testclass;
import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;



public class VerifyTVSelectionOnAamazon extends BaseTest   {


   @Test( priority = 1)
    public void test001_GotoAmazonlandingPage() throws Exception {
       assertThat(pageController.AmazonLandingPage().getTitleOfPage()).isNotEmpty().contains("Amazon.in");
   }


    @Test(priority = 2)
    public void test002_VerifyAbilityToSelect() throws Exception {
        pageController.AmazonLandingPage().selectHamburgerMenuItem();
        pageController.SideMenuPage().selectHeaderMenuItem(rx.readConfig().get("HDR_MENU_ITEM_SHOP"));
        pageController.SideMenuPage().selectMenuItem(rx.readConfig().get("MENU_ITEM_SHOP"));
        pageController.SideMenuPage().selectCommodityType("Televisions");
        pageController.SideMenuPage().selectCommodityBrand("Samsung");
        assertThat( pageController.SamsungTVResultPage().readResultElement().toLowerCase().contains("samsung"));
        assertThat(pageController.SamsungTVResultPage().sortResultPriceHighToLow().toLowerCase().contains("price high to low"));
        assertThat(pageController.SamsungTVResultPage().compareTopTwoTVPrices()).isTrue();
        pageController.SamsungTVResultPage().selectSecondTVResult();
        assertThat(pageController.SamsungTVDetailsPage().isDescriptionDiplayed()).isTrue();

    }




}

