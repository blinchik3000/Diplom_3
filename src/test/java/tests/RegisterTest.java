package tests;

import common.CredentialsGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;

public class RegisterTest {
    static WebDriver driver;
    static final String REGISTER_PAIGE_URL = "https://stellarburgers.nomoreparties.site/register";
    static final String LOGIN_PAIGE_URL = "https://stellarburgers.nomoreparties.site/login";
    RegisterPage registerPageObj;
    CredentialsGenerator generator;

    @Before
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriverc.exe");
        driver = new ChromeDriver();
        driver.get(REGISTER_PAIGE_URL);
        registerPageObj = new RegisterPage(driver);
        generator = new CredentialsGenerator();
    }

    @After
    public void teardown() {
        // Закрываю браузер
        driver.quit();
    }


    @Test
    @DisplayName("checking for succes registration with correct credentials")
    @Description("checking for succes registration with correct credentials")
    public void checkCanRegisterWithCorrectCredentials() throws InterruptedException {
        LoginPage loginPageObj = new LoginPage(driver);
        MainPage mainPageObj = new MainPage(driver);

        registerPageObj.setRegisterFields(generator.getRandomName(), generator.getRandomEmail(), generator.getRandomPassword());
        registerPageObj.clickRegisterButton();
        Thread.sleep(2000);
        Assert.assertEquals("", LOGIN_PAIGE_URL, driver.getCurrentUrl());

        loginPageObj.setFieldsToLogin(generator.getRandomEmail(), generator.getRandomPassword());
        loginPageObj.clickLoginButton();
        Thread.sleep(2000);

        Assert.assertTrue(mainPageObj.makeOrderButtonIsDisplayed());
    }


    @Test
    @DisplayName("checking for succes registration with password <6")
    @Description("checking for succes registration with password <6")
    public void checkCanRegisterWithSmallPassword() {
        generator.setRandomPassword("12345");
        registerPageObj.setRegisterFields(generator.getRandomName(), generator.getRandomEmail(), generator.getRandomPassword());
        registerPageObj.clickRegisterButton();
        Assert.assertTrue(registerPageObj.wrongPasswordMessageIsDisplayed());
    }
}
