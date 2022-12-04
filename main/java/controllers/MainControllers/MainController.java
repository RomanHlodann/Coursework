package controllers.MainControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController {
    public static MainController mainController = new MainController();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttnCmp;

    @FXML
    private Button bttnExit;

    @FXML
    private Button bttnMain;

    @FXML
    private Button bttnTariff;


    @FXML
    void initialize() {
        bttnMain.setOnAction(event -> {
            ((Stage)bttnMain.getScene().getWindow()).close();
            openNewScene("/Main/MainFXML/TariffLab.fxml");
        });
        bttnCmp.setOnAction(event -> {
            ((Stage)bttnCmp.getScene().getWindow()).close();
            openNewScene("/Main/MainFXML/Companies.fxml");
        });
        bttnTariff.setOnAction(event -> {
            ((Stage)bttnCmp.getScene().getWindow()).close();
            openNewScene("/Main/OtherFXML/chooseCompany.fxml");
        });
        bttnExit.setOnAction(event -> {
            ((Stage)bttnCmp.getScene().getWindow()).close();
            openNewScene("/Main/MainFXML/Exit.fxml");
        });
        return;
    }
    public void openNewScene(String window){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static Text getTextBttn (String txt, int x, int y, Color color, int sizeOfText){
        Text label = new Text();
        label.setText(txt);
        label.setX(x);
        label.setY(y);
        label.setFont(Font.font(sizeOfText));
        label.setFill(color);
        return label;
    }
}
