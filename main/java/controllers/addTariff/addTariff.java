package controllers.addTariff;

import controllers.MainControllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class addTariff {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button bttnAddAnnual;

    @FXML
    private Button bttnAddMaximum;

    @FXML
    private Button bttnAddOptimum;

    @FXML
    private Button bttnAddStart;

    @FXML
    private Button bttnGetBack;
    @FXML
    void initialize() {
        bttnAddStart.setOnAction(event -> {
            ((Stage)bttnAddStart.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/addTariffFXML/addTariffStart.fxml");
        });
        bttnAddOptimum.setOnAction(event -> {
            ((Stage)bttnAddStart.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/addTariffFXML/addOptimumTariff.fxml");
        });
        bttnAddMaximum.setOnAction(event -> {
            ((Stage)bttnAddMaximum.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/addTariffFXML/addMaximumTariff.fxml");
        });
        bttnAddAnnual.setOnAction(event -> {
            ((Stage)bttnAddAnnual.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/addTariffFXML/addAnnualTariff.fxml");
        });
        bttnGetBack.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/Tariff.fxml");
        });
    }
}

