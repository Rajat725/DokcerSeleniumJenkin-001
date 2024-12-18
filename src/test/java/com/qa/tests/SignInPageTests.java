package com.qa.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.constants.FrameworkConstants;
import com.qa.pages.LandingPage;
import com.qa.pages.SignInPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class SignInPageTests extends BaseTest{
    @Test
    public void invalidLoginCredTest()
    {
        ObjectMapper objectMapper=new ObjectMapper();
        InputStream testDataStream = FrameworkConstants.getTestDataStream();
        final HashMap<String, String> hashMap;
        try {
            hashMap = objectMapper.readValue(testDataStream, new TypeReference<HashMap<String, String>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LandingPage landingPage=new LandingPage();
        SignInPage signInPage = landingPage.navigateToSignInPage();
       signInPage.enterUserEmail(hashMap.get("email"));
       signInPage.enterPassword(hashMap.get("password"));
        signInPage.clickLoginbtn();
        Assertions.assertThat(signInPage.getErrorMessage()).contains("Warning: No match for E-Mail Address and/or Password.");
    }
}
