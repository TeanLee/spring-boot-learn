package com.example.demo.controller;

import com.example.demo.Bean.City;
import com.example.demo.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class CityController {
    @Autowired
    CityService cityService;

    @ResponseBody
    @GetMapping("/city")
    public City getCity(@RequestParam("id") int id) {
        return cityService.getCity(id);
    }

    @ResponseBody
    @PostMapping("/city/update")
    public City updateCity(City city) {
        log.info("修改成功！");
        return cityService.updateCity(city);
    }

    @ResponseBody
    @PostMapping("/city/insert")
    public void insertCity(City city) {
        cityService.insertCity(city);
    }
}
