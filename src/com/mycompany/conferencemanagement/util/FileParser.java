package com.mycompany.conferencemanagement.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mycompany.conferencemanagement.pojo.TimeSlot;


public class FileParser {

	public static List<TimeSlot> parseFile(File file){
		FileInputStream is=null;
		BufferedReader br=null;
		TimeSlot proposal = null;
		List<TimeSlot> proposals = null;
		String line = null;

		try {
			is = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(is));

			proposals = new ArrayList<TimeSlot>();
			while ((line = br.readLine()) != null) {
				proposal = populateProposal(line);
				proposals.add(proposal);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return proposals;

	}

	private static TimeSlot populateProposal(String line){
		TimeSlot timeSlot = null;

		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()){
			timeSlot = new TimeSlot();
			timeSlot.setDuration(Integer.parseInt(matcher.group()));
			timeSlot.setDescription(line);
		}else{
			timeSlot = new TimeSlot();
			timeSlot.setDuration(Constant.LIGHTING_PERIOD);
			timeSlot.setDescription(line);
		}

		return timeSlot;
	}

}
