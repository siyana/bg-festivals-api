package home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataManager {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void createEventsTable() {
		getJdbcTemplate().execute("DROP TABLE IF EXISTS events;");
		getJdbcTemplate().execute("CREATE TABLE events(id SERIAL, "
				+ "title VARCHAR(255),"
				+ "eventDescription VARCHAR(255),"
				+ "rating DOUBLE PRECISION,"
				+ "location VARCHAR(255),"
				+ "contactEmail VARCHAR(255),"
				+ "isSelected BOOLEAN);");
	}
	
	
	public void batchUpdate(List<Object[]> events) {
        // Uses JdbcTemplate's batchUpdate operation to bulk load data
		getJdbcTemplate().batchUpdate("INSERT INTO events("
        		+ "title, eventDescription,"
        		+ "rating, location,"
        		+ "contactEmail, isSelected) "
        		+ "VALUES (?,?,?,?,?,?);", 
        		events);
	}
	
	public List<Event> allEvents() {
		return getJdbcTemplate().query(
                "SELECT id, title, eventDescription FROM events;",
                (rs, rowNum) -> new Event(rs.getString("title"), rs.getString("eventDescription"))
        );
	}
}
