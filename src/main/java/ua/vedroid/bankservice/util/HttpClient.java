package ua.vedroid.bankservice.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.springframework.stereotype.Component;
import ua.vedroid.bankservice.exception.HttpRequestException;

@Component
public class HttpClient {
    public JsonObject sendRequest(String uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .timeout(Duration.ofSeconds(5))
                    .build();
            HttpResponse<String> response = java.net.http.HttpClient.newBuilder()
                    .version(java.net.http.HttpClient.Version.HTTP_2)
                    .connectTimeout(Duration.ofSeconds(5))
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return JsonParser
                    .parseString(response.body())
                    .getAsJsonObject();
        } catch (InterruptedException | IOException e) {
            throw new HttpRequestException("Failed to send request for uri: " + uri, e);
        }
    }
}
