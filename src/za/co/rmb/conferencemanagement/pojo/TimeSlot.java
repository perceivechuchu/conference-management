package za.co.rmb.conferencemanagement.pojo;

/**
 * @author Perceive Chuchu
 *
 */
public class TimeSlot {

	private String startTime;
	
	private String description;
	
	private int duration;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String toString(){
		return this.getStartTime()+" "+this.getDescription();
	}
}
