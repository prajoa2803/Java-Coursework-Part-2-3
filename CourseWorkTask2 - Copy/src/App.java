import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("App.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 640);
        stage.setTitle("Foodies Fave");
        stage.setScene(scene);
        stage.show();
    }

    public static void launch() {
        Application.launch();
    }
}

