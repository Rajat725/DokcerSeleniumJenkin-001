package com.qa.tests;

import com.qa.pages.LandingPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

public class LandingPageTests extends BaseTest{

    @Test
    public void validateTitleTest()
    {
        LandingPage landingPage=new LandingPage();
        Assertions.assertThat(landingPage.getTitleOfPage())
                .isEqualToIgnoringCase("Your Store");
    }
    @Test
    public void validateURLTest()
    { LandingPage landingPage=new LandingPage();
        Assertions.assertThat(landingPage.getURLOfPage())
                .isEqualToIgnoringCase("https://naveenautomationlabs.com/opencart/");}
    @Test
    public void validateFeatureProdCountTest()
    { LandingPage landingPage=new LandingPage();
        Assertions.assertThat(landingPage.getFeaturedProductCount())
                .isEqualTo(4);
    }
    @Test
    public void validateLinksTest()
    {
        LandingPage landingPage=new LandingPage();
        Assertions.assertThat(landingPage.getAllLinksStatusCodes())
               .isEmpty();
             //   landingPage.getAllLinksStatusCodes();
    }
}
