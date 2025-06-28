package Pages;

import Utilities.Driver;
import Utilities.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage {

    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement cerezKabulEtBtn;

    @FindBy(css = ".layout-catalog-logo")
    public WebElement anasayfaLogo;

    @FindBy(css = "[data-qa-id='layout-header-toggle-menu']")
    public WebElement menuBtn;

    @FindBy(css = ".layout-header-action-search")
    public WebElement searchBoxBtn;

    @FindBy(css = "#search-home-form-combo-input")
    public WebElement searchBoxInput;

    public void clickCategoryElement(String kategoriAdi) {
        String kategoriLocater = String.format("//span[@class='layout-categories-category__name' and normalize-space(text())='%s']", kategoriAdi);
        WebElement kategoriElementi = Driver.get().findElement(By.xpath(kategoriLocater));
        Util.sleep(2);
        kategoriElementi.click();
    }
}
