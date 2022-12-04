package controllers.MainControllers;

import Main.DB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExitController {
    @FXML
    private AnchorPane Anchor;

    @FXML
    private Button bttnExt;

    @FXML
    private Button bttnGetBack;

    @FXML
    private Button bttnUdt;

    @FXML
    void initialize() {
       bttnUdt.setOnAction(event -> {
           DB database = new DB();
           database.setInfo();
           Text label = new Text();
           String info = "Successfully updated!";
           label.setText(info);
           label.setX(330);
           label.setY(450);
           label.setFont(Font.font(40));
           label.setFill(Color.GREEN);
           Anchor.getChildren().add(label);
       });
        bttnGetBack.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/TariffLab.fxml");
        });
        bttnExt.setOnAction(event -> {
            System.exit(0);
        });
    }
}
