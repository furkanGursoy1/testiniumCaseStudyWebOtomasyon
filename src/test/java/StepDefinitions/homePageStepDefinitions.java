package StepDefinitions;

import Pages.homePage;
import Utilities.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class homePageStepDefinitions {
    homePage HomePage = new homePage();

    @Given("Kullanıcı Zara ana sayfasına gider")
    public void kullanıcıZaraAnaSayfasınaGider() {
        HomePage.cerezKabulEtBtn.click();
        Assert.assertTrue(HomePage.anasayfaLogo.isDisplayed());
    }

    @Given("{string} menüsüne ve {string} butonuna tıklar")
    public void menüsüne_ve_butonuna_tıklar(String kategoriAdi, String TumunuGorBtn) {
        HomePage.menuBtn.click();
        HomePage.clickCategoryElement(kategoriAdi);
        HomePage.clickCategoryElement(TumunuGorBtn);
    }

    @Given("Excel dosyasından birinci kelimeyi alarak arama kutusuna yazar")
    public void excel_dosyasından_birinci_kelimeyi_alarak_arama_kutusuna_yazar() {
        ExcelUtil excelUtil = new ExcelUtil("Sayfa1");
        String kelime = excelUtil.getCellData(0, 0);
        System.out.println("Excel dosyasındaki birinci satır birinci sutündaki kelime = " + kelime);
        Util.sleep(2);
        HomePage.searchBoxBtn.click();
        Util.waitForElementToBeClickable(HomePage.searchBoxInput, 3);
        HomePage.searchBoxInput.sendKeys(kelime);
    }

    @When("Arama kutusunu temizler")
    public void arama_kutusunu_temizler() {
        HomePage.searchBoxInput.sendKeys(Keys.CONTROL + "a");
        HomePage.searchBoxInput.sendKeys(Keys.DELETE);
    }

    @Given("Excel dosyasından ikinci kelimeyi alarak arama kutusuna yazar")
    public void excel_dosyasından_ikinci_kelimeyi_alarak_arama_kutusuna_yazar() {
        ExcelUtil excelUtil = new ExcelUtil("Sayfa1");
        String ikinciKelime = excelUtil.getCellData(0, 1);
        System.out.println("Excel dosyasındaki birinci satır ikinci sutündaki kelime = " + ikinciKelime);
        HomePage.searchBoxInput.sendKeys(ikinciKelime);
    }

    @Given("Klavyeden Enter tuşuna basar")
    public void klavyeden_enter_tuşuna_basar() {
        HomePage.searchBoxInput.sendKeys(Keys.ENTER);
    }
}
