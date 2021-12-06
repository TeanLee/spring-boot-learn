package com.example.demo.mapper;

import com.example.demo.Bean.City;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {
    public City getCity(int id);

    public City updateCity(City city);

    public void insertCity(City city);
}
