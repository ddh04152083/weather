package com.acat.spring.cloud.weather.Service;

/**
 * 天气数据收集
 */
public interface WeatherDataCollectionService {
     void sysncDataByCityId(String cityId);
}
