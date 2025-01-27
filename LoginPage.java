package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By continueWithMobileButton = By.xpath("//button[.//text()[normalize-space()='Continue with Mobile']]");
    private By phoneNumberField = By.xpath("//input[@placeholder='Phone number']");
    private By continueButton = By.xpath("//div[2]//button[1]");
    private By otpInputField = By.id("input-40--0");
    private By verifyButton = By.xpath("//button[.//text()[normalize-space()='Verify']]");
    private By instituteNameText = By.xpath("//div[contains(@class, 'name') and contains(text(), 'Testing Institute')]");

    public void navigateToLoginPage(String url) {
        driver.get(url);
    }

    public void enterPhoneNumber(String phoneNumber) {
        WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(phoneNumberField));
        phoneInput.sendKeys(phoneNumber);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void enterOTP(String otp) {
        WebElement otpInput = wait.until(ExpectedConditions.elementToBeClickable(otpInputField));
        otpInput.sendKeys(otp);
    }

    public void clickVerify() {
        wait.until(ExpectedConditions.elementToBeClickable(verifyButton)).click();
    }

    public void verifyInstituteName(String expectedInstituteName) {
        String actualInstituteName = wait.until(ExpectedConditions.visibilityOfElementLocated(instituteNameText)).getText();
        Assert.assertEquals(actualInstituteName, expectedInstituteName, "Institute name verification failed!");
    }

    public void login(String url, String phoneNumber, String otp, String expectedInstituteName) {
        try {
            navigateToLoginPage(url);
            wait.until(ExpectedConditions.elementToBeClickable(continueWithMobileButton)).click();
            enterPhoneNumber(phoneNumber);
            clickContinue();
            enterOTP(otp);
            clickVerify();
            verifyInstituteName(expectedInstituteName);
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
}

