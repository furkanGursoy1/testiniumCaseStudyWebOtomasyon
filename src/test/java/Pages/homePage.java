package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage{

    @FindBy(css="img[alt='Mavi']")
    public WebElement AnasayfaHeader;

}
