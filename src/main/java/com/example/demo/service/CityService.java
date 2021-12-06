package com.example.demo.service;

import com.example.demo.Bean.Account;
import com.example.demo.Bean.City;
import com.example.demo.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    CityMapper cityMapper;

    public City getCity(int id) {
        return cityMapper.getCity(id);
    }

    public City updateCity(City city) {
        return cityMapper.updateCity(city);
    }

    public void insertCity(City city) {
        cityMapper.insertCity(city);
    }
}
