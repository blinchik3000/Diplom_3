package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By emailInputField = By.xpath(".//fieldset[1]/div/div/input");
    private final By passwordInputField = By.xpath(".//fieldset[2]/div/div/input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmailInputField(String email) {
        driver.findElement(emailInputField).sendKeys(email);
    }

    public void setPasswordInputField(String password) {
        driver.findElement(passwordInputField).sendKeys(password);
    }

    @Step("set fields to login")
    public void setFieldsToLogin(String email, String password) {
        setEmailInputField(email);
        setPasswordInputField(password);
    }

    @Step("click login button")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
