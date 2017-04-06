package home.model;

import java.sql.Timestamp;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_id")
	private long id;

	@Column(nullable = false)
	private String title;

	@Column
	private String eventDescription;

	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private Double rating;

	@Column
	private Timestamp startDate;

	@Column
	private Timestamp endDate;

	// @Column
	// Should be foreign key to address entity
	// private String location;

	// @Column
	// should be foreign key to organizer
	// private String contactEmail;

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

	public Event(String title, String eventDescription, Double rating, Timestamp startDate, Timestamp endDate) {
		super();
		this.title = title;
		this.eventDescription = eventDescription;
		this.rating = rating;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public long getId() {
		return id;
	}
}
