import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;

    @FXML
    void login() throws IOException {
        if (usernameField.getText().equals("username") && passwordField.getText().equals("password")) {
            System.out.println("Login successful!");
            // TODO: open new tab in case of successful login
            switchScene();
        } else System.out.println("Invalid username or password");
    }

    // code from https://www.youtube.com/watch?v=qnwBZveyUtA
    private void switchScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("page.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        // why do I not need stage.show() here?
    }
}
