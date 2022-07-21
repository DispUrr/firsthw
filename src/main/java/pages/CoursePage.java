package pages;

import org.openqa.selenium.WebDriver;

public class CoursePage extends AnyPageAbs<CoursePage> {
  public CoursePage(WebDriver driver) {
    super(driver);
  }

//    public CoursePage actionsClickOnCard(){
//        if (actualCourse != null) {
//            String courseName = getCourseName(actualCourse).replace("Специализация ", "");
//            scrollToElement.apply(driver, actualCourse);
//            actions.moveToElement(actualCourse)
//                    .build()
//                    .perform();
//            actualCourse.click();
//            assertThat(isExpectedCoursePageOpen(courseName))
//                    .as(String.format("Wrong course page has been opened! Course name on card: %s   Course name on page: %s",
//                            courseName, getOpenedCourseName()))
//                    .isTrue();
//            return new CoursePage(driver);
//        } else {
//            try {
//                throw new Exception("Actual course wasn't chosen! It's possible to click on all courses!");
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                return null;
//            }
//        }
//    }
}
