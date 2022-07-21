package pages;

import annotations.URL;
import org.openqa.selenium.WebDriver;

@URL("/")
public class MainPage extends AnyPageAbs<MainPage> {
  public MainPage(WebDriver driver) {
    super(driver);
  }

}