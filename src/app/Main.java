package app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    @FXML
    public Button temperatureWindowButton;

    @FXML
    public Button currencyWindowButton;

    public void handleButtonClick (){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            if (temperatureWindowButton.isHover()) {
                fxmlLoader.setLocation(getClass().getResource("temperature/ConvertTemp.fxml"));
            } else if (currencyWindowButton.isHover()) {
                fxmlLoader.setLocation(getClass().getResource("currency/ConvertCurrencies.fxml"));
            }

            Scene scene = new Scene(fxmlLoader.load(), 250, 250);
            Stage stage = new Stage();
            stage.setTitle("Temperature converter");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to open.", e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("Unit Converter");


        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
