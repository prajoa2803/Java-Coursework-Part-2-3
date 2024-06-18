
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable{
    FileChooser fileChooser = new FileChooser();
    @FXML
    private Label A1;

    @FXML
    private Label A2;

    @FXML
    private Label B1;

    @FXML
    private Label B2;

    @FXML
    private Label B3;

    @FXML
    private Label C1;

    @FXML
    private Label C2;

    @FXML
    private Label C3;

    @FXML
    private Label C4;

    @FXML
    private Label C5;

    @FXML
    protected void onHelloButtonClick() {
        File file = fileChooser.showOpenDialog(new Stage());

        try{
            Scanner scanner = new Scanner (file);
            A1.setText(scanner.nextLine());
            A2.setText(scanner.nextLine());
            B1.setText(scanner.nextLine());
            B2.setText(scanner.nextLine());
            B3.setText(scanner.nextLine());
            C1.setText(scanner.nextLine());
            C2.setText(scanner.nextLine());
            C3.setText(scanner.nextLine());
            C4.setText(scanner.nextLine());
            C5.setText(scanner.nextLine());


        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("D:\\IIT\\SD\\PROGRAMMING\\JAVA\\demo1\\src\\main\\java\\com\\example\\demo1"));

    }
}