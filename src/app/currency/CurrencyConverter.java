package app.currency;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CurrencyConverter extends Application {

//    Sarnane nagu temperature, aga 2 choiceboxi, kus valid mis milliseks raha ühikuks teha, ning teise valimisel teostatakse tehe automaatselt.

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ConvertCurrencies.fxml"));
        primaryStage.setTitle("Currency converter");

        Scene scene = new Scene(root, 200, 200);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
