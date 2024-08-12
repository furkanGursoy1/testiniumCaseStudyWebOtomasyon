package StepDefinitions;

import Pages.homePage;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class homePageStepDefinitions {
    homePage HomePage = new homePage();

    @Then("kullanici basarili bir sekilde anasayfada oldugunu gorur")
    public void kullanici_basarili_bir_sekilde_anasayfada_oldugunu_gorur() {
        String maviLogoText = HomePage.AnasayfaHeader.getAttribute("alt");
        System.out.println("Homepage Logo= " + maviLogoText);
        Assert.assertEquals(maviLogoText, "Mavi");
    }
}
