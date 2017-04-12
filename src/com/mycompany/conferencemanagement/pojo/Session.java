package com.mycompany.conferencemanagement.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Perceive Chuchu
 *
 */
public class Session {

	private String name;
	private int startTime;
	private int duration;
	private List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
	private int remainingTime;
	private int nextStartTime;

	public Session(String name, int startTime, int duration){
		this.name = name;
		this.startTime = startTime;
		this.duration = duration;
		this.remainingTime = duration;
		this.nextStartTime = startTime;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public void addTimeSlot(TimeSlot timeSlot){
		this.setRemainingTime( getRemainingTime() - timeSlot.getDuration());
		this.setNextStartTime(this.nextStartTime + timeSlot.getDuration());
		this.getTimeSlots().add(timeSlot);
	}


	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

	public int getNextStartTime() {
		return nextStartTime;
	}

	public void setNextStartTime(int nextStartTime) {
		this.nextStartTime = nextStartTime;
	}

	@Override
	public String toString(){
		String timeSlot = "";
		for(TimeSlot timeSlotInner : getTimeSlots()){
			timeSlot = timeSlot  + "\n"+ timeSlotInner;
		}
		return timeSlot;
	}

}
