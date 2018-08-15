package com.acat.spring.cloud.weather.Service.impl;

import com.acat.spring.cloud.weather.Service.WeatherDataClient;
import com.acat.spring.cloud.weather.Service.WeatherRepoterService;
import com.acat.spring.cloud.weather.vo.Forecast;
import com.acat.spring.cloud.weather.vo.Weather;
import com.acat.spring.cloud.weather.vo.WeatherReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherRepoterServiceImpl implements WeatherRepoterService {
    @Autowired
    private WeatherDataClient weatherDataClient;

    /**
     * @param cityId
     * @return
     */
    @Override
    public Weather getDataByCityId(String cityId) {
        //由天气预报API微服务来提供数据
        WeatherReponse resp = weatherDataClient.getDataByCityId(cityId);
        Weather data = resp.getData();
        return data;
    }
}
