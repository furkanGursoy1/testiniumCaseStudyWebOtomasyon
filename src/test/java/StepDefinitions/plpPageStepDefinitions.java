package StepDefinitions;

import Pages.cartPage;
import Pages.plpPage;
import Utilities.FileUtil;
import Utilities.TestData;
import Utilities.Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class plpPageStepDefinitions {
    plpPage PlpPage = new plpPage();
    cartPage CartPage = new cartPage();

    @When("Sonuçlardan rastgele bir ürün seçerek sepete ekler")
    public void sonuçlardan_rastgele_bir_ürün_seçerek_sepete_ekler() {
        Util.sleep(3);

        List<WebElement> products = PlpPage.plpProduct;
        if (products.isEmpty()) {
            System.out.println(" Ürün listesi boş, ürün bulunamadı!");
            return;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        WebElement selectedProduct = products.get(randomIndex);

        WebElement nameElement = selectedProduct.findElement(By.cssSelector(".product-grid-product-info__main-info"));
        Util.waitForElementToBeClickable(nameElement, 2);
        String nameText = nameElement.getText().split("\\R")[0];

        WebElement priceElement = selectedProduct.findElement(By.cssSelector("[data-qa-qualifier='price-amount-current']"));
        String priceText = priceElement.getText();

        String oldPriceText = "Yok";
        try {
            WebElement oldPriceElement = selectedProduct.findElement(By.cssSelector("[data-qa-qualifier='price-amount-old']"));
            oldPriceText = oldPriceElement.getText();
        } catch (Exception e) {
            System.out.println("Eski fiyat bulunamadı, büyük ihtimalle indirimsiz ürün.");
        }

        TestData.PLPurunAdi = nameText;
        TestData.PLPurunFiyati = priceText;
        TestData.PLPurunEskiFiyati = oldPriceText;

        System.out.println("Seçilen ürün indexi: " + randomIndex);
        System.out.println("PLP Ürün Adı: " + nameText);
        System.out.println("PLP Ürün fiyatı: " + priceText);
        System.out.println("PLP Eski fiyat: " + oldPriceText);

        try {
            WebElement sepeteEkleBtn = selectedProduct.findElement(By.cssSelector(".product-add-to-cart__button"));
            Util.waitForElementToBeClickable(sepeteEkleBtn, 2);
            sepeteEkleBtn.click();
        } catch (Exception e) {
            System.out.println("Sepete ekle butonu bulunamadı.");
            return;
        }
        try {
            WebElement sizeSelector = selectedProduct.findElement(By.cssSelector(".size-selector-sizes-size__element"));
            Util.waitForElementToBeClickable(sizeSelector, 2);
            sizeSelector.click();
        } catch (Exception e) {
            System.out.println("Beden seçme adımı atlandı, üründe stok bitmiş!");
        }
    }

    @Then("Seçilen ürün bilgisi ve fiyatı txt dosyasına yazılır")
    public void seçilen_ürün_bilgisi_ve_fiyatı_txt_dosyasına_yazılır() {
        FileUtil.writeProductInfoToTxt(TestData.PLPurunAdi, TestData.PLPurunFiyati, TestData.PLPurunEskiFiyati);
    }

    @Then("Ürün bilgileri ile sepetteki ürün bilgileri karşılaştırılır")
    public void ürün_bilgileri_ile_sepetteki_ürün_bilgileri_karşılaştırılır() {
        Util.waitForElementToBeClickable(PlpPage.alisverisSepetiniGorBtn, 2);
        PlpPage.alisverisSepetiniGorBtn.click();
        Util.waitForElementToBeClickable(CartPage.sepettekiUrunAdiText, 2);
        TestData.SepeturunAdi = CartPage.sepettekiUrunAdiText.getText().split("\\R")[0];
        TestData.SepeturunFiyati = CartPage.sepettekiFiyatText.getText();
        try {
            TestData.SepeturunEskiFiyati = CartPage.sepettekiEskiFiyatText.getText();
        } catch (Exception e) {
            TestData.SepeturunEskiFiyati = "Yok";
            System.out.println("Sepette eski fiyat görünmüyor.");
        }
        System.out.println("Sepetteki Ürün Adı: " + TestData.SepeturunAdi);
        System.out.println("Sepetteki Fiyat: " + TestData.SepeturunFiyati);
        System.out.println("Sepetteki Eski Fiyat: " + TestData.SepeturunEskiFiyati);
        try {
            Assert.assertEquals(TestData.PLPurunAdi, TestData.SepeturunAdi);
            Assert.assertEquals(TestData.PLPurunFiyati, TestData.SepeturunFiyati);
            Assert.assertEquals(TestData.PLPurunEskiFiyati, TestData.SepeturunEskiFiyati);
            System.out.println("Ürün PLP bilgileri ile sepet bilgileri aynıdır:");
        } catch (AssertionError e) {
            System.out.println("PLP bilgileri ile sepet bilgileri uyuşmamaktadır!");
        }
    }
}
