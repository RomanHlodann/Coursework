package controllers.addTariff;

import Cooperation.Check;
import Tariff.MaximumTariff;
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

public class addMaximumTariff {
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button bttnAdd;

    @FXML
    private Button bttnGetBack;

    @FXML
    private TextField inpInternet;

    @FXML
    private TextField inpMinWithOthNetw;

    @FXML
    private TextField inpPrice;

    @FXML
    private TextField inpTimeAction;
    Group generalButtonGroup = new Group();

    @FXML
    void initialize() {
        bttnAdd.setOnAction(event -> {
            setStyles();
            if(!checkInfo())
                return;
            CurrentValues.company.addTariffToList(new MaximumTariff(Integer.parseInt(inpInternet.getText()),
                    Integer.parseInt(inpMinWithOthNetw.getText()), Double.parseDouble(inpPrice.getText()), inpTimeAction.getText()));
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/Tariff.fxml");
        });
        bttnGetBack.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/addTariffFXML/addTariff.fxml");
        });
    }
    public void setStyles(){
        inpInternet.setStyle("-fx-border-color: #fafafa");
        inpMinWithOthNetw.setStyle("-fx-border-color: #fafafa");
        inpTimeAction.setStyle("-fx-border-color: #fafafa");
        inpPrice.setStyle("-fx-border-color: #fafafa");
    }
    private boolean checkInfo(){
        generalButtonGroup.getChildren().clear();
        AnchorPane.getChildren().remove(generalButtonGroup);
        Text text;
        if(!Check.checkInteger(inpInternet.getText(), 1000)){
            inpInternet.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed value", 460,500, Color.RED,20);
        }
        else if(!Check.checkInteger(inpMinWithOthNetw.getText(), 1000)){
            inpMinWithOthNetw.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed value", 460,500, Color.RED,20);
        }
        else if(!Check.checkTimeDuration(inpTimeAction.getText())){
            inpTimeAction.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed time action", 460,500, Color.RED,20);
        }
        else if(!Check.checkDouble(inpPrice.getText(), 1000)){
            inpPrice.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed price", 460,500, Color.RED,20);
        }else
            return true;
        generalButtonGroup.getChildren().add(text);
        AnchorPane.getChildren().add(generalButtonGroup);
        return false;
    }
}
