import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button signUpButton;
    @FXML
    private Button backButton;

    public void setUsername(String username) {

    }

    public void setPassword(String password) {

    }

    private boolean availableUsername() {
        return true;
    }

    private boolean validPassword() {
        String potentialPassword = passwordField.getText();

        Pattern specialChars = Pattern.compile("[^a-zA-z0-9 ]");
        Pattern numbers = Pattern.compile("[0-9]");

        Matcher matchSpecial = specialChars.matcher(potentialPassword);
        Matcher matchNums = numbers.matcher(potentialPassword);

        boolean longEnough = potentialPassword.length() >= 8;
        boolean hasUpperCase = !potentialPassword.toLowerCase().equals(potentialPassword);
        boolean hasLowerCase = !potentialPassword.toUpperCase().equals(potentialPassword);
        boolean hasNum = matchNums.find();
        boolean hasSpecial = matchSpecial.find();

        return longEnough && hasUpperCase && hasLowerCase && hasNum && hasSpecial;
    }

    private boolean passwordsMatch() {
        return passwordField.getText().equals(confirmPasswordField.getText());
    }

    public void signUp() {
        if (availableUsername() && validPassword() && passwordsMatch()) {
            setUsername(usernameField.getText());
            setPassword(passwordField.getText());
        }
    }

    @FXML
    public void switchToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
