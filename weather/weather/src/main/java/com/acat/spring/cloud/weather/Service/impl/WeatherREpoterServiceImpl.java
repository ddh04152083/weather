package com.acat.spring.cloud.weather.Service.impl;

import com.acat.spring.cloud.weather.Service.WeatherDataService;
import com.acat.spring.cloud.weather.Service.WeatherRepoterService;
import com.acat.spring.cloud.weather.vo.Weather;
import com.acat.spring.cloud.weather.vo.WeatherReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherREpoterServiceImpl implements WeatherRepoterService {
    @Autowired
    private WeatherDataService weatherDataService;

    /**
     *
     * @param cityId
     * @return
     */
    @Override
    public Weather getDataByCityId(String cityId) {
      WeatherReponse resp= weatherDataService.getDataByCityId(cityId);
      return resp.getData();
    }
}
