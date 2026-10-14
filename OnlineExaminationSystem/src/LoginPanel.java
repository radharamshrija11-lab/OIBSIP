
import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    public LoginPanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 245, 255));

        JPanel form = new JPanel(new GridLayout(0, 1, 10, 10));
        form.setPreferredSize(new Dimension(320, 260));
        form.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        form.setBackground(Color.WHITE);

        JLabel title = new JLabel("Online Examination System",
                SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        messageLabel = new JLabel(" ", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        form.add(title);
        form.add(usernameLabel);
        form.add(usernameField);
        form.add(passwordLabel);
        form.add(passwordField);
        form.add(loginButton);
        form.add(messageLabel);

        add(form);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.equals("student") &&
                    password.equals(main.getPassword())) {
                messageLabel.setText(" ");

                main.setDisplayName("Student");
                main.showProfileScreen();
            } else {
                messageLabel.setText("Invalid username or password");
            }
        });
    }
}
