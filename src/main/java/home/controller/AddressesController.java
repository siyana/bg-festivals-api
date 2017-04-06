package home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import home.model.Address;
import home.model.AddressesRepository;
import home.model.City;

@RestController()
@RequestMapping("/addresses")
public class AddressesController {
	@Autowired
	private AddressesRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Address> addresses() {
		List<Address> addresses = null;
		try {
			addresses = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addresses;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/addresses_by_city")
	@ResponseBody
	public List<Address> addressesByCity(Long cityId) {
		List<Address> addresses = null;
		try {
			addresses = repository.findByCity_Id(cityId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addresses;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/address")
	@ResponseBody
	public String create(String street, String zip, City city) {

		try {
			Address address = new Address(street, zip, city);
			repository.save(address);		
		} catch (Exception ex) {
			return "Error creating city: " + ex.toString();
		}

		return "Address successfully created";
	}

}
