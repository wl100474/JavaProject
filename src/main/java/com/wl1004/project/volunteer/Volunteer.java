package com.wl1004.project.volunteer;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Volunteer {
	private int v_no;
	private String v_writer;
	private String v_type;
	private String v_title;
	private String v_content;
	private Date v_date;
	private int v_time;
	private String v_area;
	private int v_recruit;
	private int v_staff;
	private int v_status;
	private String v_chat;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date v_regdate;
	
	public Volunteer() {
		// TODO Auto-generated constructor stub
	}

	public Volunteer(int v_no, String v_writer, String v_type, String v_title, String v_content, Date v_date,
			int v_time, String v_area, int v_recruit, int v_staff, int v_status, String v_chat, Date v_regdate) {
		super();
		this.v_no = v_no;
		this.v_writer = v_writer;
		this.v_type = v_type;
		this.v_title = v_title;
		this.v_content = v_content;
		this.v_date = v_date;
		this.v_time = v_time;
		this.v_area = v_area;
		this.v_recruit = v_recruit;
		this.v_staff = v_staff;
		this.v_status = v_status;
		this.v_chat = v_chat;
		this.v_regdate = v_regdate;
	}

	public int getV_no() {
		return v_no;
	}

	public void setV_no(int v_no) {
		this.v_no = v_no;
	}

	public String getV_writer() {
		return v_writer;
	}

	public void setV_writer(String v_writer) {
		this.v_writer = v_writer;
	}

	public String getV_type() {
		return v_type;
	}

	public void setV_type(String v_type) {
		this.v_type = v_type;
	}

	public String getV_title() {
		return v_title;
	}

	public void setV_title(String v_title) {
		this.v_title = v_title;
	}

	public String getV_content() {
		return v_content;
	}

	public void setV_content(String v_content) {
		this.v_content = v_content;
	}

	public Date getV_date() {
		return v_date;
	}

	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}

	public int getV_time() {
		return v_time;
	}

	public void setV_time(int v_time) {
		this.v_time = v_time;
	}

	public String getV_area() {
		return v_area;
	}

	public void setV_area(String v_area) {
		this.v_area = v_area;
	}

	public int getV_recruit() {
		return v_recruit;
	}

	public void setV_recruit(int v_recruit) {
		this.v_recruit = v_recruit;
	}

	public int getV_staff() {
		return v_staff;
	}

	public void setV_staff(int v_staff) {
		this.v_staff = v_staff;
	}

	public int getV_status() {
		return v_status;
	}

	public void setV_status(int v_status) {
		this.v_status = v_status;
	}

	public String getV_chat() {
		return v_chat;
	}

	public void setV_chat(String v_chat) {
		this.v_chat = v_chat;
	}

	public Date getV_regdate() {
		return v_regdate;
	}

	public void setV_regdate(Date v_regdate) {
		this.v_regdate = v_regdate;
	}

}
