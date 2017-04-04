package home;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {

	@Autowired
	private EventsRepository eventsRepository;

	private final AtomicLong counter = new AtomicLong();
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private DataManager dataManager;

	/**
	 * @category public
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/events")
	@ResponseBody
	public List<Event> events() throws Exception {
		return getEvents();
	}

	/**
	 * @category Crud operations
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String create(String title, Date startDate, Date endDate, String eventDescription, Double rating, String location,
			String contactEmail, Boolean isSelected) {
		String eventId = "";
		try {
			Event event = new Event(title, startDate, endDate, eventDescription, rating, location, contactEmail, isSelected);
			eventsRepository.save(event);
			eventId = String.valueOf(event.getId());
		} catch (Exception ex) {
			return "Error creating event: " + ex.toString();
		}

		return "Event successfully created with id: " + eventId;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Long id) {
		try {
			Event event = new Event(id);
			eventsRepository.delete(event);
		} catch (Exception ex) {
			return "Error deleting event: " + ex.toString();
		}

		return "Event successfully deleted.";
	}

	@RequestMapping("/update_location")
	@ResponseBody
	public String updateLocation(Long id, String location) {
		try {
			Event event = eventsRepository.findOne(id);
			event.setLocation(location);
			eventsRepository.save(event);
		} catch (Exception ex) {
			return "Error updating event: " + ex.toString();
		}

		return "Event successfully updated.";
	}

	@RequestMapping("/find_by_location")
	@ResponseBody
	public List<Event> getByLocation(String location) {
		List<Event> events = null;
		try {
			events = eventsRepository.findByLocation(location);
		} catch (Exception ex) {
			return null;
		}

		return events;
	}

	/**
	 * @category private
	 */
	private List<Event> getEvents() throws Exception {
		List<Event> events = null;
		try {
			events = eventsRepository.findAll();
		} catch (Exception e) {
			return null;
		}
		return events;
	}
}
