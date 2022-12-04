package controllers.MainControllers;

import Cooperation.Company;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CompanyController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;
    @FXML
    private URL location;

    @FXML
    private Button bttnAdd;

    @FXML
    private Button bttnGetBack;

    @FXML
    void initialize() {
        updateInfo(Company.companies);
        bttnAdd.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/OtherFXML/addCompany.fxml");
        });
        bttnGetBack.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/TariffLab.fxml");
        });
        //root.getChildren().add(generalTextGroup);
        //updateInfo(Company.companies);
    }
    static Group generalTextGroup = new Group();
    //static Pane root = new Pane();
    public void updateInfo(ArrayList<Company> companies){
        final ArrayList<Company> company = companies;
        generalTextGroup.getChildren().clear();
        AnchorPane.getChildren().remove(generalTextGroup);
        if(company.size() == 0)
            AnchorPane.getChildren().add(MainController.getTextBttn("No companies were found!", 270, 350, Color.RED, 40));
        for(int i = 0; i < companies.size(); i++){
            final int index = i;
            generalTextGroup.getChildren().add(printInfoAboutCompany(company, i));

            Button removeCompany = new Button();
            removeCompany.setId("bttnRemoveCompany" + (i+1));
            removeCompany.setText("Remove");
            removeCompany.setStyle("-fx-background-color: #61D4C3; -fx-text-fill: #FAFAFA");
            removeCompany.setOnAction(event -> {
                company.remove(company.get(index));
                AnchorPane.getChildren().remove(removeCompany);
                updateInfo(company);
            });
            removeCompany.setPrefSize(150,40);
            generalTextGroup.getChildren().add(removeCompany);
            removeCompany.setLayoutX(800);
            removeCompany.setLayoutY(-68+60*(i+1));
        }
        generalTextGroup.setLayoutX(0);
        generalTextGroup.setLayoutY(200);
        AnchorPane.getChildren().add(generalTextGroup);

    }
    public static Text printInfoAboutCompany( ArrayList<Company> company, int i) {
        Text label = new Text();
        String info = i+1 + ". Name = " + company.get(i).getName() + ", Key number = " + company.get(i).getKeyNumb()
                + ", Count of tariffs = " + company.get(i).getTariffs().size() + ", Count of users = " + company.get(i).getCountOfUsers();
        label.setText(info);
        label.setX(40);
        label.setY(-40+60*(i+1));
        label.setFill(Color.WHITE);
        label.setFont(Font.font(20));
        return label;
    }
}
