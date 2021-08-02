package app.currency;

import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class CurrencyConverterAPI {

    private static final String API_KEY = "6df2bb7563f58ac73252";
    private static final String USER_AGENT_ID = "Java/" + System.getProperty("java.version");

    static double rate(Currency from, Currency to) throws IOException {

        /**
         * Gets the rate for converting one currency to another. This
         * requires an active Internet connection to connect to the Free
         * Currency Conversion API.
         * @param source The currency to convert from. For example,
         * U. S. dollars (USD).
         * @param target The currency to convert to. For example,
         * Japanese yen (JPY).
         * @return The rate of conversion. For example, on June 1, 2020,
         * the rate of dollars to yen was 107.580505.
         * @throws IOException If there is a problem connecting to the
         * API.
         * @throws MalformedURLException If the URL to connect to the
         * API is malformed.
         */

        String queryPath
                = "https://free.currconv.com/api/v7/convert?q="
                + from.getCurrencyCode() + "_"
                + to.getCurrencyCode()
                + "&compact=ultra&apiKey=" + API_KEY;
        URL queryURL = new URL(queryPath);
        HttpURLConnection connection = (HttpURLConnection) queryURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT_ID);
        int responseCode = connection.getResponseCode();

        if (responseCode == 200) { // 200 is HTTP status OK
            InputStream stream = (InputStream) connection.getContent();
            Scanner scanner = new Scanner(stream);
            String quote = scanner.nextLine();
            String number = quote.substring(quote.indexOf(':') + 1, quote.indexOf('}'));
            return Double.parseDouble(number);

        } else {
            String excMsg = "Query " + queryPath + " returned status " + responseCode;
            throw new RuntimeException(excMsg);
        }
    }

    static List allCurrencies() throws IOException {
        String queryPath
                = "https://free.currconv.com/api/v7/currencies?"
                + "apiKey=" + API_KEY;
        URL queryURL = new URL(queryPath);
        HttpURLConnection connection = (HttpURLConnection) queryURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT_ID);
        int responseCode = connection.getResponseCode();

        if (responseCode == 200) { // 200 is HTTP status OK
            InputStream stream = (InputStream) connection.getContent();
            Scanner scanner = new Scanner(stream);
            String quote = scanner.nextLine();

            String[] quoteArray = quote.split("}");
            List<String> allCurrenciesList = extractCurrencySymbols(quoteArray);

            return allCurrenciesList.stream().sorted().collect(Collectors.toList());

        } else {
            String excMsg = "Query " + queryPath + " returned status " + responseCode;
            throw new RuntimeException(excMsg);
        }
    }

    private static List<String> extractCurrencySymbols(String[] quoteArray) {
        List<String> allCurrenciesList = new ArrayList<>();

        for (String s : quoteArray) {
            String[] quoteArraySplit = s.split(":");
            allCurrenciesList.add(quoteArraySplit[quoteArraySplit.length - 1].replaceAll("^\"|\"$", ""));
        }
        return allCurrenciesList;
    }
}
