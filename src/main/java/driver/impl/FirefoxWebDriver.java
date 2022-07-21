package driver.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.logging.Level;

public class FirefoxWebDriver implements IDriver {

  @Override
  public WebDriver newDriver() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.setHeadless(HEADLESS);
    firefoxOptions.addArguments("--enable-extensions");
    firefoxOptions.addArguments("--ignore-certificate-errors");
    firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        firefoxOptions.setCapability(CapabilityType.VERSION, System.getProperty("browser.version", ""));
    firefoxOptions.setCapability("enableVNC", Boolean.parseBoolean(System.getProperty("enableVNC", "false")));

    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
    firefoxOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

    if (getRemoteUrl() == null) {
      try {
        downloadLocalWebDriver(DriverManagerType.FIREFOX);
      } catch (DriverTypeNotSupported ex) {
        ex.printStackTrace();
      }

      return new FirefoxDriver(firefoxOptions);
    } else
      return new RemoteWebDriver(getRemoteUrl(), firefoxOptions);
  }
}
