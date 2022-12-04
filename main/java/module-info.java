module Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports Main;
    opens Main to javafx.fxml;
    opens controllers.MainControllers to javafx.fxml;
    opens controllers.OtherControllers to javafx.fxml;
    opens controllers.addTariff to javafx.fxml;
    exports controllers.addTariff;
}