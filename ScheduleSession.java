package sample;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScheduleSession {

    private WebDriver driver;
    private WebDriverWait wait;

    public ScheduleSession(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    private By liveSessionsButton = By.xpath("//a[.='Live Sessions']");
    private By scheduleSessionsButton = By.xpath("//span[.=' Schedule sessions ']");
    private By addSessionButton = By.xpath("//span[.=' Add session ']");
    private By sessionTimeInput = By.xpath("//div[@class='col col-11']//div[@class='v-select__slot']/input[@type='text']");
    private By createButton = By.xpath("//span[.=' Create ']");
    private By timelineButton = By.xpath("//a[.='Timeline']");
    private By sessionCard = By.xpath(".//div[contains(text(), 'Live session')]");
    private By sessionDetailsElements = By.xpath("//div[@class='order-sm-1 col order-1']");
    public void navigateToLiveSessions() {
        wait.until(ExpectedConditions.elementToBeClickable(liveSessionsButton)).click();
    }

    public void openScheduleSessionPage() {
        wait.until(ExpectedConditions.elementToBeClickable(scheduleSessionsButton)).click();
    }

    public void addNewSession() {
        wait.until(ExpectedConditions.elementToBeClickable(addSessionButton)).click();
    }

    public void selectSessionTime(String time) {
        WebElement timeInput = wait.until(ExpectedConditions.elementToBeClickable(sessionTimeInput));
        timeInput.clear();
        Actions action = new Actions(driver);
        action.click(timeInput).sendKeys(time).build().perform();
    }

    public void createSession() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    public void viewTimeline() {
        wait.until(ExpectedConditions.elementToBeClickable(timelineButton)).click();
    }

    public void selectSessionCard() {
        WebElement card = wait.until(ExpectedConditions.elementToBeClickable(sessionCard));
        card.click();
    }

    public List<String> extractSessionDetails() {
        List<String> sessionDetailsList = new ArrayList<>();
        List<WebElement> sessionElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(sessionDetailsElements));
        for (WebElement sessionElement : sessionElements) {
            String extractedText = sessionElement.getText();
            String[] lines = extractedText.split("\n");

            if (lines.length >= 4) {
                sessionDetailsList.add("Session Name: " + lines[0]);
                sessionDetailsList.add("Instructor Name: " + lines[1]);
                sessionDetailsList.add("Session Date And Time: " + lines[2]);
                sessionDetailsList.add("Status: " + lines[3]);
            } else {
                System.out.println("Unexpected format for session element text: " + extractedText);
            }
        }
        return sessionDetailsList;
    }

    public void scheduleSession(String sessionTime) {
        try {
            navigateToLiveSessions();
            openScheduleSessionPage();
            addNewSession();
            selectSessionTime(sessionTime);
            createSession();
            viewTimeline();
            selectSessionCard();

            List<String> sessionDetails = extractSessionDetails();
            sessionDetails.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Session scheduling failed: " + e.getMessage());
        }
    }
}

