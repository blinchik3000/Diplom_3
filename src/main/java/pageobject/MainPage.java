package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    By toPersonalAccountButton = By.xpath(".//a[@href='/account']");
    By toLogonPageButton = By.xpath(".//button[text()='Войти в аккаунт']");
    By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    By bunSection = By.xpath(".//parent::span[text()='Булки']//parent::div");
    By sauceSection = By.xpath(".//parent::span[text()='Соусы']//parent::div");
    By fillingSection = By.xpath(".//parent::span[text()='Начинки']//parent::div");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("click personal account button")
    public void clickPersonalAccountButton() {
        driver.findElement(toPersonalAccountButton).click();
    }

    @Step("click login button")
    public void clickLoginButton() {
        driver.findElement(toLogonPageButton).click();
    }

    @Step("click bun section")
    public void clickBunSection() {
        driver.findElement(bunSection).click();
    }

    @Step("click sauce section")
    public void clickSauceSection() {
        driver.findElement(sauceSection).click();
    }

    @Step("click filling section")
    public void clickFillingSection() {
        driver.findElement(fillingSection).click();
    }

    public boolean makeOrderButtonIsDisplayed() {
        return driver.findElement(makeOrderButton).isDisplayed();
    }

    public boolean SectionIsChosen(String sectionName) {
        String className;
        switch (sectionName) {
            case "Булки":
                className = driver.findElement(bunSection).getAttribute("class");
                break;
            case "Соусы":
                className = driver.findElement(sauceSection).getAttribute("class");
                break;
            case "Начинки":
                className = driver.findElement(fillingSection).getAttribute("class");
                break;
            default:
                className = "no class name";
        }
        return className.equals("tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
    }


}
