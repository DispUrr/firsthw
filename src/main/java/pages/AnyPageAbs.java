package pages;

import annotations.URL;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.StandardWaiter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AnyPageAbs<T> {

  protected WebDriver driver;
  protected StandardWaiter waiter;
  protected Actions actions;

  public AnyPageAbs(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    waiter = new StandardWaiter(driver);
    actions = new Actions(driver);
  }

  public T open() {
    driver.get(getBaseUrl() + getPageUrl());

    return (T) page(getClass());
  }

  private String getBaseUrl() {
    return StringUtils.stripEnd(System.getProperty("webdriver.base.url"), "/");
  }

  private String getPageUrl() {
    URL url = getClass().getAnnotation(URL.class);
    if (url != null) {
      return url.value().replaceAll("/$", "");
    }
    return "";
  }

  private <T> T page(Class<T> clazz) {
    try {
      Constructor constructor = clazz.getConstructor(WebDriver.class);

      return convertInstanceOfObject(constructor.newInstance(driver), clazz);
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
    try {
      return clazz.cast(o);
    } catch (ClassCastException e) {
      return null;
    }
  }

}
