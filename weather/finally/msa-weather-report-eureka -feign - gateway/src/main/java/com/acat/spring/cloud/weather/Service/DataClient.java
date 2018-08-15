package com.acat.spring.cloud.weather.Service;

import com.acat.spring.cloud.weather.vo.City;
import com.acat.spring.cloud.weather.vo.WeatherReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * msa-weather-eureka-client-zuul
 */
@Service
@FeignClient("msa-weather-eureka-client-zuul")
public interface DataClient {
    /**
     * 获取城市列表
     * @return
     * @throws Exception
     */

    @GetMapping("/city/cities")
    List<City> listCity() throws Exception;

    /**
     * 根据城市ID查询天气数据
     * @param cityId
     * @return
     */
    @GetMapping("/data/weather/cityId/{cityId}")
    WeatherReponse getDataByCityId(@PathVariable("cityId") String cityId);
}
