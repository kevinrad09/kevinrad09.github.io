/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendar.web;

import com.calendar.dao.EventDao;
import com.calendar.dao.EventLocationDao;
import com.calendar.dao.EventNameDao;
import com.calendar.pojo.Event;
import com.calendar.pojo.EventLocation;
import com.calendar.pojo.EventName;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kevinbullis
 */
@WebServlet(name = "Calendar", urlPatterns = {"/calendar"})
public class CalendarServlet extends HttpServlet {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CalendarServlet.class);

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//define variables
			//get month value the user selected
			String month = request.getParameter("month");
			//get year value the user selected
			String year = request.getParameter("year");
			//create a string I can plug into the SQL select statement
			String enteredDate = year + "-" + month + "-%";
			int retrievedLocationId;
			int retrievedNameId;
			
			//create DAOs
			EventDao eventDao = new EventDao();
			
			//find events by the enteredDate
			List<Event> events = eventDao.getEvents(enteredDate);
			
			/**
			 * What comes from eventDao.getEvents() is a list with sets of variables with different values, I think [
			 * {eventId = 1, description = "Described by Brecht . . .", date = 2016-10-14, locationId = 1, nameId = 1},
			 * {eventId = 2, description = "Step back in time . . .",date = 2016-11-30, locationId = 2, nameId = 2},
			 * {eventId = 3, description = "View new work from . . .",date = 2015-09-01, locationId = 3, nameId = 3} ]
			 *
			 */
			//make an empty JS array called eventsJson.
			JSONArray eventsJson = new JSONArray();

			//for loop shorthand will loop through each list item (event) in events (a list created by getEvents()).
			for (Event event : events) {
				//create an empty JS object that can take set of key value pairs.
				JSONObject eventJson = new JSONObject();

				//set some variables that set the format for the date results we got from SQL
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				String date = dateFormat.format(event.getDate());

				/*now pair a key "id" with a value (get the value of EventId from the pojo)
					and add that pair to the JS object we just made.*/
				//this actually makes the stuff in parentheses a parameter for an instancd of JSONObject();
				eventJson.put("id", event.getEventId());
				//and so on for description and the other columns
				eventJson.put("description", event.getDescription());
				eventJson.put("location_id", event.getLocationId());//don't really need this in the JSON.
				eventJson.put("name_id", event.getNameId());//I don't really need this in the JSON.
				eventJson.put("date", date);

				EventLocation eventLocation = event.getLocation();
				EventName eventName = event.getName();

				eventJson.put("venue", eventLocation.getVenue());
				eventJson.put("street", eventLocation.getStreet());
				eventJson.put("city", eventLocation.getCity());
				eventJson.put("state", eventLocation.getState());
				eventJson.put("name", eventName.getName());

				//add this object to the JS array we created above.
				eventsJson.put(eventJson);
				//then do that again until you've gone through the whole list we got from the POJO
			}
			/*
			Now we should have an array that looks something like this:
			[
				{id: 1, description: "Described by Brecht . . .", date: 2016-10-14, location_id: 1, name_id: 1},
				{id: 2, description: "Step back in time . . .",date: 2016-11-30}, location_id: 2, name_id: 2},
				{id: 3, description: "View new work from . . .",date: 2015-09-01}, location_id: 3, name_id: 3}
			]
			 */

 /*
			Use the response parameter to send the JS object back to the web page that did the request, 
			in a string format JS can understand.
			 */
			response.getWriter().write(eventsJson.toString());
		} catch (NamingException | SQLException ex) {
			logger.error("Could not process request.", ex);
		}

	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);/*this said we need try and catch here. Not sure why that wasn't included, 
		so maybe the screwup is elsewhere. Changed below in the doPush part. Leaving it here as is until I figure out 
		if something I added or changed messed it up.
		Aha. I threw a naming exception above. That must be it.
		*/
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
