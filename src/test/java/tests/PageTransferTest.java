package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
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
import pageobject.PersonalAccountProfilePage;

public class PageTransferTest {
    WebDriver driver;
    static final String MAIN_PAIGE_URL = "https://stellarburgers.nomoreparties.site/";
    static final String LOGIN_PAIGE_URL = "https://stellarburgers.nomoreparties.site/login";
    static final String PERSONAL_ACCOUNT_PROFILE_PAIGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
static final String WRONG_PAGE_ERROR_MESSAGE = "Неверная страница";

    @Before
    public void driverSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        // Закрываю браузер
        driver.quit();
    }

    @Test
    @DisplayName("Transfer from main page to personal account before login")
    @Description("Transfer from main page to personal account before login")
    public void checkTransferFromMainPageToPersonalAccountBeforeLogin() {
        driver.get(MAIN_PAIGE_URL);
        MainPage mainPageObj = new MainPage(driver);
        mainPageObj.clickPersonalAccountButton();
        Assert.assertEquals(WRONG_PAGE_ERROR_MESSAGE, LOGIN_PAIGE_URL, driver.getCurrentUrl());
    }


    @Test
    @DisplayName("Transfer from main page to personal account after login")
    @Description("Transfer from main page to personal account after login")
    public void checkTransferFromMainPageToPersonalAccountAfterLogin() throws InterruptedException {
        driver.get(LOGIN_PAIGE_URL);
        LoginPage loginPageObj = new LoginPage(driver);
        MainPage mainPageObj = new MainPage(driver);

        loginPageObj.setFieldsToLogin("1@q.ru", "q1w2e3");
        loginPageObj.clickLoginButton();

        mainPageObj.clickPersonalAccountButton();
        Thread.sleep(2000);

        Assert.assertEquals(WRONG_PAGE_ERROR_MESSAGE, PERSONAL_ACCOUNT_PROFILE_PAIGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Transfer from personal account page to constructor by constructor button")
    @Description("Transfer from personal account page to constructor by constructor button")
    public void checkTransferFromPersonalAccountToConstructor() {
        driver.get(LOGIN_PAIGE_URL);
        LoginPage loginPageObj = new LoginPage(driver);
        MainPage mainPageObj = new MainPage(driver);
        PersonalAccountProfilePage personalAccountProfilePageObj = new PersonalAccountProfilePage(driver);

        loginPageObj.setFieldsToLogin("1@q.ru", "q1w2e3");
        loginPageObj.clickLoginButton();

        mainPageObj.clickPersonalAccountButton();

        personalAccountProfilePageObj.clickToConstructorButton();

        Assert.assertEquals(WRONG_PAGE_ERROR_MESSAGE, MAIN_PAIGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Transfer from personal account page to constructor by logo")
    @Description("Transfer from personal account page to constructor by logo")
    public void checkTransferFromPersonalAccountToConstructorByLogo() {
        driver.get(LOGIN_PAIGE_URL);
        LoginPage loginPageObj = new LoginPage(driver);
        MainPage mainPageObj = new MainPage(driver);
        PersonalAccountProfilePage personalAccountProfilePageObj = new PersonalAccountProfilePage(driver);

        loginPageObj.setFieldsToLogin("1@q.ru", "q1w2e3");
        loginPageObj.clickLoginButton();

        mainPageObj.clickPersonalAccountButton();

        personalAccountProfilePageObj.clickLogoButton();

        Assert.assertEquals(WRONG_PAGE_ERROR_MESSAGE, MAIN_PAIGE_URL, driver.getCurrentUrl());
    }


    @Test
    @DisplayName("Logout test")
    @Description("Logout test")
    public void checkCanLogout() throws InterruptedException {
        driver.get(LOGIN_PAIGE_URL);
        LoginPage loginPageObj = new LoginPage(driver);
        MainPage mainPageObj = new MainPage(driver);

        loginPageObj.setFieldsToLogin("1@q.ru", "q1w2e3");
        loginPageObj.clickLoginButton();

        mainPageObj.clickPersonalAccountButton();
        Thread.sleep(2000);

        PersonalAccountProfilePage personalAccountProfilePageObj = new PersonalAccountProfilePage(driver);
        personalAccountProfilePageObj.clickLogoutButton();
        Thread.sleep(2000);

        Assert.assertEquals(WRONG_PAGE_ERROR_MESSAGE, LOGIN_PAIGE_URL, driver.getCurrentUrl());
    }


    @Test
    @DisplayName("Change sections on main page")
    @Description("Change sections on main page")
    public void checkCanChangeSections() throws InterruptedException {
        driver.get(MAIN_PAIGE_URL);
        MainPage mainPageObj = new MainPage(driver);

        mainPageObj.clickSauceSection();
        Assert.assertTrue("Секция не выбрана",mainPageObj.SectionIsChosen("Соусы"));

        mainPageObj.clickFillingSection();
        Thread.sleep(2000);

        Assert.assertTrue("Секция не выбрана",mainPageObj.SectionIsChosen("Начинки"));

        mainPageObj.clickBunSection();
        Thread.sleep(2000);

        Assert.assertTrue("Секция не выбрана",mainPageObj.SectionIsChosen("Булки"));

    }
}
