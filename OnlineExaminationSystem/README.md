# Online Examination System

## Project Overview

The Online Examination System is a desktop application developed using Java and Java Swing. It allows students to log in, update their profile, answer multiple-choice questions, and view their examination results.

## Objective

To develop a graphical examination system with timed MCQs, question navigation, automatic submission, and result calculation.

## Technologies Used

- Java
- Java Swing
- AWT
- Eclipse IDE

## Features

- Student login with username and password
- Profile update with display name and password
- Multiple-choice questions with four options
- Next and Previous question navigation
- 30-minute countdown timer
- Manual exam submission with confirmation
- Automatic submission when the timer expires
- Result screen showing score, time taken, and answer breakdown
- Confirmation dialog when closing the window during an exam
- Logout functionality

## Project Structure

```text
OnlineExaminationSystem/
├── src/
│   ├── Main.java
│   ├── LoginPanel.java
│   ├── ProfilePanel.java
│   ├── Question.java
│   ├── ExamManager.java
│   ├── ExamPanel.java
│   └── ResultPanel.java
├── screenshots/
│   ├── login.png
│   ├── profile.png
│   ├── exam.png
│   └── result.png
└── README.md
```

## Demo Login Credentials

- **Username:** `student`
- **Password:** `1234`

**Note:** This project uses demonstration credentials stored in memory. Profile changes are not permanently saved.

## How to Run

1. Install a compatible Java Development Kit (JDK).
2. Open Eclipse IDE.
3. Import or open the `OnlineExaminationSystem` project.
4. Open `Main.java` in the `src` folder.
5. Right-click and select **Run As → Java Application**.
6. Log in using the demo credentials.

## Screenshots

Application screenshots are available in the `screenshots` folder.

## Future Enhancements

- Add more questions and subjects.
- Store student accounts and results in a database.
- Add administrator functionality to manage questions.
- Generate downloadable result reports.

## Author

**Radharam Shrija**

Java Development Project — Task 4
