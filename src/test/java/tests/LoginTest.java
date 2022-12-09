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
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.ForgotPasswordPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;


public class LoginTest {
    WebDriver driver;
    static final String REGISTER_PAIGE_URL = "https://stellarburgers.nomoreparties.site/register";
    static final String LOGIN_PAIGE_URL = "https://stellarburgers.nomoreparties.site/login";
    static final String MAIN_PAIGE_URL = "https://stellarburgers.nomoreparties.site";
    static final String FORGOT_PASSWORD_PAIGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    RegisterPage registerPageObj;
    static CredentialsGenerator generator;


    @Before
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedrivery.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\poles\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);

        driver.get(REGISTER_PAIGE_URL);
        registerPageObj = new RegisterPage(driver);
        generator = new CredentialsGenerator();
        registerPageObj.setRegisterFields(generator.getRandomName(), generator.getRandomEmail(), generator.getRandomPassword());
        registerPageObj.clickRegisterButton();
    }

    @After
    public void teardown() {
        // Закрываю браузер
        driver.quit();
    }


    @Test
    @DisplayName("Base login test")
    @Description("Base login test")
    public void checkCanLogin() throws InterruptedException {
        driver.get(LOGIN_PAIGE_URL);
        LoginPage loginPageObj = new LoginPage(driver);
        MainPage mainPageObj = new MainPage(driver);

        loginPageObj.setFieldsToLogin(generator.getRandomEmail(), generator.getRandomPassword());
        loginPageObj.clickLoginButton();
        Thread.sleep(2000);

        Assert.assertTrue(mainPageObj.makeOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Login by enter account button from main page")
    @Description("Login by enter account button from main page")
    public void canLoginByEnterAccountButtonFromMainPage() throws InterruptedException {
        driver.get(MAIN_PAIGE_URL);
        MainPage mainPageObj = new MainPage(driver);
        LoginPage loginPageObj = new LoginPage(driver);

        mainPageObj.clickLoginButton();

        loginPageObj.setFieldsToLogin(generator.getRandomEmail(), generator.getRandomPassword());
        loginPageObj.clickLoginButton();
        Thread.sleep(2000);

        Assert.assertTrue(mainPageObj.makeOrderButtonIsDisplayed());
    }


    @Test
    @Description("Login by personal account button from main page")
    @DisplayName("Login by personal account button from main page")
    public void canLoginByPersonalAccountButtonFromMainPage() throws InterruptedException {
        driver.get(MAIN_PAIGE_URL);
        MainPage mainPageObj = new MainPage(driver);
        LoginPage loginPageObj = new LoginPage(driver);

        mainPageObj.clickPersonalAccountButton();

        loginPageObj.setFieldsToLogin(generator.getRandomEmail(), generator.getRandomPassword());
        loginPageObj.clickLoginButton();
        Thread.sleep(2000);

        Assert.assertTrue(mainPageObj.makeOrderButtonIsDisplayed());
    }


    @Test
    @DisplayName("Login from register page")
    @Description("Login from register page")
    public void canLoginByLoginButtonFromRegisterPage() throws InterruptedException {
        driver.get(REGISTER_PAIGE_URL);
        MainPage mainPageObj = new MainPage(driver);
        LoginPage loginPageObj = new LoginPage(driver);

        registerPageObj.clickLoginPageButton();

        loginPageObj.setFieldsToLogin(generator.getRandomEmail(), generator.getRandomPassword());
        loginPageObj.clickLoginButton();
        Thread.sleep(2000);

        Assert.assertTrue(mainPageObj.makeOrderButtonIsDisplayed());
    }


    @Test
    @DisplayName("Login from forgot password page")
    @Description("Login from forgot password page")
    public void canLoginByLoginButtonFromForgotPasswordPage() throws InterruptedException {
        driver.get(FORGOT_PASSWORD_PAIGE_URL);
        ForgotPasswordPage forgotPasswordPageObj = new ForgotPasswordPage(driver);
        MainPage mainPageObj = new MainPage(driver);
        LoginPage loginPageObj = new LoginPage(driver);

        forgotPasswordPageObj.clickLoginPageButton();

        loginPageObj.setFieldsToLogin(generator.getRandomEmail(), generator.getRandomPassword());
        loginPageObj.clickLoginButton();
        Thread.sleep(2000);

        Assert.assertTrue(mainPageObj.makeOrderButtonIsDisplayed());
    }


}
