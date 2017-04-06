package home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import home.model.CitiesRepository;
import home.model.City;

@RestController()
@RequestMapping("/cities")
public class CitiesController {

	@Autowired
	private CitiesRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<City> cities() {
		List<City> cities = null;
		try {
			cities = repository.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cities;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cities_by_country")
	@ResponseBody
	public List<City> citiesByCountry(String country) {
		List<City> cities = null;
		try {
			cities = repository.findByCountry(country);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cities;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/city")
	@ResponseBody
	public City cityById(Long id) {
		City city = null;
		try {
			city = repository.findOne(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return city;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/city")
	@ResponseBody
	public String create(String name, String country) {

		try {
			City city = new City(name, country);
			repository.save(city);		
		} catch (Exception ex) {
			return "Error creating city: " + ex.toString();
		}

		return "City successfully created";
	}

}
