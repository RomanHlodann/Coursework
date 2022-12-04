package controllers.addTariff;

import Cooperation.Check;
import Tariff.OptimumTariff;
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

public class addOptimumTariff {


    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button bttnAdd;

    @FXML
    private Button bttnGetBack;

    @FXML
    private TextField inpInternet;

    @FXML
    private TextField inpLimit;

    @FXML
    private TextField inpMinAbr;

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
            CurrentValues.company.addTariffToList(new OptimumTariff(Integer.parseInt(inpInternet.getText()),
                    Integer.parseInt(inpMinAbr.getText()), inpLimit.getText(), Double.parseDouble(inpPrice.getText()), inpTimeAction.getText()));
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/Tariff.fxml");
        });
        bttnGetBack.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/addTariffFXML/addTariff.fxml");
        });
    }
    private boolean checkInfo(){
        generalButtonGroup.getChildren().clear();
        AnchorPane.getChildren().remove(generalButtonGroup);
        Text text;
        if(!Check.checkInteger(inpInternet.getText(), 1000)){
            inpInternet.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed value", 450,575, Color.RED,20);
        }
        else if(!Check.checkInteger(inpMinAbr.getText(), 1000)){
            inpMinAbr.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed value", 450,575, Color.RED,20);
        }
        else if(!inpLimit.getText().equals("limited") && !inpLimit.getText().equals("unlimited")){
            inpLimit.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed limit", 450,575, Color.RED,20);
        }
        else if(!Check.checkTimeDuration(inpTimeAction.getText())){
            inpTimeAction.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed time action", 450,575, Color.RED,20);
        }
        else if(!Check.checkDouble(inpPrice.getText(), 1000)){
            inpPrice.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed price", 450,575, Color.RED,20);
        }else
            return true;
        generalButtonGroup.getChildren().add(text);
        AnchorPane.getChildren().add(generalButtonGroup);
        return false;
    }
    public void setStyles(){
        inpInternet.setStyle("-fx-border-color: #fafafa");
        inpPrice.setStyle("-fx-border-color: #fafafa");
        inpLimit.setStyle("-fx-border-color: #fafafa");
        inpTimeAction.setStyle("-fx-border-color: #fafafa");
        inpMinAbr.setStyle("-fx-border-color: #fafafa");
    }
}
