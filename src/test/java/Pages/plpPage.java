package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class plpPage extends basePage {

    @FindBy(css = ".product-grid__product-list .product-grid-product")
    public List<WebElement> plpProduct;

    @FindBy(css = "button[data-qa-action='nav-to-cart']")
    public WebElement alisverisSepetiniGorBtn;

}
