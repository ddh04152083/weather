package com.acat.spring.cloud.weather.controller;

import com.acat.spring.cloud.weather.service.CityClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    @Autowired
    private CityClient cityClient;
    @GetMapping("/cities")
    @HystrixCommand(fallbackMethod = "defaultCities")
    public String cities() {
        String hello = cityClient.listCity();
        return hello;
    }

    public String defaultCities(){
        return "City data Server is down!";
    }
}
