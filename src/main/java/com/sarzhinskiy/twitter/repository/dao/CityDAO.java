package com.sarzhinskiy.twitter.repository.dao;

import java.util.List;

import com.sarzhinskiy.twitter.domain.city.City;

public interface CityDAO {
	public boolean create(City city);
	public boolean update(City city);
	public City findById(Integer id);
	public List<City> findByName(String name);
	public List<City> findByCountry(String country);
	public List<City> findAll();
	public boolean remove(Integer id);
	public boolean remove(String name);
	public boolean remove(String cityName, String countryName);
	public boolean removeAll();

}
