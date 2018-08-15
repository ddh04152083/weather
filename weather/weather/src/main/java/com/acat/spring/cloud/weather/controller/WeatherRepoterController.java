package com.acat.spring.cloud.weather.controller;

import com.acat.spring.cloud.weather.Service.CityDataService;
import com.acat.spring.cloud.weather.Service.WeatherRepoterService;
import com.acat.spring.cloud.weather.vo.WeatherReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/report")
public class WeatherRepoterController {
    @Autowired
    private CityDataService cityDataService;
    @Autowired
    private WeatherRepoterService weatherRepoterService;

    @GetMapping("cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable(value = "cityId") String cityId, Model model) throws Exception {
        model.addAttribute("title", "我是你海哥哥的天气预报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityDataService.listCity());
        model.addAttribute("report", weatherRepoterService.getDataByCityId(cityId));
        return new ModelAndView("weather/report", "reportModel", model);
    }

}
