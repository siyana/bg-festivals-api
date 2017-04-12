package home.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private Long id;
	
	@Column
	private String street;
	
	@Column
	private String zip;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="city_id")
	@JsonBackReference
	private City city;
	
	public Address(){
		super();
	}

	public Address(String street, String zip, City city) {
		super();
		this.street = street;
		this.zip = zip;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

//	public void setStreet(String street) {
//		this.street = street;
//	}

	public String getZip() {
		return zip;
	}

//	public void setZip(String zip) {
//		this.zip = zip;
//	}

	public City getCity() {
		return city;
	}

//	public void setCity(City city) {
//		this.city = city;
//	}

	public Long getId() {
		return id;
	}	
	
	
}
