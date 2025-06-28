package Pages;

import Utilities.ConfigurationReader;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends basePage {
    @FindBy(css = ".layout-header-action-account")
    public WebElement homePageGirisYapBtn;

    @FindBy(css = "button[data-qa-id='oauth-logon-button']")
    public WebElement loginSayfaGirisYapBtn;

    @FindBy(id = "zds-:r5:")
    public WebElement emailInputBox;

    @FindBy(id = "zds-:r8:")
    public WebElement passwordInputBox;

    @FindBy(css = "button[data-qa-id='logon-form-submit']")
    public WebElement girisYapBtn;

    @FindBy(css = "[data-qa-id='layout-header-user-account']")
    public WebElement kullanıcıAdı;

    public void successLogin() {
        homePageGirisYapBtn.click();
        loginSayfaGirisYapBtn.click();
        emailInputBox.click();
        emailInputBox.sendKeys(ConfigurationReader.get("email"));
        passwordInputBox.click();
        passwordInputBox.sendKeys(ConfigurationReader.get("password"));
        girisYapBtn.click();
        System.out.println("Kullanici Adi = " + kullanıcıAdı.getText());
        Assert.assertEquals(kullanıcıAdı.getText(), ConfigurationReader.get("kullaniciAdi"));
    }

}
