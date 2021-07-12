package app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TemperatureConverter extends Application {

    ObservableList<String> temperatureChoiceList =
            FXCollections.observableArrayList("\u00b0C to \u00b0F", "\u00b0F to \u00b0C");

    // ℃ = \u2103
    // ℉ = \u2109
    // ° = \u00b0

    @FXML
    public TextField entry;

    @FXML
    public ChoiceBox temperatureChoiceBox;

    @FXML
    public Button calculateButton;

    @FXML
    public Text resultText;

    @FXML
    private void initialize() {
        //Add "C to F" and "F to C" to choices.
        temperatureChoiceBox.setItems(temperatureChoiceList);
        temperatureChoiceBox.setValue("\u00b0C to \u00b0F");
    }

    //Main temperature converting method.
    public void convertTemperatures() {
        Object choiceBoxValue = temperatureChoiceBox.getValue();
        //C to F
        if (choiceBoxValue == "\u00b0C to \u00b0F") {
            //Continue only on numbers.
            if (checkDigits(entry.getText())) {
                CoreLogic coreLogic = new CoreLogic();
                double roundOff = Math.round(coreLogic.convertCToF(Double.parseDouble(entry.getText())) * 1000) / 1000.0;
                String result = String.valueOf(roundOff);
                String textResult = String.format("Result: %s \u00b0F", result);
                resultText.setText(textResult);
            } else {
                //If user entry isn't a number, show appropriate alert message.
                converterEntryError();
            }
        //F to C
        } else if (choiceBoxValue == "\u00b0F to \u00b0C"){ //F to C
            //Continue only on numbers.
            if (checkDigits(entry.getText())) {
                CoreLogic coreLogic = new CoreLogic();
                double roundOff = Math.round(coreLogic.convertFToC(Double.parseDouble(entry.getText())) * 1000) / 1000.0;
                String result = String.valueOf(roundOff);
                String textResult = String.format("Result: %s \u00b0C", result);
                resultText.setText(textResult);
            } else {
                //If user entry isn't a number, show appropriate alert message.
                converterEntryError();
            }
        }
    }

    private void converterEntryError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        if (entry.getText().equals("")) {
            //Empty input alert.
            alert.setContentText("Please input a number.");
        } else {
            //False input alert.
            alert.setContentText("Can only convert numbers!");
        }
        alert.showAndWait();
    }

    public void setPrompt() {
        //Adds prompt message to entry box.
        //If chosen "C to F", will display ℃.
        //If chosen "F to C", will display ℉.
        Object choiceBoxValue = temperatureChoiceBox.getValue();
        if (choiceBoxValue == "\u00b0C to \u00b0F") {
            entry.setPromptText("\u00b0C");
        } else if (choiceBoxValue == "\u00b0F to \u00b0C") {
            entry.setPromptText("\u00b0F");
        }
    }

    public boolean checkDigits(String str) {
        //Check a string if it contains only digits.
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ConvertTemp.fxml"));
        primaryStage.setTitle("Temperature converter");

        Scene scene = new Scene(root, 200, 200);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
