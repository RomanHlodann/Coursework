package controllers.OtherControllers;

import Cooperation.Check;
import Cooperation.User;
import controllers.MainControllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class addUserController {
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button bttnAddUser;

    @FXML
    private Button bttnGetBack;

    @FXML
    private TextField inpName;

    @FXML
    private TextField inpSurname;

    @FXML
    private TextField inpTelNumb;

    static Group generalButtonGroup = new Group();
    @FXML
    void initialize() {
        bttnAddUser.setOnAction(event -> {
            setStyles();
            generalButtonGroup.getChildren().clear();
            AnchorPane.getChildren().remove(generalButtonGroup);
            if(!checkInfo())
                return;
            CurrentValues.tariff.addNewUser(new User(inpName.getText(), inpSurname.getText(), "+380" + inpTelNumb.getText()));
            ((Stage)bttnAddUser.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/User.fxml");
        });
        bttnGetBack.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/Tariff.fxml");
        });
    }
    public boolean checkInfo(){
        generalButtonGroup.getChildren().clear();
        AnchorPane.getChildren().remove(generalButtonGroup);
        Text text;
        if(!Check.checkName(inpName.getText())){
            inpName.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed name", 750,300, Color.RED,15);
        }
        else if(!Check.checkName(inpSurname.getText())){
            inpSurname.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed surname", 750,360, Color.RED,15);
        }
        else if(!checkPhone(inpTelNumb.getText().replaceAll("[- ]", ""))) {
            inpTelNumb.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed phone number\n or already exists", 750,430, Color.RED,15);
        }
        else return true;
        generalButtonGroup.getChildren().add(text);
        AnchorPane.getChildren().add(generalButtonGroup);
        return false;
    }
    public void setStyles(){
        inpName.setStyle("-fx-border-color: #fafafa");
        inpSurname.setStyle("-fx-border-color: #fafafa");
        inpTelNumb.setStyle("-fx-border-color: #fafafa");
    }
    public boolean checkPhone(String phoneNumber){
        if(!phoneNumber.matches("^[0-9]{9}")) {
            return false;
        }
        if(!Check.checkPhoneNumber(phoneNumber.substring(phoneNumber.length()-7))){
            return false;
        }
        if(!Check.checkIdentifier(phoneNumber.substring(0, phoneNumber.length()-7))){
            return false;
        }
        if(CurrentValues.company.checkForPhoneInCompany(phoneNumber)){
            return false;
        }
        return true;
    }
}
