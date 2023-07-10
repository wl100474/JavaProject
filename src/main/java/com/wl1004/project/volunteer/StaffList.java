package com.wl1004.project.volunteer;

public class StaffList {
	private int v_no;
	private String v_id;

	public StaffList() {
		// TODO Auto-generated constructor stub
	}

	public StaffList(int v_no, String v_id) {
		super();
		this.v_no = v_no;
		this.v_id = v_id;
	}

	public int getV_no() {
		return v_no;
	}

	public void setV_no(int v_no) {
		this.v_no = v_no;
	}

	public String getV_id() {
		return v_id;
	}

	public void setV_id(String v_id) {
		this.v_id = v_id;
	}

}
