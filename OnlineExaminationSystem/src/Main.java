
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private String displayName = "Student";
    private String password = "1234";

    private ExamManager examManager;
    private boolean examRunning = false;

    public Main() {
        setTitle("Online Examination System");
        setSize(750, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new LoginPanel(this), "LOGIN");
        mainPanel.add(new ProfilePanel(this), "PROFILE");

        add(mainPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (examRunning) {
                    int choice = JOptionPane.showConfirmDialog(
                            Main.this,
                            "Are you sure you want to quit?",
                            "Confirm Exit",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        examRunning = false;
                        dispose();
                    }
                } else {
                    dispose();
                }
            }
        });

        showLoginScreen();
        setVisible(true);
    }

    public void showLoginScreen() {
        cardLayout.show(mainPanel, "LOGIN");
    }

    public void showProfileScreen() {
        cardLayout.show(mainPanel, "PROFILE");
    }

    public void startExam() {
        examManager = new ExamManager();

        ExamPanel examPanel = new ExamPanel(this, examManager);
        mainPanel.add(examPanel, "EXAM");

        examRunning = true;
        cardLayout.show(mainPanel, "EXAM");
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void showResultScreen(long elapsedSeconds) {
        examRunning = false;

        ResultPanel resultPanel = new ResultPanel(
                this, examManager, elapsedSeconds);

        mainPanel.add(resultPanel, "RESULT");
        cardLayout.show(mainPanel, "RESULT");

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void logout() {
        examRunning = false;
        displayName = "Student";
        password = "1234";

        mainPanel.removeAll();
        mainPanel.add(new LoginPanel(this), "LOGIN");
        mainPanel.add(new ProfilePanel(this), "PROFILE");

        showLoginScreen();

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
