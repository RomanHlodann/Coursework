package controllers.OtherControllers;

import Cooperation.Check;
import Cooperation.Company;
import controllers.MainControllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddCompanyController {

    @FXML
    private Button bttnAddCompany;

    @FXML
    private Button bttnGetBack;

    @FXML
    private TextField inpCompName;

    @FXML
    private TextField inpKeyNumb;
    @FXML
    private AnchorPane AnchorPane;
    static Group generalButtonGroup = new Group();
    @FXML
    void initialize() {
        bttnAddCompany.setOnAction(event -> {
            setStyles();
            generalButtonGroup.getChildren().clear();
            AnchorPane.getChildren().remove(generalButtonGroup);
            if(!checkInfo())
                return;
            new Company(inpCompName.getText(), inpKeyNumb.getText());
            ((Stage)bttnAddCompany.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/Companies.fxml");
        });
        bttnGetBack.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/Companies.fxml");
        });
    }
    private boolean checkInfo(){
        generalButtonGroup.getChildren().clear();
        AnchorPane.getChildren().remove(generalButtonGroup);
        Text text;
        if(!Check.checkCompanyName(inpCompName.getText()) || !Company.checkForAvailbleName(inpCompName.getText())){
            inpCompName.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed company name\n or already exists", 750,290, Color.RED,15);
        }
        else if(!Check.checkIdentifier(inpKeyNumb.getText()) || !Company.checkForAvailbleKeyNumb(inpKeyNumb.getText())){
            inpKeyNumb.setStyle("-fx-border-color: #ff0000");
            text = MainController.getTextBttn("Wrong inputed key number\n or already exists", 750,350, Color.RED,15);
        }
        else return true;
        generalButtonGroup.getChildren().add(text);
        AnchorPane.getChildren().add(generalButtonGroup);
        return false;
    }
    public void setStyles(){
        inpKeyNumb.setStyle("-fx-border-color: #fafafa");
        inpCompName.setStyle("-fx-border-color: #fafafa");
    }
}
