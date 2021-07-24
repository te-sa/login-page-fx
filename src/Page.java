import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Page {
    @FXML
    Label label;
    @FXML
    Button logoutButton;

    @FXML
    void logOut() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    String nth(String username) {
        int n = DataSource.getInstance().getId(username);
        return switch (n) {
            case 1 -> "1st";
            case 2 -> "2nd";
            case 3 -> "3rd";
            default -> n + "th";
        };
    }

    // count how many times logged in?
    void greeting(String username) {
        label.setText(String.format("Hello %s, you are the %s user!", username, nth(username)));
        // and you have logged in n times
    }
}
