package ua.vedroid.bankservice.util;

import java.time.LocalDate;
import java.util.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExchangeRateRequester {
    private final HttpClient httpClient;
    @Value("${exchange.api.host}/convert?from=%s&to=%s&date=%tF")
    private String host;

    public double getRate(Currency from, Currency to, LocalDate date) {
        String uri = String.format(host, from, to, date);
        return httpClient.sendRequest(uri)
                .getAsJsonObject("info")
                .get("rate")
                .getAsNumber()
                .doubleValue();
    }
}
