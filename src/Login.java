import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    @FXML
    void login() {
        if (usernameField.getText().equals("username") && passwordField.getText().equals("password")) {
            System.out.println("Login successful!");
            // TODO: open new tab in case of successful login
        } else System.out.println("Invalid username or password");
    }
}
