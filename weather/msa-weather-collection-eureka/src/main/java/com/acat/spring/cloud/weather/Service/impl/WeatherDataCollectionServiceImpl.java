package com.acat.spring.cloud.weather.Service.impl;

import com.acat.spring.cloud.weather.Service.WeatherDataCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.concurrent.TimeUnit;

@Service
@SuppressWarnings("all")
public class WeatherDataCollectionServiceImpl implements WeatherDataCollectionService {
    private static final long TIME_OUT=1800L;
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataCollectionServiceImpl.class);

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
        @Override
        public void sysncDataByCityId(String cityId) {
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

}
