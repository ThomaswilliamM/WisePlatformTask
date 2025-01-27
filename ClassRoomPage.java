package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ClassRoomPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ClassRoomPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    private By groupCoursesButton = By.xpath("//span[contains(text(),'Group courses')]");
    private By testingCourseLink = By.xpath("//a[contains(text(), 'testing')]");
    private By classroomText = By.xpath("//div[contains(text(),'Classroom')]");

    public void navigateToGroupCourses() {
        wait.until(ExpectedConditions.elementToBeClickable(groupCoursesButton)).click();
    }

    public void selectTestingCourse() {
        wait.until(ExpectedConditions.elementToBeClickable(testingCourseLink)).click();
    }

    public void verifyClassroomText(String expectedText) {
        String actualText = wait.until(ExpectedConditions.elementToBeClickable(classroomText)).getText();
        Assert.assertEquals(actualText, expectedText, "Classroom text verification failed!");
    }

    public void accessClassroom(String expectedText) {
        try {
            navigateToGroupCourses();
            selectTestingCourse();
            verifyClassroomText(expectedText);
        } catch (Exception e) {
            System.out.println("Classroom access failed: " + e.getMessage());
        }
    }
}

