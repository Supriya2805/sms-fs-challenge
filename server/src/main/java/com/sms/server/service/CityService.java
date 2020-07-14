package com.sms.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.server.exception.CityNotFoundException;
import com.sms.server.exception.NoDataFoundException;
import com.sms.server.model.City;
import com.sms.server.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	CityRepository cityRepository;

	public List<City> getCities() {
		List<City> cities =  cityRepository.findAll();
		if(cities.isEmpty()) {
			throw new NoDataFoundException();
		}
		return cities;
	}
	
	public City addCity(City city) {
		return cityRepository.save(city);
	}
	
	public void addAll(List<City> entities) {
		cityRepository.saveAll(entities);
	}
	
	public Optional<City> findById(int id) {
		return cityRepository.findById(id);
	}
	
	public City updateCity(City newCity, int id) {
		return cityRepository.findById(id).map(city -> {
			city.setName(newCity.getName());
			city.setStartDate(newCity.getStartDate());
			city.setEndDate(newCity.getEndDate());
			return cityRepository.save(city);
		}).orElseThrow(() -> {
			throw new CityNotFoundException(id);
		});
	}
	
	public void deleteCity(int id) {
		cityRepository.deleteById(id);
	}
}
