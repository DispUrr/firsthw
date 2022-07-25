import annotations.Driver;
import components.CourseComponent;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.util.ArrayList;

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
                .clickOnCard();
    }

    @Test
    public void clickOldestCourseTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        CourseComponent cards = new CourseComponent(driver);
        cards.getOldestCourse()
                .actionsClickOnCard();
    }
    @Test
    public void findCoursesByKeywordTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        CourseComponent cards = new CourseComponent(driver);
        String keywords = System.getProperty("keywords");
        ArrayList<String> courseListResult = cards.findCourseByKeywords(keywords);
        if (0 == courseListResult.size()) {
            System.out.println("По Вашему запросу не найдено ни одного курса.");
        } else {
            System.out.println("По вашему запросу найдены курсы:" + courseListResult);
        }
    }

}
