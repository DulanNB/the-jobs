package com.codewithdulan.thejobs.model;

public class appoinment {

	   private int id;
	   private String appoinment_note;
	   private int user_id;
	   private int consultant_id;
	   private String appoinment_date;
	   private String appoinment_time;
	   private String country;
	   private String consultant;
	   private String status;
	 
	   public appoinment() {

	   }
	   
	public appoinment(int id, String appoinment_note, int user_id, int consultant_id, String country, String appoinment_date,String appoinment_time,String consultant,String status) {
		super();
		this.id = id;
		this.appoinment_note = appoinment_note;
		this.user_id = user_id;
		this.consultant_id = consultant_id;
		this.country = country;
		this.appoinment_date = appoinment_date;
		this.appoinment_time = appoinment_time;
		this.consultant = consultant;
		this.status = status;
	}
	
	
	public int getAppoinmentID() {
		return id;
	}
	
	public void setAppoinmentID(int id) {
		this.id = id;
	}
	
	public String getAppoinmentNote() {
		return appoinment_note;
	}
	
	public void setAppoinmentNote(String appoinment_note) {
		this.appoinment_note = appoinment_note;
	}
	
	public int getUserID() {
		return user_id;
	}
	
	public void setUserID(int user_id) {
		this.user_id = user_id;
	}
	
	public int getConsultantId() {
		return consultant_id;
	}
	
	public void setConsultantId(int consultant_id) {
		this.consultant_id = consultant_id;
	}
	
	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}
	
	public String getConsultant() {
		return consultant;
	}
	
	public String getAppoinmentDate() {
		return appoinment_date;
	}
	
	public void setAppoinmentDate(String appoinment_date) {
		this.appoinment_date = appoinment_date;
	}
	public String getAppoinmentTime() {
		return appoinment_time;
	}
	
	public void setAppoinmentTime(String appoinment_time) {
		this.appoinment_time = appoinment_time;
	}
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
