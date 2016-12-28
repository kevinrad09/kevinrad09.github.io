package com.calendar.pojo;

import java.util.Date;

/**
 *
 * @author kevinbullis
 */
public class Event {

	private int eventId;
	private String description;
	private Date date;
	private int locationId;
	private int nameId;

	private EventLocation location;
	private EventName name;
	
	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the nameId
	 */
	public int getNameId() {
		return nameId;
	}

	/**
	 * @param nameId the nameId to set
	 */
	public void setNameId(int nameId) {
		this.nameId = nameId;
	}

	/**
	 * @return the location
	 */
	public EventLocation getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(EventLocation location) {
		this.location = location;
	}

	/**
	 * @return the name
	 */
	public EventName getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(EventName name) {
		this.name = name;
	}

	
}
