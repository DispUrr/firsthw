import annotations.Driver;
import components.CourseComponent;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class MainOtusPageTest {

    @Driver
    public WebDriver driver;

    @Test
    public void clickEarliestCourseTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        CourseComponent cards = new CourseComponent(driver);
        cards.getEarliestCourse()
                .actionsClickOnCard();
    }

    @Test
    public void clickOldestCourseTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        CourseComponent cards = new CourseComponent(driver);
        cards.getOldestCourse()
                .actionsClickOnCard();
    }

}
