import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // possible to simplify layouts?
    // how to set min size for login in fxml
    // how to make masking dots in password field smaller?

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setMinHeight(428);
        stage.setMinWidth(350);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
