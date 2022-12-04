package controllers.OtherControllers;

import Cooperation.Company;
import controllers.MainControllers.CompanyController;
import controllers.MainControllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChooseCompany {
    @FXML
    private Button bttnGetBack;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    void initialize() {
        bttnGetBack.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/TariffLab.fxml");
        });
        companyInfo(Company.companies);
    }
    static Group generalButtonGroup = new Group();
    public void companyInfo(ArrayList<Company> companies){
        final ArrayList<Company> company = companies;
        generalButtonGroup.getChildren().clear();
        AnchorPane.getChildren().remove(generalButtonGroup);
        for(int i = 0; i < companies.size(); i++){
            final int index = i;
            generalButtonGroup.getChildren().add(CompanyController.printInfoAboutCompany(company, i));

            CheckBox chooseCompany = new CheckBox();
            chooseCompany.setId("bttnChooseCompany" + (i+1));
            chooseCompany.setText("");
            chooseCompany.setOnAction(event -> {
                CurrentValues.company = company.get(index);
                ((Stage)bttnGetBack.getScene().getWindow()).close();
                MainController.mainController.openNewScene("/Main/MainFXML/Tariff.fxml");
            });
            chooseCompany.setPrefSize(150,40);
            AnchorPane.getChildren().add(chooseCompany);
            chooseCompany.setLayoutX(800);
            chooseCompany.setLayoutY(132+60*(i+1));
        }
        generalButtonGroup.setLayoutX(0);
        generalButtonGroup.setLayoutY(200);
        AnchorPane.getChildren().add(generalButtonGroup);

    }
}
