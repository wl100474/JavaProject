package com.wl1004.project.comment;

import java.util.Date;

public class Comment {
	private int c_no;
	private int v_no;
	private String c_id;
	private String c_content;
	private Date c_regdate;
	private int c_indent;
	private int c_ansnum;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(int c_no, int v_no, String c_id, String c_content, Date c_regdate, int c_indent, int c_ansnum) {
		super();
		this.c_no = c_no;
		this.v_no = v_no;
		this.c_id = c_id;
		this.c_content = c_content;
		this.c_regdate = c_regdate;
		this.c_indent = c_indent;
		this.c_ansnum = c_ansnum;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getV_no() {
		return v_no;
	}

	public void setV_no(int v_no) {
		this.v_no = v_no;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public Date getC_regdate() {
		return c_regdate;
	}

	public void setC_regdate(Date c_regdate) {
		this.c_regdate = c_regdate;
	}

	public int getC_indent() {
		return c_indent;
	}

	public void setC_indent(int c_indent) {
		this.c_indent = c_indent;
	}

	public int getC_ansnum() {
		return c_ansnum;
	}

	public void setC_ansnum(int c_ansnum) {
		this.c_ansnum = c_ansnum;
	}

}
