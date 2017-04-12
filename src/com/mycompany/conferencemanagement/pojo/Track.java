package com.mycompany.conferencemanagement.pojo;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.conferencemanagement.util.Constant;


/**
 * @author perceive
 *
 */
public class Track {

	private List<Session> sessions = new ArrayList<Session>();
	private String name;
	private int nextStartTime;
	public Track(){

	}
	
	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNextStartTime() {
		return nextStartTime;
	}
	public void setNextStartTime(int nextStartTime) {
		this.nextStartTime = nextStartTime;
	}
	public Session getSessionByName(String name){
		Session session = null;
		for(Session innerSession : this.getSessions()){
			if(innerSession.getName().equals(name)){
				return innerSession;
			}
		}
		return session;
	}

	@Override
	public String toString(){
		String sessions = "";
		for(Session sessionInner : getSessions()){
			sessions = sessions  + ""+ sessionInner;
		}
		return sessions;
	}
}
