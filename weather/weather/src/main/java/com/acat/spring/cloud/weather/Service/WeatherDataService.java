package com.acat.spring.cloud.weather.Service;

import com.acat.spring.cloud.weather.vo.WeatherReponse;

/**
 * 天气数据接口
 */
public interface WeatherDataService {
    /**
     * 根据城市Id 查询天气数据
     * @param cityId
     * @return
     */
    WeatherReponse getDataByCityId(String cityId);

    /**
     * 根据城市天气查询天气预报
     * @param cityName
     * @return
     */
    WeatherReponse getDataByCityName(String cityName);

    /**
     * 根据城市Id同步天气
     * @param cityId
     */
    void syncDateByCityId(String cityId);
}
