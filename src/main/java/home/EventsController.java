package home;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/events")
public class EventsController {

	@Autowired
	private EventsRepository eventsRepository;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
	
	/**
	 * @category public
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Event> events() {
		List<Event> events = null;
		try {
			events = eventsRepository.findAll();
		} catch (Exception e) {
			return null;
		}
		return events;
		}

	/**
	 * @category Crud operations
	 */
	@RequestMapping(value = "/event", method = RequestMethod.POST)
	@ResponseBody
	public String create(String title, String eventDescription, Double rating, String startDate, String endDate) {
		String eventId = "";
		try {		    
			Event event = new Event(title, eventDescription, rating, formattedTimestamp(startDate), formattedTimestamp(endDate));			
			eventsRepository.save(event);
			eventId = String.valueOf(event.getId());
		} catch (Exception ex) {
			return "Error creating event: " + ex.toString();
		}

		return "Event successfully created with id: " + eventId;
	}

	@RequestMapping(value = "/event", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(Long id) {
		try {
			eventsRepository.delete(id);
		} catch (Exception ex) {
			return "Error deleting event: " + ex.toString();
		}

		return "Event successfully deleted.";
	}
	
	@RequestMapping(value = "/event", method = RequestMethod.PUT)
	@ResponseBody
	public String update(Long id, String title, String eventDescription, Double rating, String startDate, String endDate) {
		try {
			Event event = eventsRepository.findOne(id);
			if (title != null) {				
				event.setTitle(title);
			}
			
			if (eventDescription != null) {
				event.setEventDescription(eventDescription);
			}
			
			if (rating != null) {
				event.setRating(rating);
			}
			
			if (startDate != null) { 
				event.setStartDate(formattedTimestamp(startDate));
			}
			
			if (endDate != null) {
				event.setEndDate(formattedTimestamp(endDate));
			}
			
			eventsRepository.save(event);
			
		} catch (Exception ex) {
			return "Error updating event: " + ex.toString();
		}

		return "Event successfully updated.";
	}
	
	/**
	 * @throws ParseException 
	 * @category Helpers
	 */
	
	private Timestamp formattedTimestamp(String dateString) throws ParseException {
		Date parsedDate = (Date) dateFormat.parse(dateString);
	    Timestamp timestamp = new Timestamp(parsedDate.getTime());
	    return timestamp;
	}

//	@RequestMapping("/update_location")
//	@ResponseBody
//	public String updateLocation(Long id, String location) {
//		try {
//			Event event = eventsRepository.findOne(id);
//			event.setLocation(location);
//			eventsRepository.save(event);
//		} catch (Exception ex) {
//			return "Error updating event: " + ex.toString();
//		}
//
//		return "Event successfully updated.";
//	}

//	@RequestMapping("/find_by_location")
//	@ResponseBody
//	public List<Event> getByLocation(String location) {
//		List<Event> events = null;
//		try {
//			events = eventsRepository.findByLocation(location);
//		} catch (Exception ex) {
//			return null;
//		}
//
//		return events;
//	}
}
