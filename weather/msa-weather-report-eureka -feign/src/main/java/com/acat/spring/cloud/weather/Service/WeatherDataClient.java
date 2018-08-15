package com.acat.spring.cloud.weather.Service;

import com.acat.spring.cloud.weather.vo.WeatherReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("msa-weather-city-eureka")
public interface WeatherDataClient {
    @GetMapping("weather/cityId/{cityId}")
    WeatherReponse getDataByCityId(@PathVariable("cityId")String cityId);
}
