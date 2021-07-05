import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    Button signupButton;

    @FXML
    void logIn() throws IOException {
        boolean hasUsername = !usernameField.getText().isBlank();
        if (hasUsername && passwordField.getText().equals("password")) {
            switchToPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            // TODO: make alert look better
            alert.setContentText("Invalid username or password");
            passwordField.setText("");

            if (hasUsername) { // to set the focus on the field the user needs to change input in
                passwordField.requestFocus();
            } else {
                usernameField.requestFocus();
            }

            alert.show();
        }
    }

    @FXML
    void switchToSignup() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) signupButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // code from https://www.youtube.com/watch?v=qnwBZveyUtA
    private void switchToPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("page.fxml"));
        Parent root = loader.load();

        // code from https://www.youtube.com/watch?v=wxhGKR3PQpo
        Page page = loader.getController();
        page.greeting(usernameField.getText());

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        // why do I not need stage.show() here?
    }
}
