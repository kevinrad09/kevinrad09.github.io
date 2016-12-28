/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendar.dao;

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
public class EventNameDao extends Dao {

	private static final Logger logger = LoggerFactory.getLogger(EventNameDao.class);

	public EventNameDao() throws NamingException {
		super("jdbc/calendar");
	}

	public EventName getName(int nameId) throws SQLException {
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
			stmt = connection.prepareStatement("SELECT * FROM calendar.eventName WHERE name_id=?");//what do I want to pass in here? And how?
			//Fill in the ? parameter in the SQL statement above with real data.
			stmt.setInt(1, nameId); 
			//Put the SQL statement about to execute into the log file.
			logger.debug("SQL: {}", stmt.toString());
			//Execute the SQL statement and get the result data.
			rs = stmt.executeQuery();
			//Check if the result data has at least 1 row of data.
			if (rs.next()) {
				//Call our private utility method to construct an Email POJO instance from the result data.
				EventName name = getName(rs);
				return name;
			}
			return null;//or should I return something more helpful?
		} finally {
			/**
			 * finally blocks like this are where cleanup activities go, 
			 * since finally blocks are always executed no matter what.
			 */
			closeResources(rs, stmt, connection);
		}
	}

	//private function that returns a single name, so named singular. getNames puts this in a loop plural names.
	//this class "EventName" is the pojo
	//okay. do I need this utility? 
	
	EventName getName(ResultSet rs) throws SQLException {
		EventName name = new EventName();
		name.setNameId(rs.getInt("name_id"));
		name.setName(rs.getString("name"));
		return name;
	}

}
