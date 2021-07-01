import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Page {
    @FXML
    Label label;

    void greeting() {
        label.setText("Hello");
    }
}
