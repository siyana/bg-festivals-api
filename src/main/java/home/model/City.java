package home.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "city")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "city_id")
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String country;
	
	@OneToMany(mappedBy="city")
	@JsonManagedReference
	private List<Address> addresses;
	
	public City() {
		super();
	}

	public City(String name, String country) {
		super();
		this.name = name;
		this.country = country;
	}

	public City(String name, String country, List<Address> addresses) {
		super();
		this.name = name;
		this.country = country;
		this.addresses = addresses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Long getId() {
		return id;
	}
	
	
}
