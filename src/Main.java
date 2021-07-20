import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

// use tab pane or split pane for login vs signup?

public class Main extends Application {

    // possible to simplify layouts?
    // how to set min size for login in fxml

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // I only want this warning on the page, not the login
        // do they use the same window? Yes, they do. How to change that?

        stage.setOnCloseRequest(e -> {
            e.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to exit the program?");

            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                stage.close();
            }
        });


        stage.setScene(new Scene(root));
        stage.setMinHeight(428);
        stage.setMinWidth(350);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        if (!DataSource.getInstance().open()) {
            System.out.println("FATAL ERROR: Couldn't connect to database");
            // how to have pop-up alert instead of print statement?
            Platform.exit(); // make user interface not appear if data base can't be accessed
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DataSource.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
