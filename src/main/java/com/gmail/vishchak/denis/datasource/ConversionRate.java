package com.gmail.vishchak.denis.datasource;

import com.gmail.vishchak.denis.entity.Currency;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ConversionRate {
    public static Currency conversionRateToday(String baseCurrency, String... currencies) throws IOException {
        Currency currency = new Currency();
        Map<String, Double> conversionRate = new HashMap<>();
        currency.setBaseCurrency(baseCurrency);

        URL url = new URL("https://v6.exchangerate-api.com/v6/9f2c772ac5200e33fec51896/latest/" + baseCurrency);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

// Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

// Accessing object
        StringBuilder sb = new StringBuilder(jsonobj.toString());

        sb.delete(0, 354).deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);

        String[] allCurrenciesRates = sb.toString().split(",");
        for (String s :
                allCurrenciesRates) {
            String[] currencyRate = s.split(":");
            for (String selectedCurrency :
                    currencies) {
                if (selectedCurrency.equalsIgnoreCase(currencyRate[0].substring(1, 4))) {
                    conversionRate.put(selectedCurrency, Double.valueOf(currencyRate[1]));
                }
            }
        }

        currency.setCurrencyConversionRate(conversionRate);
        return currency;
    }
}
