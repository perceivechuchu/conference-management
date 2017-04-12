package com.mycompany.conferencemanagement.util;

public class TimeFormatter {

	public static String formatTime(int minutes) {

		String formattedTime = "";
		String formattedMinutes = "";

		int hours = minutes / 60;

		String formattedHours = Integer.toString(hours);

		if (hours > 12) {

			formattedHours = Integer.toString(hours - 12);
		}

		if (formattedHours.length() == 1) {
			formattedHours = "0" + formattedHours;
		}

		minutes = minutes - (hours * 60);

		if (minutes == 0) {
			formattedMinutes = formattedMinutes + "00";
		} else if (minutes < 10) {
			formattedMinutes = "0" + minutes;
		} else {
			formattedMinutes = formattedMinutes + minutes;
		}

		if (hours < 12) {
			formattedTime = formattedTime + " AM";
			if (formattedHours.equals("00")) {
				formattedTime = formattedTime + "12";
			}
		} else {
			formattedTime = " PM";
		}
		formattedTime = formattedHours + ":" + formattedMinutes + formattedTime;

		return formattedTime;
	}
}
