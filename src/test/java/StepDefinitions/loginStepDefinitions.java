package StepDefinitions;

import Pages.loginPage;
import io.cucumber.java.en.When;

public class loginStepDefinitions {

    loginPage LoginPage = new loginPage();

    @When("Kullanıcı login olur")
    public void kullanıcı_login_olur() {
        LoginPage.successLogin();
    }
}
