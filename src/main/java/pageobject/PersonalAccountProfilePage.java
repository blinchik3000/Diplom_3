package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountProfilePage {
    private final WebDriver driver;
    private final By toConstructorButton = By.xpath(".//parent::p[text()='Конструктор']//parent::a[@class='AppHeader_header__link__3D_hX']");
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    public PersonalAccountProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("click toConstructor button")
    public void clickToConstructorButton() {
        driver.findElement(toConstructorButton).click();
    }

    @Step("click logout button")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("click logo button")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }
}
