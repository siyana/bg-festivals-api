package home;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column
	private String eventDescription;
	
	@Column(columnDefinition="Decimal(10,2) default '0.00'")
	private Double rating;
	
	@Column
	private String location;
	
	@Column
	private String contactEmail;
	
	@Column
	private Boolean isSelected;		
	
	
	public Event() {
		super();
	}

	public Event(long id) {
		super();
		this.id = id;
	}

	public Event(String title, String eventDescription) {
		super();
		this.title = title;
		this.eventDescription = eventDescription;
	}


	public Event(String title, Date startDate, Date endDate, String eventDescription, Double rating,
			String location, String contactEmail, Boolean isSelected) {
		super();
		this.title = title;

		this.eventDescription = eventDescription;
		this.rating = rating;
		this.location = location;
		this.contactEmail = contactEmail;
		this.isSelected = isSelected;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public Double getRating() {
		return rating;
	}
	public String getLocation() {
		return location;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}	
}
