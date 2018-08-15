package com.acat.spring.cloud.weather.Service;

import com.acat.spring.cloud.weather.vo.Weather;

public interface WeatherRepoterService {
    /**
     * 根据城市Id查询天气信息
     * @param cityId
     * @return
     */
    Weather getDataByCityId(String cityId);
}
