
package WeatherForecastSystem;

import tools.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String home(
            @RequestParam(defaultValue = "Pune") String city,
            Model model) {

        model.addAttribute("city", city);

        try {
            JsonNode weather = weatherService.getWeather(city);
            JsonNode current = weather.path("current");
            JsonNode daily = weather.path("daily");

            model.addAttribute(
                    "temperature",
                    current.path("temperature_2m").asDouble());

            model.addAttribute(
                    "humidity",
                    current.path("relative_humidity_2m").asInt());

            model.addAttribute(
                    "feelsLike",
                    current.path("apparent_temperature").asDouble());

            model.addAttribute(
                    "wind",
                    current.path("wind_speed_10m").asDouble());

            model.addAttribute(
                    "description",
                    weatherService.getDescription(
                            current.path("weather_code").asInt()));

            List<ForecastDay> forecast = new ArrayList<>();

            for (int i = 0; i < daily.path("time").size(); i++) {
                forecast.add(new ForecastDay(
                        daily.path("time").get(i).asText(),
                        daily.path("temperature_2m_max").get(i).asDouble(),
                        daily.path("temperature_2m_min").get(i).asDouble(),
                        weatherService.getDescription(
                                daily.path("weather_code").get(i).asInt())
                ));
            }

            model.addAttribute("forecast", forecast);
            model.addAttribute("weatherLoaded", true);

        } catch (Exception e) {
            model.addAttribute(
                    "error",
                    "Unable to load weather. Check the city name and internet connection.");

            model.addAttribute("weatherLoaded", false);
        }

        return "index";
    }

    public record ForecastDay(
            String date,
            double maxTemp,
            double minTemp,
            String description) {
    }
}
