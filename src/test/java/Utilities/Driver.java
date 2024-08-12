package Utilities;

//import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver get() {

        if (driverPool.get() == null) {
            // this line will tell which browser should open based on the value from properties file
            String browserParamFromEnv = System.getProperty("browser");
            String browser = browserParamFromEnv == null ? ConfigurationReader.get("browser") : browserParamFromEnv;
            switch (browser) {
                case "chrome":
//                    WebDriverManager.chromedriver().setup();
                    ChromeOptions opt = new ChromeOptions();
                    opt.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                    opt.addArguments("--disable-extensions");
                    opt.addArguments("disable-notifications");
                    opt.addArguments("no-sandbox");
                    opt.addArguments("--remote-allow-origins=*");
                    opt.addArguments("start-maximized");
                    opt.setAcceptInsecureCerts(true);
                    driverPool.set(new ChromeDriver(opt));
                    Driver.get().manage().window().maximize();
                    break;
                case "chrome-headless":
//                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("start-maximized");
                    driverPool.set(new ChromeDriver(options));
                    Dimension dimension = new Dimension(1552, 840);
                    Driver.get().manage().window().setSize(dimension);
                    Driver.get().manage().window().fullscreen();
                    break;
                case "firefox":
//                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox_headless":
//                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().addArguments("--headless=new")));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    }
//                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Edge");
                    }
//                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your OS doesn't support Safari");
                    }
//                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;

            }
        }

        return driverPool.get();
    }

    public static void closeDriver() {
        try {
            driverPool.get().quit();
            driverPool.remove();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driverPool.get() != null) {
                driverPool.get().quit();
                driverPool.remove();
            }
        }

    }
}