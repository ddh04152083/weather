package com.acat.spring.cloud.weather.controller;

import com.acat.spring.cloud.weather.Service.WeatherRepoterService;
import com.acat.spring.cloud.weather.vo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report")
public class WeatherRepoterController {

    @Autowired
    private WeatherRepoterService weatherRepoterService;

    @GetMapping("cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable(value = "cityId") String cityId, Model model) throws Exception {
      List<City> cityList=null;
        cityList=new ArrayList<>();
        City city=new City();
        city.setCityName("西安");
        city.setCityId("101010100");
        cityList.add(city);
        //TODO 由城市数据API微服务来提供数据
        model.addAttribute("title", "我是你海哥哥的天气预报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityList);
        model.addAttribute("report", weatherRepoterService.getDataByCityId(cityId));
        return new ModelAndView("weather/report", "reportModel", model);
    }

}
