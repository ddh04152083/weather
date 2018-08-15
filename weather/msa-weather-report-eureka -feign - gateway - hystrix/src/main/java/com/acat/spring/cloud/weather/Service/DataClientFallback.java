package com.acat.spring.cloud.weather.Service;

import com.acat.spring.cloud.weather.vo.City;
import com.acat.spring.cloud.weather.vo.WeatherReponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataClientFallback implements DataClient {
    @Override
    public List<City> listCity() throws Exception {
        List<City> cityList = null;
        cityList = new ArrayList<>();
        City city = new City();
        city.setCityId("101110101");
        city.setCityName("西安");
        cityList.add(city);
        city=new City();
        city.setCityId("101110102");
        city.setCityName("长安");
        cityList.add(city);
        return cityList;
    }

    @Override
    public WeatherReponse getDataByCityId(String cityId) {
        return null;
    }
}
