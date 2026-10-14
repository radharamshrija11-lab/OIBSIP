
import java.awt.*;
import javax.swing.*;

public class ProfilePanel extends JPanel {

    private JTextField displayNameField;
    private JPasswordField newPasswordField;

    public ProfilePanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 245, 255));

        JPanel form = new JPanel(new GridLayout(0, 1, 10, 10));
        form.setPreferredSize(new Dimension(350, 270));
        form.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        form.setBackground(Color.WHITE);

        JLabel title = new JLabel("Update Your Profile",
                SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel nameLabel = new JLabel("Display Name:");
        displayNameField = new JTextField(main.getDisplayName());

        JLabel passwordLabel = new JLabel("New Password:");
        newPasswordField = new JPasswordField();

        JButton saveButton = new JButton("Save and Continue");
        JLabel messageLabel = new JLabel(" ", SwingConstants.CENTER);

        form.add(title);
        form.add(nameLabel);
        form.add(displayNameField);
        form.add(passwordLabel);
        form.add(newPasswordField);
        form.add(saveButton);
        form.add(messageLabel);

        add(form);

        saveButton.addActionListener(e -> {
            String name = displayNameField.getText().trim();
            String password = new String(
                    newPasswordField.getPassword());

            if (name.isEmpty()) {
                messageLabel.setText("Enter a display name.");
                return;
            }

            if (!password.isEmpty()) {
                if (password.length() < 4) {
                    messageLabel.setText(
                            "Password must have at least 4 characters.");
                    return;
                }

                main.setPassword(password);
            }

            main.setDisplayName(name);
            main.startExam();
        });
    }
}
