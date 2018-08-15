package com.acat.spring.cloud.weather.Service.impl;

import com.acat.spring.cloud.weather.Service.WeatherRepoterService;
import com.acat.spring.cloud.weather.vo.Forecast;
import com.acat.spring.cloud.weather.vo.Weather;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherRepoterServiceImpl implements WeatherRepoterService {


    /**
     *
     * @param cityId
     * @return
     */
    @Override
    public Weather getDataByCityId(String cityId) {
        //TODO 改为由天气预报API微服务来提供数据
        Weather data=new Weather();
        data.setAqi("81");
        data.setCity("西安");
        data.setGanmao("ganmao");
        data.setWendu("34");
        List<Forecast> forecastList=new ArrayList<>();
        Forecast forecast=new Forecast();
        forecast.setDate("1日星期二");
        forecast.setFengli("mei");
        forecast.setHigh("34");
        forecast.setLow("24");
        forecastList.add(forecast);
        forecast=new Forecast();
        forecast.setDate("30日星期一");
        forecast.setFengli("mei");
        forecast.setHigh("34");
        forecast.setLow("24");
        forecastList.add(forecast);
        forecast=new Forecast();
        forecast.setDate("29日星期天");
        forecast.setFengli("mei");
        forecast.setHigh("34");
        forecast.setLow("24");
        forecastList.add(forecast);
        forecast=new Forecast();
        forecast.setDate("28日星期六");
        forecast.setFengli("mei");
        forecast.setHigh("34");
        forecast.setLow("24");
        forecastList.add(forecast);
        forecast=new Forecast();
        forecast.setDate("27日星期五");
        forecast.setFengli("mei");
        forecast.setHigh("34");
        forecast.setLow("24");
        forecastList.add(forecast);
        data.setForecast(forecastList);
//      WeatherReponse resp= weatherDataService.getDataByCityId(cityId);
        return data ;
    }
}
