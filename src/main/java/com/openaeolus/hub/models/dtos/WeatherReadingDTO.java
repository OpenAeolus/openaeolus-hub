package com.openaeolus.hub.models.dtos;

import com.openaeolus.hub.models.WeatherReading;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@NoArgsConstructor
public class WeatherReadingDTO {

    @Min(-300) @Max(300)
    private double temperature;

    @Min(0) @Max(100)
    private double humidity;

    @Min(0) @Max(2000)
    private double pressure;

    public WeatherReadingDTO(WeatherReading weatherReading) {
        this.temperature = weatherReading.getTemperature();
        this.humidity = weatherReading.getHumidity();
        this.pressure = weatherReading.getPressure();
    }
}
