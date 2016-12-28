/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendar.dao;

import com.calendar.pojo.Event;
import com.calendar.pojo.EventLocation;
import com.calendar.pojo.EventName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kevinbullis
 */
public class EventDao extends Dao {

	private static final Logger logger = LoggerFactory.getLogger(EventDao.class);

	public EventDao() throws NamingException {
		super("jdbc/calendar");
	}

	public List<Event> getEvents(String enteredDate) throws SQLException, NamingException {
		List<Event> events = new LinkedList<>();
		/**
		 * A connection to the database. Connections are used to execute SQL.
		 */
		Connection connection = null;
		/**
		 * A SQL statement to execute against the database.
		 */
		PreparedStatement stmt = null;
		/**
		 * The results of the SQL execution.
		 */
		ResultSet rs = null;
		try {
			//Use the data source to get a connection
			connection = ds.getConnection();
			//Use the connection to create a SQL statement
			stmt = connection.prepareStatement("SELECT * FROM calendar.event inner join calendar.eventLocation on event.location_id = eventLocation.location_id inner join calendar.eventName on event.name_id = eventName.name_id where date like ?");
			//Fill in the ? parameter in the SQL statement above with real data.
			stmt.setString(1, enteredDate);
			//Put the SQL statement about to execute into the log file.
			logger.debug("SQL: {}", stmt.toString());
			//Execute the SQL statement and get the result data.
			rs = stmt.executeQuery();
			//Check if the result data has at least 1 row of data.
			while (rs.next()) {
				//Call our private utility method to construct an Event POJO instance from the result data.
				Event event = getEvent(rs);
				events.add(event);
			}
			return events;
		} finally {
			/**
			 * finally blocks like this are where cleanup activities go, 
			 * since finally blocks are always executed no matter what.
			 */
			closeResources(rs, stmt, connection);
		}
	}

	//private function that returns a single episode, so named singular. getEvents puts this in a loop plural events.
	private Event getEvent(ResultSet rs) throws SQLException, NamingException {
		Event event = new Event();
		event.setEventId(rs.getInt("id"));
		event.setDescription(rs.getString("description"));
		event.setDate(rs.getDate("date"));
		event.setLocationId(rs.getInt("location_id"));
		event.setNameId(rs.getInt("name_id"));
		EventLocationDao locationDao = new EventLocationDao();
		EventLocation location = locationDao.getLocation(rs);
		EventNameDao nameDao = new EventNameDao();
		EventName name = nameDao.getName(rs);
		event.setLocation(location);
		event.setName(name);
		return event;
	}

}
