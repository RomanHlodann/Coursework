package controllers.MainControllers;

import Cooperation.User;
import controllers.OtherControllers.CurrentValues;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UserController {
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button bttnAdd;

    @FXML
    private Button bttnGetBack;

    @FXML
    void initialize() {
        bttnGetBack.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/MainFXML/Tariff.fxml");
        });
        bttnAdd.setOnAction(event -> {
            ((Stage)bttnGetBack.getScene().getWindow()).close();
            MainController.mainController.openNewScene("/Main/OtherFXML/addUser.fxml");
        });
        outputUserInfo(CurrentValues.tariff.getUsers());
    }
    static Group generalButtonGroup = new Group();
    public void outputUserInfo(ArrayList<User> users){
        final ArrayList<User> userCopy = users;
        generalButtonGroup.getChildren().clear();
        AnchorPane.getChildren().remove(generalButtonGroup);
        if(CurrentValues.tariff.getUsers().size() == 0) {
            Text label = new Text();
            String info = "No users were found!";
            label.setText(info);
            label.setX(350);
            label.setY(350);
            label.setFont(Font.font(40));
            label.setFill(Color.RED);
            AnchorPane.getChildren().add(label);
            return;
        }
        for(int i = 0; i < users.size(); i++){
            final int index = i;

            generalButtonGroup.getChildren().add(printInfoAboutUser(userCopy, i));

            CheckBox removeUser = new CheckBox();
            removeUser.setId("bttnRemoveUser" + (i+1));
            removeUser.setText("");
            removeUser.setOnAction(event -> {
                CurrentValues.tariff.getUsers().remove((userCopy.get(index)));
                outputUserInfo(CurrentValues.tariff.getUsers());
            });
            generalButtonGroup.getChildren().add(removeUser);
            removeUser.setLayoutX(800);
            removeUser.setLayoutY(46+62*(i+1));
        }
        generalButtonGroup.setLayoutX(0);
        generalButtonGroup.setLayoutY(100);
        AnchorPane.getChildren().add(generalButtonGroup);
    }
    public static Text printInfoAboutUser(ArrayList<User> user, int i) {
        Text label = new Text();
        String info = i+1 + ". Name = " + user.get(i).getName() + ", Surname = " + user.get(i).getSurname()
                + ", Telephone Number = " + user.get(i).getTelephoneNumber();
        label.setText(info);
        label.setX(40);
        label.setY(70+60*(i+1));
        label.setFill(Color.WHITE);
        label.setFont(Font.font(20));
        return label;
    }
}
