package home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.model.Address;
import home.model.AddressesRepository;
import home.model.City;

@Service
public class AddressService {
	
	@Autowired
	private AddressesRepository repository;
	
	public String saveAddress(String street, String zip, City city) {
		try {
			Address address = new Address(street, zip, city);
			repository.save(address);
				
		} catch (Exception ex) {
			return "Error creating city: " + ex.toString();
		}

		return "Address successfully created";
	}

}
