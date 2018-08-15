package com.acat.spring.cloud.weather.job;

import com.acat.spring.cloud.weather.Service.CityDataService;
import com.acat.spring.cloud.weather.Service.WeatherDataService;
import com.acat.spring.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.slf4j.Logger;


import java.util.List;


/**
 * 天气数据同步
 */

public class WeatherDataSyncJob extends QuartzJobBean {
    @Autowired
    private CityDataService cityDataService;
    @Autowired
    private WeatherDataService weatherDataService;
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("Weather Data Sync Job Start");
        //获取城市ID列表
        List<City> cityList = null;
        try {
           cityList= cityDataService.listCity();
        } catch (Exception e) {
            logger.error("Exception !", e);
        }

        for (City city : cityList) {
            String cityId = city.getCityId();
            logger.info("Weather Data Sync Job,cityId:" + cityId);
            weatherDataService.syncDateByCityId(cityId);
        }
        logger.info("Weather Data Sync Job.End!");
    }
}
