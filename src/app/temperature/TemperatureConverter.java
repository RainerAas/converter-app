package app.temperature;

import app.CoreLogic;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TemperatureConverter extends Application {

//    ADD K to C and K to F!

    @FXML
    public Text topText;

    @FXML
    public ComboBox temperatureComboBox;

    @FXML
    public TextField entry;

    @FXML
    public Button calculateButton;

    @FXML
    public Text resultText;

    @FXML
    private void initialize() {

        //Add choices to the choice box.
        ObservableList<String> temperatureChoiceList =
                FXCollections.observableArrayList("\u00b0C", "\u00b0F");

        // ℃ = \u2103
        // ℉ = \u2109
        // ° = \u00b0

        //Add "C" and "F" to choices.
        temperatureComboBox.setItems(temperatureChoiceList);
        temperatureComboBox.setValue("\u00b0C");
    }

    //Main temperature converting method.
    public void convertTemperatures() {
        Object choiceBoxValue = temperatureComboBox.getValue();
        //C -> F, K
        if (choiceBoxValue == "\u00b0C") {
            //Continue only on numbers.
            if (checkDigits(entry.getText())) {
                CoreLogic coreLogic = new CoreLogic();
                double roundOffF = Math.round(coreLogic.convertCToF(Double.parseDouble(entry.getText())) * 1000) / 1000.0;
                double roundOffK = Math.round(coreLogic.convertCToK(Double.parseDouble(entry.getText())) * 1000) / 1000.0;
                String resultOne = String.valueOf(roundOffF);
                String resultTwo = String.valueOf(roundOffK);
                String textResult = "Results:\n" + resultOne + " \u00b0F" + "\n" + resultTwo + " K";
                resultText.setText(textResult);
                resultText.setManaged(true);
                resultText.setVisible(true);
            } else {
                //If user entry isn't a number, show appropriate alert message.
                converterEntryError();
            }
        //F -> C, K
        } else if (choiceBoxValue == "\u00b0F"){
            //Continue only on numbers.
            if (checkDigits(entry.getText())) {
                CoreLogic coreLogic = new CoreLogic();
                double roundOffC = Math.round(coreLogic.convertFToC(Double.parseDouble(entry.getText())) * 1000) / 1000.0;
                double roundOffK = Math.round(coreLogic.convertFToK(Double.parseDouble(entry.getText())) * 1000) / 1000.0;
                String resultOne = String.valueOf(roundOffC);
                String resultTwo = String.valueOf(roundOffK);
                String textResult = "Results:\n" + resultOne + " \u00b0C" + "\n" + resultTwo + " K";
                resultText.setText(textResult);
                resultText.setManaged(true);
                resultText.setVisible(true);
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
        Object choiceBoxValue = temperatureComboBox.getValue();
        if (choiceBoxValue == "\u00b0C") {
            entry.setPromptText("\u00b0C");
            resultText.setText("");
            resultText.setVisible(false);
            resultText.setManaged(false);
        } else if (choiceBoxValue == "\u00b0F") {
            entry.setPromptText("\u00b0F");
            resultText.setText("");
            resultText.setVisible(false);
            resultText.setManaged(false);
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
        Parent root = FXMLLoader.load(getClass().getResource("temperature/ConvertTemp.fxml"));
        primaryStage.setTitle("Temperature converter");

        Scene scene = new Scene(root, 200, 200);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
