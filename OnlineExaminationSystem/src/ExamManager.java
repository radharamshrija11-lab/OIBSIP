
import java.util.ArrayList;
import java.util.List;

public class ExamManager {

    private List<Question> questions;
    private int[] selectedAnswers;

    public ExamManager() {
        questions = new ArrayList<Question>();

        questions.add(new Question(
            "Which keyword is used to inherit a class in Java?",
            new String[]{"implements", "extends", "inherits", "super"},
            1
        ));

        questions.add(new Question(
            "Which method is the entry point of a Java application?",
            new String[]{"start()", "run()", "main()", "init()"},
            2
        ));

        questions.add(new Question(
            "Which of these is not a primitive data type in Java?",
            new String[]{"int", "boolean", "String", "char"},
            2
        ));

        questions.add(new Question(
            "Which Swing component is used to display a button?",
            new String[]{"JLabel", "JButton", "JFrame", "JTextField"},
            1
        ));

        questions.add(new Question(
            "Which keyword creates an object in Java?",
            new String[]{"class", "this", "new", "void"},
            2
        ));

        selectedAnswers = new int[questions.size()];

        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1;
        }
    }

    public int getQuestionCount() {
        return questions.size();
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public void saveAnswer(int questionIndex, int answerIndex) {
        selectedAnswers[questionIndex] = answerIndex;
    }

    public int getSelectedAnswer(int questionIndex) {
        return selectedAnswers[questionIndex];
    }

    public int calculateScore() {
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            if (selectedAnswers[i] ==
                    questions.get(i).getCorrectAnswer()) {
                score++;
            }
        }

        return score;
    }

    public int getCorrectCount() {
        return calculateScore();
    }

    public int getIncorrectCount() {
        return questions.size() - calculateScore();
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
