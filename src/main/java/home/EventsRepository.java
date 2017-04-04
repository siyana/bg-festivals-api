package home;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface EventsRepository extends CrudRepository<Event, Long> {
	
	// will list all events
	public List<Event> findAll();
	
	// will return events for location
	public List<Event> findByLocation(String location);
}
