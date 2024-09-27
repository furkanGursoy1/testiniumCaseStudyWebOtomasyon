package Utilities;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class Util {

    public static void sleep(int second) {
/* * Belirtilen saniye kadar bekler.
// *
// * @param second bekleme süresi (saniye cinsinden)
// */
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static WebElement waitForVisibilityOfElement(WebElement element, int timeToWaitInSec) {
        /**
         * Bir WebElement'in görünür olmasını bekler.
         *
         * @param element Beklenecek WebElement
         * @param timeToWaitInSec Bekleme süresi (saniye cinsinden)
         * @return Görünür olan WebElement
         */
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementToBeClickable(WebElement element, int timeToWaitInSec) {
        /**
         * Bir WebElement'in tıklanabilir olmasını bekler.
         *
         * @param element Beklenecek WebElement
         * @param timeToWaitInSec Bekleme süresi (saniye cinsinden)
         * @return Tıklanabilir olan WebElement
         */
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static Boolean waitForInvisibilityOfElement(WebElement element, int timeToWaitInSec) {
        /**
         * Bir WebElement'in görünmez olmasını bekler.
         *
         * @param element Beklenecek WebElement
         * @param timeToWaitInSec Bekleme süresi (saniye cinsinden)
         * @return Görünmez olan WebElement
         */
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        /**
         * Sayfanın tamamen yüklenmesini bekler.
         *
         * @param timeOutInSeconds Bekleme süresi (saniye cinsinden)
         */
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void hover(WebElement element) {
        /**
         * Bir WebElement'in üzerine fare ile gelme işlemini gerçekleştirir.
         *
         * @param element Üzerine gelinmek istenen WebElement
         */
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(element).perform();
    }

    public static void scrollToElement(WebElement element) {
        /**
         * Bir WebElement'e kaydırma işlemi yapar.
         *
         * @param element Kaydırılacak WebElement
         */
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        Util.sleep(1);
    }

    public static void scrollToElementAndclick(WebElement element) {
        /**
         * Bir WebElement'e kaydırma ve tıklama işlemi yapar.
         *
         * @param element Kaydırılacak ve tıklanacak WebElement
         */
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        Util.sleep(2);
        element.click();
    }

    public static void clickToElement(String text) {
        /**
         * Metin içeren bir elementi tıklar.
         *
         * @param text Tıklanacak elementin içeriği
         */
        Driver.get().findElement(By.xpath("//*[contains(text(),'" + text + "')]")).click();

    }

    public static List<String> getTextOfList(List<WebElement> list) {
        /**
         * Bir WebElement listesinin metinlerini alır.
         *
         * @param list Metinleri alınacak WebElement listesi
         * @return Metinlerin bulunduğu yeni bir liste
         */
        List<String> newList = new ArrayList<>();
        for (WebElement each : list) {
            newList.add(each.getText().trim());
        }
        System.out.println("newList = " + newList);
        return newList;
    }

    public static List<String> getAttributesOfList(List<WebElement> list, String attribute) {
        /**
         * Bir WebElement listesinin belirli bir attribute'ünü alır.
         *
         * @param list Attribute'ü alınacak WebElement listesi
         * @param attribute Alınacak attribute'ün adı
         * @return Attribute'lerin bulunduğu yeni bir liste
         */
        List<String> newList = new ArrayList<>();
        for (WebElement each : list) {
            newList.add(each.getAttribute(attribute).trim());
        }
        System.out.println("newList = " + newList);
        return newList;
    }

    public static void waitForCurrentUrlContainsText(String text, int second) {
        /**
         * URL'nin belirtilen metni içerip içermediğini kontrol eder.
         *
         * @param text URL'de aranacak metin
         * @param second Bekleme süresi (saniye cinsinden)
         */
        int counter = 0;
        //click on element as many as you specified in attempts parameter
        while (counter < second) {
            try {
                if (Driver.get().getCurrentUrl().contains(text))
                    break;
            } catch (WebDriverException e) {
                //if click failed
                //print exception
                //print attempt
                e.printStackTrace();
                ++counter;
                //wait for 1 second, and try to click again
                sleep(1);
            }
        }
    }


    public static void verifyEachTextExistInList(String text, List<WebElement> elements) {
        /**
         * Bir listedeki her bir metnin belirli bir metni içerip içermediğini doğrular.
         *
         * @param text Aranacak metin
         * @param elements Metinleri kontrol edilecek WebElement listesi
         */
        List<String> textOfNameList = Util.getTextOfList(elements);
        for (String nameTxt : textOfNameList) {
            System.out.println("data = " + nameTxt);
            Assert.assertTrue("data = " + nameTxt, nameTxt.contains(text));
        }
        if (textOfNameList.size() < 1) {
            Assert.fail("Liste boş [...]");
        }
    }


    public static String reverse(String str) {
        /**
         * Verilen bir string'i tersine çevirir.
         *
         * @param str Tersine çevrilecek string
         * @return Tersine çevrilmiş string
         */
        String reverse = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reverse += str.charAt(i);
        }
        return reverse;
    }

    public static String generateRandomNumber() {
        // 1 ile 100 arasında random sayı üretir. Bunu string olarak döner.
        Random random = new Random();
        int num = random.nextInt(100) + 1;
        return String.valueOf(num);
    }

    public static Integer generateRandomNumber(Integer limit) {
        // Integer limit arasında random sayı üretir.
        Random random = new Random();
        return random.nextInt(limit);
    }


    public static Select select(WebElement element) {
        /**
         * Verilen WebElement'i tıklanabilir olana kadar bekler ve ardından bir Select nesnesi döner.
         *
         * @param element Tıklanabilir olması beklenen WebElement
         * @return Select nesnesi
         */
        waitForElementToBeClickable(element, 10);
        return new Select(element);
    }

    public static void setAttribute(WebElement element, String attributeName, String attributeValue) {
        /**
         * Bir WebElement'in belirli bir attribute'ünü ayarlar.
         *
         * @param element Attribute'ü ayarlanacak WebElement
         * @param attributeName Ayarlanacak attribute'ün adı
         * @param attributeValue Ayarlanacak attribute'ün değeri
         */
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }

    public static void clickWithJS(WebElement element) {
        /**
         * Bir WebElement'e JavaScript kullanarak tıklama işlemi yapar.
         *
         * @param element Tıklanacak WebElement
         */
        JavascriptExecutor executor = (JavascriptExecutor) Driver.get();
        executor.executeScript("arguments[0].click();", element);
    }

    public static void setImplicitWait(int waitTimeInSeconds) {
        /**
         * Belirtilen süre boyunca implicit wait ayarlar.
         *
         * @param waitTimeInSeconds Bekleme süresi (saniye cinsinden)
         */
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTimeInSeconds));
    }


    public static int getNumbersInText(WebElement element) {
        /**
         * Bir WebElement'in metnindeki sayıları alır ve bir tam sayı olarak döner.
         *
         * @param element Metni alınacak WebElement
         * @return Metindeki sayılar
         */
        String text = element.getText().trim();
        System.out.println("text = " + text);
        String numeric = "";
        for (int i = 0; i < text.length(); i++) {
            if (StringUtils.isNumeric(text.charAt(i) + "")) {
                numeric += text.charAt(i);
            }
        }
        System.out.println("numeric = " + numeric);
        return Integer.parseInt(numeric);
    }

    public static void clickElementByText(String text) {
        /**
         * Metin içeren bir elementi tıklar.
         *
         * @param text Tıklanacak elementin içeriği
         */
        WebElement element = Driver.get().findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        System.out.println("Tıklanan Element= " + element.getText());
        scrollToElementAndclick(element);
    }


    public static void verifyElementContainsText(WebElement element, String expectedText) {
        /**
         * Bir WebElement'in beklenen metni içerip içermediğini doğrular.
         *
         * @param element Metni kontrol edilecek WebElement
         * @param expectedText Beklenen metin
         */
        System.out.println("element text = " + element.getText());
        System.out.println("expected text = " + expectedText);
        Assert.assertTrue(element.getText().contains(expectedText));
    }

    public static void verifyElementEqualsText(WebElement element, String expectedText) {
        /**
         * Bir WebElement'in beklenen metni içerip içermediğini doğrular.
         *
         * @param element Metni kontrol edilecek WebElement
         * @param expectedText Beklenen metin
         */
        System.out.println("actual text = " + element.getText());
        System.out.println("expected text = " + expectedText);
        Assert.assertEquals(expectedText, element.getText().trim());
    }

    public static void clickWithActions(WebElement element) {
        /**
         * Bir WebElement'e Actions sınıfını kullanarak tıklama işlemi yapar.
         *
         * @param element Tıklanacak WebElement
         */
        scrollToElement(element);
        waitForElementToBeClickable(element, 10);
        new Actions(Driver.get()).click(element).perform();
    }

    public static void refreshPage() {
        Driver.get().navigate().refresh();
        Util.sleep(1);
    }

    public static String generateEmail() {
        /**
         * Rastgele bir e-posta adresi üretir.
         *
         * @return Üretilen e-posta adresi
         */
        String eMail = "";
        for (int i = 0; i < 30; i++) {
            eMail += (char) ((Math.random() * 25) + 97);
        }
        eMail += "@gmail.com";
        return eMail;
    }

    public static void switchToWindow(String targetTitle) {
        /**
         * Belirtilen başlığa sahip pencereye geçiş yapar.
         *
         * @param targetTitle Geçiş yapılacak pencerenin başlığı
         */
        String origin = Driver.get().getWindowHandle();
        for (String handle : Driver.get().getWindowHandles()) {
            Driver.get().switchTo().window(handle);
            if (Driver.get().getTitle().contains(targetTitle)) {
                return;
            }
        }
        Driver.get().switchTo().window(origin);
    }

    public static void clickWithWait(WebElement element, int second) {
        /**
         * Bir WebElement'e belirli bir süre boyunca tıklamayı dener.
         *
         * @param element Tıklanacak WebElement
         * @param second Deneme süresi (saniye cinsinden)
         */
        setImplicitWait(1);
        for (int i = 0; i < second; i++) {
            try {
                element.click();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                sleep(1);
            }
        }

    }

    public static void waitForElementContainsText(WebElement element, String text, int second) {

        /**
         * Bir WebElement'in metninin belirli bir metni içerip içermediğini kontrol eder.
         *
         * @param element Metni kontrol edilecek WebElement
         * @param text Aranacak metin
         * @param second Bekleme süresi (saniye cinsinden)
         */
        setImplicitWait(1);
        for (int i = 0; i < second; i++) {
            if (element.getText().contains(text)) {
                if (text.length() < 1) {
                    System.out.println("text alanı boş = " + text);
                    break;
                }
                break;
            }
            sleep(1);
            refreshPage();
        }

    }

    public static boolean verifyTextExistInList(List<WebElement> elements, String text) {
        /**
         * Bir listedeki WebElement'lerin metinlerinin belirli bir metni içerip içermediğini kontrol eder.
         *
         * @param elements Metinleri kontrol edilecek WebElement listesi
         * @param text Aranacak metin
         * @return Metin listede varsa true, yoksa false
         */
        boolean flag = false;
        for (String element : getTextOfList(elements)) {
            if (element.contains(text)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void verifyEachElementsDisplayedInList(List<WebElement> elements) {
        /**
         * Bir listedeki her bir elementin görüntülenip görüntülenmediğini doğrular.
         *
         * @param elements Görüntülenmesi kontrol edilecek WebElement listesi
         */
        for (WebElement element : elements) {
            Assert.assertTrue(element.isDisplayed());
        }
    }

    public static int convertStringToInt(String str) {
        /**
         * Bir string'i tam sayıya dönüştürür.
         *
         * @param str Dönüştürülecek string
         * @return Dönüştürülen tam sayı
         */
        int i = Integer.parseInt(str);
        System.out.println("i = " + i);
        return i;
    }

    public static int convertWebelementToInt(WebElement element) {
        /**
         * Bir WebElement'in metnini tam sayıya dönüştürür.
         *
         * @param element Metni alınacak WebElement
         * @return Dönüştürülen tam sayı
         */
        int i = Integer.parseInt(element.getText());
        System.out.println("i = " + i);
        return i;
    }

    public static void verifyElementIsNotExist(By locator) {
        /**
         * Bir WebElement'in mevcut olmadığını doğrular.
         *
         * @param locator Mevcut olup olmadığı kontrol edilecek WebElement'in konumu
         */
        setImplicitWait(3);
        System.out.println("Driver.get().findElements(locator).size() = " + Driver.get().findElements(locator).size());
        Assert.assertFalse(Driver.get().findElements(locator).size() > 0);

    }

    public static boolean verifyElementIsExist(By locator) {
        /**
         * Bir WebElement'in mevcut olup olmadığını doğrular.
         *
         * @param locator Mevcut olup olmadığı kontrol edilecek WebElement'in konumu
         * @return WebElement mevcutsa true, değilse false
         */
        setImplicitWait(3);
        boolean flag = false;
        try {
            flag = Driver.get().findElements(locator).size() > 0;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static String getCurrentURL() {
        return Driver.get().getCurrentUrl();
    }


}
