package ua.vedroid.bankservice.util;

import com.google.gson.JsonParser;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Currency;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ua.vedroid.bankservice.exception.CurrencyConvertException;

@Component
@AllArgsConstructor
public class CurrencyConverter {
    private static final String CONVERT_ENDPOINT = "%s/convert?from=%s&to=%s&amount=%f";
    private final Environment environment;

    public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
        String host = environment.getProperty("exchange.api.host");
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format(CONVERT_ENDPOINT, host, from, to, amount)))
                    .timeout(Duration.ofSeconds(5))
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .connectTimeout(Duration.ofSeconds(5))
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return JsonParser
                    .parseString(response.body())
                    .getAsJsonObject()
                    .get("result")
                    .getAsBigDecimal();
        } catch (InterruptedException | IOException e) {
            throw new CurrencyConvertException("Failed to convert currency", e);
        }
    }
}
