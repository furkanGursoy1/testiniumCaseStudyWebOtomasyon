package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class cartPage extends basePage {

    @FindBy(css = ".shop-cart-item__info .shop-cart-item-header")
    public WebElement sepettekiUrunAdiText;

    @FindBy(css = ".shop-cart-item__info .shop-cart-item-pricing__old")
    public WebElement sepettekiEskiFiyatText;

    @FindBy(css = ".shop-cart-item__info .shop-cart-item-pricing__current")
    public WebElement sepettekiFiyatText;

    @FindBy(css = "[data-qa-id='add-order-item-unit']")
    public WebElement sepettekiUrunArtirBtn;

    @FindBy(css = "input.shop-cart-item-quantity")
    public WebElement sepetUrunMiktari;

    @FindBy(xpath = "//a[contains(@data-qa-action, 'Shopping_bag')]")
    public WebElement sepetBaslikUrunMiktari;

    @FindBy(css = "button[data-qa-action='remove-order-item']")
    public WebElement sepetteUrunSilBtn;

    @FindBy(css = ".zds-empty-state__title")
    public WebElement sepetBosText;

}
