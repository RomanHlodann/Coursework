package controllers.addTariff;

import java.net.URL;
import java.util.ResourceBundle;

import Cooperation.Check;
import Tariff.StartTariff;
import controllers.OtherControllers.CurrentValues;
import controllers.MainControllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class addStartTariff {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button bttnAdd;

    @FXML
    private Button bttnGetBack;

    @FXML
    private TextField inpMWT;

    @FXML
    private TextField inpPrice;

    @FXML
    private TextField inpSMS;
    Group generalButtonGroup = new Group();
    @FXML
    void initialize() {
        bttnAdd.setOnAction(event -> {
            setStyles();
            if(!checkInfo())
                return;
            CurrentValues.company.addTariffToList(new StartTariff(Integer.parseInt(inpMWT.getText()),
                    Integer.parseInt(inpSMS.getText()), Double.parseDouble(inpPrice.getText())));
            ((Stage)bttnAdd.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/Tariff.fxml");
        });
        bttnGetBack.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/addTariffFXML/addTariff.fxml");
        });
    }
    public boolean checkInfo(){
        generalButtonGroup.getChildren().clear();
        AnchorPane.getChildren().remove(generalButtonGroup);
        Text text;
        if(!Check.checkInteger(inpMWT.getText(), 100)){
            inpMWT.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed value", 750,300, Color.RED,15);
        }
        else if(!Check.checkInteger(inpSMS.getText(), 200)){
            inpSMS.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed value", 750,360, Color.RED,15);
        }
        else if(!Check.checkDouble(inpPrice.getText(), 500)){
            inpPrice.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed value", 750,420, Color.RED,15);
        }
        else
            return true;

        generalButtonGroup.getChildren().add(text);
        AnchorPane.getChildren().add(generalButtonGroup);
        return false;
    }
    public void setStyles(){
        inpMWT.setStyle("-fx-border-color: #fafafa");
        inpPrice.setStyle("-fx-border-color: #fafafa");
        inpSMS.setStyle("-fx-border-color: #fafafa");
    }
}
