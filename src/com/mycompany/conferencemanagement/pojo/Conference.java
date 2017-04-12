package com.mycompany.conferencemanagement.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Perceive Chuchu
 *
 */
public class Conference {

	private List<Track> tracks = new ArrayList<Track>();

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
	@Override
	public String toString(){
		String track = "";
		for(Track trackInner : getTracks()){
			track = track  + "\n\n\n"+trackInner.getName()+": \n"+ trackInner;
		}
		return track;
	}
	
}
