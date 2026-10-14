
import java.awt.*;
import javax.swing.*;

public class ExamPanel extends JPanel {

    private Main main;
    private ExamManager manager;
    private int currentQuestion = 0;
    private int timeLeft = 30 * 60;
    private long startTime;

    private JLabel questionNumberLabel;
    private JLabel timerLabel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton previousButton;
    private JButton nextButton;
    private JButton submitButton;
    private Timer timer;

    public ExamPanel(Main main, ExamManager manager) {
        this.main = main;
        this.manager = manager;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        setBackground(new Color(240, 245, 255));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        questionNumberLabel = new JLabel();
        questionNumberLabel.setFont(new Font("Arial", Font.BOLD, 16));

        timerLabel = new JLabel("Time: 30:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel.setForeground(Color.RED);

        topPanel.add(questionNumberLabel, BorderLayout.WEST);
        topPanel.add(timerLabel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(35, 20, 20, 20));
        centerPanel.setBackground(Color.WHITE);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        centerPanel.add(questionLabel);
        centerPanel.add(Box.createVerticalStrut(25));

        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 16));
            options[i].setBackground(Color.WHITE);
            options[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            optionGroup.add(options[i]);
            centerPanel.add(options[i]);
            centerPanel.add(Box.createVerticalStrut(15));
        }

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setOpaque(false);

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        submitButton = new JButton("Submit Exam");

        bottomPanel.add(previousButton);
        bottomPanel.add(nextButton);
        bottomPanel.add(submitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        previousButton.addActionListener(e -> {
            saveCurrentAnswer();

            if (currentQuestion > 0) {
                currentQuestion--;
                showQuestion();
            }
        });

        nextButton.addActionListener(e -> {
            saveCurrentAnswer();

            if (currentQuestion < manager.getQuestionCount() - 1) {
                currentQuestion++;
                showQuestion();
            }
        });

        submitButton.addActionListener(e -> {
            saveCurrentAnswer();

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to submit your exam?",
                    "Confirm Submission",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                finishExam();
            }
        });

        showQuestion();
        startTimer();
    }

    private void showQuestion() {
        Question question = manager.getQuestion(currentQuestion);

        questionNumberLabel.setText(
                "Question " + (currentQuestion + 1) +
                " of " + manager.getQuestionCount()
        );

        questionLabel.setText(
                "<html><body style='width: 500px'>" +
                question.getQuestionText() +
                "</body></html>"
        );

        String[] answerOptions = question.getOptions();
        optionGroup.clearSelection();

        for (int i = 0; i < options.length; i++) {
            options[i].setText(answerOptions[i]);
            options[i].setSelected(
                    manager.getSelectedAnswer(currentQuestion) == i
            );
        }

        previousButton.setEnabled(currentQuestion > 0);
        nextButton.setEnabled(
                currentQuestion < manager.getQuestionCount() - 1
        );
    }

    private void saveCurrentAnswer() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected()) {
                manager.saveAnswer(currentQuestion, i);
                return;
            }
        }

        manager.saveAnswer(currentQuestion, -1);
    }

    private void startTimer() {
        startTime = System.currentTimeMillis();
        updateTimerLabel();

        timer = new Timer(1000, e -> {
            timeLeft--;
            updateTimerLabel();

            if (timeLeft <= 0) {
                timer.stop();
                saveCurrentAnswer();

                JOptionPane.showMessageDialog(
                        this,
                        "Time is up! Your exam will be submitted."
                );

                finishExam();
            }
        });

        timer.start();
    }

    private void updateTimerLabel() {
        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;

        timerLabel.setText(
                String.format("Time: %02d:%02d", minutes, seconds)
        );
    }

    private void finishExam() {
        if (timer != null) {
            timer.stop();
        }

        saveCurrentAnswer();

        long elapsedSeconds =
                (System.currentTimeMillis() - startTime) / 1000;

        main.showResultScreen(elapsedSeconds);
    }
}
