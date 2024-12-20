package model;
import java.util.Date;

public class MentalCheckResult {
	private int score;
	private Date date;
	private String formattedDate;
	
	 public MentalCheckResult(int score, Date date) {
	        this.score = score;
	        this.date = date;
	    }
	 
	 
	public int getScore() {return score;}
	public Date getDate() {return date;}
	public String getFormattedDate() {return formattedDate;}
	
	
	public void setScore(int score) {this.score = score;}
	public void setDate(Date date) {this.date = date;}
	public void setFormattedDate(String formattedDate) {this.formattedDate = formattedDate;}

}
