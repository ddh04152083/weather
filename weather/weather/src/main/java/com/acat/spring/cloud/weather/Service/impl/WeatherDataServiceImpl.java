package com.acat.spring.cloud.weather.Service.impl;

import com.acat.spring.cloud.weather.Service.WeatherDataService;
import com.acat.spring.cloud.weather.vo.WeatherReponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 接口实现类
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    private static final long TIME_OUT=1800L;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public WeatherReponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherReponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeather(uri);
    }

    @Override
    public void syncDateByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放入缓存
     * @param uri
     */
    private void saveWeatherData(String uri){
        String key=uri;
        String strBody = null;
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        ValueOperations<String,String> ops=stringRedisTemplate.opsForValue();

            //再调用服务接口来获取
            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
            }
            //数据写入缓存
            ops.set(key,strBody,TIME_OUT,TimeUnit.SECONDS);


    }

    private WeatherReponse doGetWeather(String uri) {
        String key=uri;
        String strBody = null;
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        WeatherReponse resp = null;
         ValueOperations<String,String> ops=stringRedisTemplate.opsForValue();
         //先查缓存，缓存有的取缓存中的数据
        if (stringRedisTemplate.hasKey(key)){
            logger.info("redis has data");
           strBody = ops.get(key);
        }else {
            logger.info("redis don't has data");
            //缓存没有，再调用服务接口来获取
            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
            }
          //数据写入缓存
            ops.set(key,strBody,TIME_OUT,TimeUnit.SECONDS);
        }
        try {
            resp = mapper.readValue(strBody, WeatherReponse.class);
        } catch (IOException e) {
           logger.error("error");

        }

        return resp;
    }

}
