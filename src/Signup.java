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
    private Button backButton;

    private boolean availableUsername() {
        if (DataSource.getInstance().usernameExists(usernameField.getText())) {
            System.out.println("This username is already taken, please choose a different one.");
            return false;
        } else return true;
    }

    private boolean validUsername() {
        if (usernameField.getText().length() < 3) {
            System.out.println("Your username should be at least 3 characters long");
            return false;
        } else return true;
    }

    private boolean validPassword() {
        String potentialPassword = passwordField.getText();

        Pattern specialChars = Pattern.compile("[^a-zA-z0-9 ]");
        Pattern numbers = Pattern.compile("[0-9]");

        Matcher matchSpecial = specialChars.matcher(potentialPassword);
        Matcher matchNums = numbers.matcher(potentialPassword);

        if (potentialPassword.length() < 8) {
            System.out.println("Your password should contain at least 8 characters");
            return false;
        } else if (potentialPassword.toUpperCase().equals(potentialPassword)
                || potentialPassword.toLowerCase().equals(potentialPassword)) {
            System.out.println("Your password should contain both uppercase and lowercase letters");
            return false;
        } else if (!matchNums.find()) {
            System.out.println("Your password should contain at least one number");
            return false;
        } else if (!matchSpecial.find()) {
            System.out.println("Your password should contain at least one special character");
            return false;
        } else return true;
    }

    private boolean passwordsMatch() {
        if (passwordField.getText().equals(confirmPasswordField.getText())) return true;
        else {
            System.out.println("Passwords don't match!");
            return false;
        }
    }

    public void signUp() throws IOException {
        String user = usernameField.getText();
        if (validUsername() && availableUsername() && validPassword() && passwordsMatch()) {
            DataSource.getInstance().insertCredentials(user, passwordField.getText());
            System.out.printf("Welcome %s!", user);
            switchToLogin(); // go to page immediately instead?
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
