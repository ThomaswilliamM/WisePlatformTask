# WisePlatformTask

**Overview**

This project contains automated test scripts for the Wise Platform, using Selenium WebDriver and TestNG framework. The tests cover basic login functionality, classroom navigation, and scheduling of live sessions.

**Project Structure**
The project consists of several classes, each representing a part of the Wise Platform. Here's an overview of the class structure:

**1. LoginPage.java**
Automates the login process to the Wise Platform.
Enters phone number, clicks the "Continue" button, verifies OTP, and asserts the correct institute name after login.

**2. ClassRoomPage.java**

Navigates to the Classroom page after login.
Verifies the classroom's name and ensures it matches the expected text ("Classroom for Automated testing").

**3. ScheduleSession.java**

Automates the process of scheduling a live session.
Fills in the session details like time, creates a session, navigates to the timeline, and clicks on the session card.
Extracts session details and prints them.

**4. WisePlatformTest.java**

The entry point for the test execution.
Initializes WebDriver and WebDriverWait.
Instantiates and runs the methods from LoginPage, ClassRoomPage, and ScheduleSession.
