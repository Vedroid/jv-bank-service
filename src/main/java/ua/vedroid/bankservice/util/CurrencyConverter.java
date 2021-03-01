package ua.vedroid.bankservice.util;

import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;
import org.springframework.stereotype.Component;
import ua.vedroid.bankservice.exception.CurrencyConvertException;

@Component
public class CurrencyConverter {
    private static final String API_HOST =
            "https://api.exchangerate.host/convert?from=%s&to=%s&amount=%f";

    public double convert(Currency from, Currency to, double amount) {
        try {
            URL url = new URL(String.format(API_HOST, from, to, amount));
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            return JsonParser
                    .parseReader(new InputStreamReader((InputStream) request.getContent()))
                    .getAsJsonObject()
                    .get("result")
                    .getAsDouble();
        } catch (IOException e) {
            throw new CurrencyConvertException("Failed to convert currency", e);
        }
    }
}
