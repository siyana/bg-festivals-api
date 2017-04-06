package home.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CitiesRepository extends CrudRepository<City, Long> {
	public List<City> findAll();
	
	/**
	 * 
	 * @param country
	 * @return All cities in the country
	 */
	public List<City> findByCountry(String country);
}
