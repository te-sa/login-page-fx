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

    void greeting(String username) {
        label.setText("Hello " + username + "!");
    }
}
