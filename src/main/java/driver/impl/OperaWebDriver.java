package driver.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.logging.Level;

public class OperaWebDriver implements IDriver {

  @Override
  public WebDriver newDriver() {
    OperaOptions operaOptions = new OperaOptions();
    operaOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    operaOptions.setCapability("enableVNC", Boolean.parseBoolean(System.getProperty("enableVNC", "false")));

    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
    operaOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

    if (getRemoteUrl() == null) {
      try {
        downloadLocalWebDriver(DriverManagerType.OPERA);
      } catch (DriverTypeNotSupported ex) {
        ex.printStackTrace();
      }

      return new OperaDriver(operaOptions);
    } else
      return new RemoteWebDriver(getRemoteUrl(), operaOptions);
  }
}
