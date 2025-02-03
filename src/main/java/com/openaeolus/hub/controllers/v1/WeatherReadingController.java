package com.openaeolus.hub.controllers.v1;

import com.openaeolus.hub.models.dtos.WeatherReadingDTO;
import com.openaeolus.hub.services.WeatherReadingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather-reading")
public class WeatherReadingController {

    private final WeatherReadingService weatherReadingService;

    @Autowired
    public WeatherReadingController(WeatherReadingService weatherReadingService) {
        this.weatherReadingService = weatherReadingService;
    }

    @PostMapping("/send")
    public ResponseEntity<WeatherReadingDTO> sendWeatherReading(@Valid @RequestBody final WeatherReadingDTO weatherReading) {
        return ResponseEntity.ok(weatherReadingService.save(weatherReading));
    }

}
