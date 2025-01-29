package com.openaeolus.hub.models;

import com.openaeolus.hub.models.dtos.WeatherReadingDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "weather_readings")
@Data
@NoArgsConstructor
public class WeatherReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double temperature;
    private double humidity;
    private double pressure;

    public WeatherReading(WeatherReadingDTO weatherReading) {
        this.temperature = weatherReading.getTemperature();
        this.humidity = weatherReading.getHumidity();
        this.pressure = weatherReading.getPressure();
    }

}
