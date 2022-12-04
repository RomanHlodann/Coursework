package controllers.MainControllers;

import Cooperation.Check;
import Cooperation.Company;
import Filters.FilterTariffs;
import Tariff.BaseTariff;
import controllers.OtherControllers.CurrentValues;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TariffController {
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button bttnAddTariff;

    @FXML
    private Button bttnGetBack;
    @FXML
    private Button bttnApply;
    @FXML
    private TextField fdMaxPrice;

    @FXML
    private TextField fdMaxTD;
    @FXML
    private Button bttnSortA;
    @FXML
    private Button bttnSortD;
    @FXML
    private TextField fdMinPrice;
    @FXML
    private Button bttnReset;

    @FXML
    private TextField fdMinTD;
    private ArrayList<BaseTariff> tariffs = new ArrayList<BaseTariff> (CurrentValues.company.getTariffs());

    @FXML
    void initialize() {
        setTariffs();
        bttnGetBack.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            new MainController().openNewScene("/Main/MainFXML/TariffLab.fxml");
        });
        bttnApply.setOnAction(event -> {
            filterTariffs();
        });
        bttnSortA.setOnAction(event -> {
            outputTariffInfo(Company.sortTariffs(tariffs, Company.TariffSorting.ASC));
            return;
        });
        bttnAddTariff.setOnAction(event -> {
            ((Stage)bttnAddTariff.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/addTariffFXML/addTariff.fxml");
        });
        bttnSortD.setOnAction(event -> {
            outputTariffInfo(Company.sortTariffs(tariffs, Company.TariffSorting.DESC));
            return;
        });
        bttnReset.setOnAction(event -> {
            setTariffs();
            outputTariffInfo(tariffs);
            return;
        });
        outputTariffInfo(tariffs);
    }
    static Group generalButtonGroup = new Group();
    public void outputTariffInfo(ArrayList<BaseTariff> tariffs){
        final ArrayList<BaseTariff> tariffsCopy = tariffs;
        generalButtonGroup.getChildren().clear();
        AnchorPane.getChildren().remove(generalButtonGroup);
        if(CurrentValues.company.getTariffs().size() == 0) {
            Text label = new Text();
            String info = "No tariffs were found!";
            label.setText(info);
            label.setX(240);
            label.setY(350);
            label.setFont(Font.font(40));
            label.setFill(Color.RED);
            AnchorPane.getChildren().add(label);
            return;
        }
        for(int i = 0; i < tariffs.size(); i++){
            final int index = i;

            generalButtonGroup.getChildren().add(printInfoAboutTariff(tariffsCopy, i));

            CheckBox removeTariff = new CheckBox();
            removeTariff.setId("bttnRemoveTariff" + (i+1));
            removeTariff.setText("");
            removeTariff.setOnAction(event -> {
                CurrentValues.company.getTariffs().remove(tariffsCopy.get(index));
                outputTariffInfo(CurrentValues.company.getTariffs());
            });
            generalButtonGroup.getChildren().add(removeTariff);
            removeTariff.setLayoutX(700);
            removeTariff.setLayoutY(0+100*(i+1));
        }
        generalButtonGroup.setLayoutX(0);
        generalButtonGroup.setLayoutY(100);
        AnchorPane.getChildren().add(generalButtonGroup);
    }
    public void filterTariffs(){
        while(true) {
            setStyles();
            if (!fdMinPrice.getText().equals("") || !fdMaxPrice.getText().equals("")) {
                try{
                    tariffs = FilterTariffs.filterTariffsByPrice(tariffs,
                            parseToDouble(fdMinPrice), parseToDouble(fdMaxPrice));
                } catch (NullPointerException e){
                    break;
                }
                outputTariffInfo(tariffs);
            }
            break;
        }
        while(true){
            if (!fdMinTD.getText().equals("") || !fdMaxTD.getText().equals("")) {
                try{
                    tariffs = FilterTariffs.filterTariffsByDurationTime(tariffs,
                            parseToTimeDuration(fdMinTD), parseToTimeDuration(fdMaxTD));
                } catch (NullPointerException e){
                    return;
                }
                outputTariffInfo(tariffs);
            }
            return;
        }
    }
    public Button printInfoAboutTariff(ArrayList<BaseTariff> tariffs, int i) {
        Button bttn = new Button();
        String info = i+1 + ". Name = " + tariffs.get(i).getName() + ", gbOfInternet = " + tariffs.get(i).getGbOfInternet()
                + ", Other network = " + tariffs.get(i).getMinutesWithOtherNetwork() + ", Minutes abroad = " + tariffs.get(i).getMinutesAbroad()
                + ",\n Limit top apps = " + tariffs.get(i).getLimitTopApps() + ", SMS = " + tariffs.get(i).getCountOfSms()
                + ", Price = " + tariffs.get(i).getPrice() + ", Time action = " + tariffs.get(i).getTimeAction();
        bttn.setText(info);
        bttn.setPrefSize(630,70);
        AnchorPane.getChildren().add(bttn);
        bttn.setLayoutX(40);
        bttn.setLayoutY(-20+100*(i+1));
        bttn.setFont(Font.font(15));
        bttn.setStyle("-fx-background-color: #61D4C3; -fx-text-fill: #FAFAFA");
        bttn.setOnAction(event -> {
            CurrentValues.tariff =  tariffs.get(i);
            ((Stage)bttn.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/User.fxml");
        });
        return bttn;
    }
    public double parseToDouble(TextField text){
        String value = text.getText();
        if(value.equals(""))
            return 0;
        if(Check.checkDouble(value, 1500)){
            return Double.parseDouble(value);
        }
        text.setStyle("-fx-border-color: #ff0000");
        throw new NullPointerException();
    }
    public String parseToTimeDuration(TextField text){
        String value = text.getText();
        if(value.equals(""))
            return "1 day";
        if(Check.checkTimeDuration(value)){
            return value;
        }
        text.setStyle("-fx-border-color: #ff0000");
        throw new NullPointerException();
    }
    public void setTariffs(){
        tariffs = new ArrayList<BaseTariff> (CurrentValues.company.getTariffs());
    }
    public void setStyles(){
        fdMinTD.setStyle("-fx-border-color: #fafafa");
        fdMaxTD.setStyle("-fx-border-color: #fafafa");
        fdMinPrice.setStyle("-fx-border-color: #fafafa");
        fdMaxPrice.setStyle("-fx-border-color: #fafafa");
    }
}
