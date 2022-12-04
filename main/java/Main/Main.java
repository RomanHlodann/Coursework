package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Parent root;
    @Override
    public void start(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainFXML/TariffLab.fxml"));
        stage.setScene(new Scene(root, 1059, 626));
        stage.show();
    }

    public static void main(String[] args) {
        DB newdb = new DB();
        newdb.getInfo();
        launch();
    }
}