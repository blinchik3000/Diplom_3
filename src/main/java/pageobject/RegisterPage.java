package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;
    private final By toLoginPageButton = By.xpath(".//a[text()='Войти']");
    private final By loginInputField = By.xpath(".//fieldset[1]/div/div/input");
    private final By emailInputField = By.xpath(".//fieldset[2]/div/div/input");
    private final By passwordInputField = By.xpath(".//fieldset[3]/div/div/input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By wrongPasswordField = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setLoginInputField(String login) {
        driver.findElement(loginInputField).sendKeys(login);
    }

    public void setEmailInputField(String email) {
        driver.findElement(emailInputField).sendKeys(email);
    }

    public void setPasswordInputField(String password) {
        driver.findElement(passwordInputField).sendKeys(password);
    }

    @Step("click register button")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("set register fields")
    public void setRegisterFields(String login, String email, String password) {
        setLoginInputField(login);
        setEmailInputField(email);
        setPasswordInputField(password);
    }

    @Step("click login page button")
    public void clickLoginPageButton() {
        driver.findElement(toLoginPageButton).click();
    }

    public boolean wrongPasswordMessageIsDisplayed() {
        return driver.findElement(wrongPasswordField).isDisplayed();
    }
}
