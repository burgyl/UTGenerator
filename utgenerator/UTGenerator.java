package utgenerator;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * UTGenerator's main class
 * @author Guduche
 */
public class UTGenerator extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL myRes = getClass().getResource("FXMLDocument.fxml");
        Parent root = FXMLLoader.load(myRes);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/utgenerator/images/UTGenerator.png"));
        stage.setTitle("UTGenerator");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
