package sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WisePlatformTask {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        LoginPage loginPage = new LoginPage(driver, wait);
        ClassRoomPage classRoomPage = new ClassRoomPage(driver, wait);
        ScheduleSession scheduleSessionPage = new ScheduleSession(driver, wait);

        loginPage.login("https://staging-web.wise.live", "1111100000", "0000", "Testing Institute");
        classRoomPage.accessClassroom("Classroom for Automated testing");
        scheduleSessionPage.scheduleSession("10:00");
    }
}

