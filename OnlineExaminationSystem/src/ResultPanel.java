
import java.awt.*;
import javax.swing.*;

public class ResultPanel extends JPanel {

    public ResultPanel(Main main, ExamManager manager,
                       long elapsedSeconds) {

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        setBackground(new Color(240, 245, 255));

        JLabel title = new JLabel(
                "Examination Results", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        add(title, BorderLayout.NORTH);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(
                resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30));

        int total = manager.getQuestionCount();
        int correct = manager.getCorrectCount();
        int incorrect = manager.getIncorrectCount();
        int unanswered = 0;

        for (int i = 0; i < total; i++) {
            if (manager.getSelectedAnswer(i) == -1) {
                unanswered++;
            }
        }

        JLabel studentLabel = new JLabel(
                "Student: " + main.getDisplayName());
        JLabel scoreLabel = new JLabel(
                "Score: " + correct + " out of " + total);
        JLabel correctLabel = new JLabel(
                "Correct Answers: " + correct);
        JLabel incorrectLabel = new JLabel(
                "Incorrect Answers: " + (incorrect - unanswered));
        JLabel unansweredLabel = new JLabel(
                "Unanswered Questions: " + unanswered);

        long minutes = elapsedSeconds / 60;
        long seconds = elapsedSeconds % 60;

        JLabel timeLabel = new JLabel(
                String.format("Time Taken: %02d:%02d",
                        minutes, seconds));

        Font resultFont = new Font("Arial", Font.PLAIN, 18);

        JLabel[] labels = {
            studentLabel, scoreLabel, correctLabel,
            incorrectLabel, unansweredLabel, timeLabel
        };

        for (JLabel label : labels) {
            label.setFont(resultFont);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            resultPanel.add(label);
            resultPanel.add(Box.createVerticalStrut(15));
        }

        resultPanel.add(new JSeparator());
        resultPanel.add(Box.createVerticalStrut(15));

        JLabel breakdownTitle = new JLabel("Answer Breakdown");
        breakdownTitle.setFont(
                new Font("Arial", Font.BOLD, 20));
        breakdownTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultPanel.add(breakdownTitle);
        resultPanel.add(Box.createVerticalStrut(10));

        for (int i = 0; i < total; i++) {
            Question question = manager.getQuestion(i);
            int selected = manager.getSelectedAnswer(i);
            boolean isCorrect =
                    selected == question.getCorrectAnswer();

            String answerStatus;

            if (selected == -1) {
                answerStatus = "Not Answered";
            } else if (isCorrect) {
                answerStatus = "Correct";
            } else {
                answerStatus = "Incorrect";
            }

            JLabel detail = new JLabel(
                    "<html>Q" + (i + 1) + ": " +
                    question.getQuestionText() +
                    "<br/>Your answer: " +
                    (selected == -1
                        ? "Not answered"
                        : question.getOptions()[selected]) +
                    "<br/>Correct answer: " +
                    question.getOptions()[
                        question.getCorrectAnswer()] +
                    "<br/>Status: " + answerStatus +
                    "</html>");

            detail.setFont(new Font("Arial", Font.PLAIN, 14));
            detail.setAlignmentX(Component.LEFT_ALIGNMENT);

            resultPanel.add(detail);
            resultPanel.add(Box.createVerticalStrut(15));
        }

        add(new JScrollPane(resultPanel), BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(logoutButton);

        add(bottomPanel, BorderLayout.SOUTH);

        logoutButton.addActionListener(e -> main.logout());
    }
}
