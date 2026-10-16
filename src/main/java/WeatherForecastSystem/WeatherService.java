
package WeatherForecastSystem;


import tools.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Service
public class WeatherService {

    private final RestClient client = RestClient.create();

    public JsonNode getWeather(String city) {
        String encodedCity =
                UriUtils.encodeQueryParam(city, StandardCharsets.UTF_8);

        JsonNode location = client.get()
                .uri("https://geocoding-api.open-meteo.com/v1/search"
                        + "?name=" + encodedCity
                        + "&count=1&language=en&format=json")
                .retrieve()
                .body(JsonNode.class);

        if (location == null
                || !location.has("results")
                || location.get("results").isEmpty()) {
            throw new IllegalArgumentException("City not found.");
        }

        JsonNode place = location.get("results").get(0);

        double latitude = place.get("latitude").asDouble();
        double longitude = place.get("longitude").asDouble();

        return client.get()
                .uri("https://api.open-meteo.com/v1/forecast"
                        + "?latitude=" + latitude
                        + "&longitude=" + longitude
                        + "&current=temperature_2m,relative_humidity_2m,"
                        + "apparent_temperature,weather_code,wind_speed_10m"
                        + "&daily=weather_code,temperature_2m_max,"
                        + "temperature_2m_min"
                        + "&timezone=auto&forecast_days=5")
                .retrieve()
                .body(JsonNode.class);
    }

    public String getDescription(int code) {
        if (code == 0) return "Clear sky";
        if (code <= 3) return "Partly cloudy";
        if (code == 45 || code == 48) return "Foggy";
        if (code <= 57 && code >= 51) return "Drizzle";
        if (code >= 61 && code <= 67) return "Rain";
        if (code >= 71 && code <= 77) return "Snow";
        if (code >= 80 && code <= 82) return "Rain showers";
        if (code >= 95) return "Thunderstorm";
        return "Variable conditions";
    }
}
