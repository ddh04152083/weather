package com.acat.spring.cloud.weather.Service;

import com.acat.spring.cloud.weather.vo.City;

import java.util.List;

/**
 * 城市数据服务接口
 */
public interface CityDataService {
    /**
     * 获取City列表
     * @return
     * @throws Exception
     */
    List<City> listCity() throws Exception;
}
