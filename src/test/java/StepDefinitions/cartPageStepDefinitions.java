package StepDefinitions;

import Pages.cartPage;
import Utilities.Util;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class cartPageStepDefinitions {
    cartPage CartPage = new cartPage();

    @Then("Ürün adedi {int} yapılır ve doğruluğu doğrulanır")
    public void ürün_adedi_yapılır_ve_doğruluğu_doğrulanır(Integer sepettUrunMiktari) {
        for (int i = 1; i < sepettUrunMiktari; i++) {
            Util.waitForElementToBeClickable(CartPage.sepettekiUrunArtirBtn, 5);
            Util.scrollToElementAndclick(CartPage.sepettekiUrunArtirBtn);
            Util.sleep(1);
        }
        String sepettekiUrunAdediStr = CartPage.sepetUrunMiktari.getAttribute("value");
        System.out.println("Sepetteki ürün adedi: " + sepettekiUrunAdediStr);
        int sepettekiUrunAdedi = Integer.parseInt(sepettekiUrunAdediStr);
        String sepetBaslikText = CartPage.sepetBaslikUrunMiktari.getText(); // Örn: "Sepet [2]"
        String sepetAdedi = sepetBaslikText.replaceAll("[^0-9]", "");
        System.out.println("Sepet başlığındaki ürün sayısı: " + sepetAdedi);
        int sepetBaslikUrunAdedi = Integer.parseInt(sepetAdedi);
        Assert.assertEquals(" Ürün adedi input değeri beklendiği gibi değil", sepettUrunMiktari.intValue(), sepettekiUrunAdedi);
        Assert.assertEquals("Sepet başlığındaki ürün sayısı beklendiği gibi değil", sepettUrunMiktari.intValue(), sepetBaslikUrunAdedi);
        System.out.println("Ürün adedi ve sepet başlığı bilgileri eşleşiyor.");
    }

    @Then("Ürün sepetten silinir ve sepetin boş olduğu kontrol edilir")
    public void ürün_sepetten_silinir_ve_sepetin_boş_olduğu_kontrol_edilir() {
        Util.scrollToElementAndclick(CartPage.sepetteUrunSilBtn);
        Assert.assertEquals("SEPETİNİZ BOŞ", CartPage.sepetBosText.getText());
        System.out.println("Sepet Boşaltılmıştır!");
    }
}
