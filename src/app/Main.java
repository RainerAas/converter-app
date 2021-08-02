package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    //Võib ühised meetodid kunagi ka kokku võtta ja ülemklassi viia

    @FXML
    public Button temperatureWindowButton;

    @FXML
    public Button currencyWindowButton;

    public void handleButtonClick (){
        try {
            if (temperatureWindowButton.isHover()) {
                Parent root = FXMLLoader.load(getClass().getResource("temperature/ConvertTemp.fxml"));
                String title = "Temperature";
                handleButtonClickCore(root, title);

            } else if (currencyWindowButton.isHover()) {
                Parent root = FXMLLoader.load(getClass().getResource("currency/ConvertCurrencies.fxml"));
                String title = "Currency";
                handleButtonClickCore(root, title);
            }

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to open.", e);
        }
    }

    private void handleButtonClickCore(Parent root, String title) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        Stage stage = new Stage();

        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Closes all windows if main is closed.
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });

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
