package home.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AddressesRepository extends CrudRepository<Address, Long> {

	public List<Address> findAll();
	/**
	 * 
	 * @param cityId
	 * @return List all addresses in a city with given id
	 */
	public List<Address> findByCity_Id(Long cityId);
}
