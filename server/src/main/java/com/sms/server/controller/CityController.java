package com.sms.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sms.server.model.City;
import com.sms.server.service.CityService;

@RestController
public class CityController {

	@Autowired
	CityService cityService;

	@GetMapping("/cities")
	public List<City> getAllCities() {
		return cityService.getCities();
	}

	@PostMapping("/cities")
	public City addCity(@RequestBody City city) {
		return cityService.addCity(city);
	}

	@PutMapping("cities/{id}")
	public City updateCity(@RequestBody City city, @PathVariable int id) {
		return cityService.updateCity(city, id);
	}

	@DeleteMapping("/cities/{id}")
	public void deleteEmployee(@PathVariable int id) {
		cityService.deleteCity(id);
	}
}
