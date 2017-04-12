package com.mycompany.conferencemanager.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.mycompany.conferencemanagement.pojo.Conference;
import com.mycompany.conferencemanagement.pojo.Session;
import com.mycompany.conferencemanagement.pojo.TimeSlot;
import com.mycompany.conferencemanagement.pojo.Track;
import com.mycompany.conferencemanagement.util.Constant;
import com.mycompany.conferencemanagement.util.FileParser;
import com.mycompany.conferencemanagement.util.TimeFormatter;


/**
 * @author Perceive Chuchu
 *
 */
public class ConferenceScheduler {

	public static int NEXT_START_TIME= Constant.MORNING_SESSION_START_TIME;
	public static int ITERATION_COUNT = 1;

	
	public static void main(String[] args){
		File inputFile = null;
		try {
			inputFile = new File(Constant.INPUT_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<TimeSlot> timeSlots = FileParser.parseFile(inputFile);
		
		//Shuffle the proposals
		Collections.shuffle(timeSlots);
		
		if(timeSlots.isEmpty()){
			System.out.println("##### Please add the Input File entries first.");
			return;
		}
		
		Conference conference = scheduleConference(timeSlots);
		System.out.println(conference);
	}

	public static Conference scheduleConference(List<TimeSlot> timeSlots){

		Conference conference = new Conference();
		
		Track track = null;
		
		while(timeSlots.size()!=0){

			//Morning Session
			Session morningSession = new Session(Constant.MORNING_SESSION, Constant.MORNING_SESSION_START_TIME, Constant.MORNING_SESSION_DURATION);
			addTimeSlotToSession(morningSession, timeSlots);

			//Lunch Time
			Session lunchTime = new Session(Constant.LUNCH, Constant.LUNCH_SESSION_START_TIME, Constant.LUNCH_SESSION_DURATION);
			TimeSlot timeSlotLunch = new TimeSlot();
			timeSlotLunch.setDescription(Constant.LUNCH);
			timeSlotLunch.setStartTime(TimeFormatter.formatTime(Constant.LUNCH_SESSION_START_TIME));
			lunchTime.addTimeSlot(timeSlotLunch);

			//Afternoon Session
			Session afternoonSession = new Session(Constant.AFTERNOON_SESSION, Constant.AFTERNOON_SESSION_START_TIME, Constant.AFTERNOON_SESSION_DURATION);
			addTimeSlotToSession(afternoonSession, timeSlots);

			//Networking Event
			Session networkingEvent = new Session(Constant.NETWORKING_EVENT, 0, 0);
			TimeSlot timeSlotNetworkEvent = new TimeSlot();
			timeSlotNetworkEvent.setDescription(Constant.NETWORKING_EVENT);
			timeSlotNetworkEvent.setStartTime(TimeFormatter.formatTime(NEXT_START_TIME));
			networkingEvent.addTimeSlot(timeSlotNetworkEvent);

			//Populating Track
			track = new Track();
			track.setName("Track "+ITERATION_COUNT);
			track.getSessions().add(morningSession);
			track.getSessions().add(lunchTime);
			track.getSessions().add(afternoonSession);
			track.getSessions().add(networkingEvent);

			//Adding Track to Conference
			conference.getTracks().add(track);

			ITERATION_COUNT = ITERATION_COUNT + 1;
		}
		return conference;
	}

	private static void addTimeSlotToSession(Session session, List<TimeSlot> timeSlots) {
		for (Iterator<TimeSlot> iter = timeSlots.iterator(); iter.hasNext();) {
			TimeSlot timeSlot = iter.next();
			if (session.getRemainingTime()>=timeSlot.getDuration()) {
				timeSlot.setStartTime(TimeFormatter.formatTime(session.getNextStartTime()));
				session.addTimeSlot(timeSlot);
				NEXT_START_TIME = session.getNextStartTime();
				iter.remove();
			}
		}
	}
}
