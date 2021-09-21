package app.currency;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class CurrencyConverter extends Application {

    @FXML
    public TextField entry;

    @FXML
    public ComboBox baseCurrency;

    @FXML
    public ComboBox desiredCurrency;

    @FXML
    public Button calculateButton;

    @FXML
    public Text resultText;

    @FXML
    private void initialize() throws IOException {
        //Add various currencies to choices.
        List allCurrenciesSymbols = CurrencyConverterAPI.allCurrencies();
        ObservableList currencyChoiceList = FXCollections.observableArrayList(allCurrenciesSymbols);

        //Add default choices.
        baseCurrency.setItems(currencyChoiceList);
        desiredCurrency.setItems(currencyChoiceList);

        baseCurrency.setValue("EUR");
        desiredCurrency.setValue("USD");
    }

    //Main currency conversion method.
    public void convertCurrencies() throws IOException {

        Object entryBoxValue = baseCurrency.getValue();
        Object desiredBoxValue = desiredCurrency.getValue();

        //Convert entries to currencies to be used by the API.
        Currency entryCurrency = Currency.getInstance(entryBoxValue.toString());
        Currency desiredCurrency = Currency.getInstance(desiredBoxValue.toString());

        //Check if entry is a number.
        if (checkDigits(entry.getText())) {
            //If number contains comma, replace it with a dot (doubles require a dot).
            Double result = 0.0;
            if (entry.getText().contains(",")) {
                String entryReplaced = entry.getText().replace(",", ".");
                double actual = CurrencyConverterAPI.rate(entryCurrency, desiredCurrency);
                result = Double.parseDouble(entryReplaced) * actual;
            } else {
                double actual = CurrencyConverterAPI.rate(entryCurrency, desiredCurrency);
                result = Double.parseDouble(entry.getText()) * actual;
            }
            result = Math.round(result * 1000) / 1000.0;
            resultText.setText("Result:\n" + result + " " + desiredCurrency);
            resultText.setManaged(true);
            resultText.setVisible(true);
        //If user entry ain't a number, throw an error.
        } else {
            converterEntryError();
        }
    }

    public void eraseResult() {
        resultText.setText("");
        resultText.setVisible(false);
        resultText.setManaged(false);
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
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ConvertCurrencies.fxml"));
        primaryStage.setTitle("Currency converter");

        Scene scene = new Scene(root, 200, 213);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
