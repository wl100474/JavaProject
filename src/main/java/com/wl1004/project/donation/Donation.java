package com.wl1004.project.donation;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Donation {
	private int d_no;
	private String d_title;
	private String d_content;
	private String d_photo;
	private Date d_startDate;
	private Date d_endDate;
	private int d_goal;
	private int d_amount;
	private int d_status;
	private Date d_regdate;
	
	
	public Donation() {
		// TODO Auto-generated constructor stub
	}

	public Donation(int d_no, String d_title, String d_content, String d_photo, Date d_startDate, Date d_endDate,
			int d_goal, int d_amount, int d_status, Date d_regdate) {
		super();
		this.d_no = d_no;
		this.d_title = d_title;
		this.d_content = d_content;
		this.d_photo = d_photo;
		this.d_startDate = d_startDate;
		this.d_endDate = d_endDate;
		this.d_goal = d_goal;
		this.d_amount = d_amount;
		this.d_status = d_status;
		this.d_regdate = d_regdate;
	}


	public int getD_no() {
		return d_no;
	}

	public void setD_no(int d_no) {
		this.d_no = d_no;
	}

	public String getD_title() {
		return d_title;
	}

	public void setD_title(String d_title) {
		this.d_title = d_title;
	}

	public String getD_content() {
		return d_content;
	}

	public void setD_content(String d_content) {
		this.d_content = d_content;
	}

	public Date getD_startDate() {
		return d_startDate;
	}

	public void setD_startDate(Date d_startDate) {
		this.d_startDate = d_startDate;
	}

	public Date getD_endDate() {
		return d_endDate;
	}

	public void setD_endDate(Date d_endDate) {
		this.d_endDate = d_endDate;
	}

	public int getD_goal() {
		return d_goal;
	}

	public void setD_goal(int d_goal) {
		this.d_goal = d_goal;
	}

	public int getD_amount() {
		return d_amount;
	}

	public void setD_amount(int d_amount) {
		this.d_amount = d_amount;
	}

	public int getD_status() {
		return d_status;
	}

	public void setD_status(int d_status) {
		this.d_status = d_status;
	}

	public Date getD_regdate() {
		return d_regdate;
	}

	public void setD_regdate(Date d_regdate) {
		this.d_regdate = d_regdate;
	}

	public String getD_photo() {
		return d_photo;
	}

	public void setD_photo(String d_photo) {
		this.d_photo = d_photo;
	}
	
	private MultipartFile posterMF;


	public MultipartFile getPosterMF() {
		return posterMF;
	}

	public void setPosterMF(MultipartFile posterMF) {
		this.posterMF = posterMF;
	}
	
	
	
}
