package components;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CoursePage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.DateFormatter.getDateFromString;

@Slf4j
public class CourseComponent extends AnyComponentAbs<CourseComponent> {

    public CourseComponent(WebDriver driver) {
        super(driver);
    }

    private WebElement actualCourse;

    @FindBy(css = ".lessons__new-item")
    private List<WebElement> courses;

    @FindBy(css = ".lessons__new-item-title")
    protected List<WebElement> coursesList;

    /**
     * Получаем дату начала курса
     */
    private Date getDate(WebElement webElement) {
        WebElement courseTimeStart;

        if (isElementPresentedInElement(webElement, By.className("lessons__new-item-start"))) {
            courseTimeStart = webElement.findElement(By.className("lessons__new-item-start"));
        } else {
            courseTimeStart = null;
        }
        if (courseTimeStart != null) {
            return getDateFromString(courseTimeStart.getText().substring(2));
        } else {
            return null;
        }
    }

    /**
     * Получаем имя курса
     */
    private String getCourseName(WebElement webElement) {
        return webElement.findElement(By.className("lessons__new-item-title")).getText();
    }

    /**
     * Получаем название открытого курса
     */
    private String getOpenedCourseName() {
        if (waiter.elementIsPresented(By.className("course-header2__title"))) {
            return driver.findElement(By.className("course-header2__title")).getText();
        } else {
            try {
                throw new Exception("Название курса не найдено на странице, рекомендуем обновить страницу");
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }
    }

    /**
     * Выбираем курс по ключевому слову
     */
    public ArrayList<String> findCourseByKeywords(String keywords) {
        ArrayList<String> listCoursesByKeywords = (ArrayList<String>) coursesList.stream()
                .map(WebElement::getText)
                .filter(coursesList -> coursesList.contains(keywords))
                .collect(Collectors.toList());
        return listCoursesByKeywords;
    }

    /**
     * Проверяем, что открытая страница курса соответствует искомому курсу
     */
    private Boolean isExpectedCoursePageOpen(String courseName) {
        return getOpenedCourseName().contains(courseName);
    }

    /**
     * Ищем самый ранний курс
     */
    public CourseComponent getEarliestCourse() {
        actualCourse = courses.stream()
                .filter(a -> getDate(a) != null)
                .reduce((a, b) -> getDate(a).before(getDate(b)) ? a : b)
                .orElse(null);
        return this;
    }

    /**
     * Ищем самый поздний курс
     */
    public CourseComponent getOldestCourse() {
        actualCourse = courses.stream()
                .filter(a -> getDate(a) != null)
                .reduce((a, b) -> getDate(a).after(getDate(b)) ? a : b)
                .orElse(null);
        return this;
    }

    /**
     * Выбираем курс
     */
    public CoursePage actionsClickOnCard() {
        if (actualCourse != null) {
            String courseName = getCourseName(actualCourse).replace("Специализация ", "");
            scrollToElement.apply(driver, actualCourse);
            actions.moveToElement(actualCourse)
                    .build()
                    .perform();
            actualCourse.click();
            assertThat(isExpectedCoursePageOpen(courseName)).isTrue();
            return new CoursePage(driver);
        } else {
            try {
                throw new Exception("Курс не выбран");
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public CoursePage clickOnCard(){

        if (actualCourse != null) {
            String courseName = getCourseName(actualCourse).replace("Специализация ", "");
            scrollToElement.apply(driver, actualCourse);
            actualCourse.click();
            assertThat(isExpectedCoursePageOpen(courseName))
                    .as(String.format("Wrong course page has been opened! Course name on card: %s   Course name on page: %s",
                            courseName, getOpenedCourseName()))
                    .isTrue();
            return new CoursePage(driver);
        } else {
            try {
                throw new Exception("Actual course wasn't chosen! It's possible to click on all courses!");
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

}

