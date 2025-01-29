package com.openaeolus.hub.repositories;

import com.openaeolus.hub.models.WeatherReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherReadingRepository extends JpaRepository<WeatherReading, Long> {
}
