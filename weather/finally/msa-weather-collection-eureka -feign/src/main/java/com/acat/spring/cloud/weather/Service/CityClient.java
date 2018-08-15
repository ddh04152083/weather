package com.acat.spring.cloud.weather.Service;

import com.acat.spring.cloud.weather.vo.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@FeignClient("msa-weather-city-eureka")
public interface CityClient {
    @GetMapping("/cities")
    List<City> listCity() throws Exception;
}
