package com.openaeolus.hub.services;

import com.openaeolus.hub.models.WeatherReading;
import com.openaeolus.hub.models.dtos.WeatherReadingDTO;
import com.openaeolus.hub.repositories.WeatherReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReadingService {

    private final WeatherReadingRepository weatherReadingRepository;

    @Autowired
    public WeatherReadingService(WeatherReadingRepository weatherReadingRepository) {
        this.weatherReadingRepository = weatherReadingRepository;
    }

    public WeatherReadingDTO save(WeatherReadingDTO weatherReading) {
        return new WeatherReadingDTO(this.weatherReadingRepository.save(new WeatherReading(weatherReading)));
    }
}
