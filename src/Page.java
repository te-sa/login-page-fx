import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Page {
    @FXML
    Label label;

    void greeting(String username) {
        label.setText("Hello " + username + "!");
    }
}
