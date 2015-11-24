package com.yuanyue.www.rongnannews.ui.entity;

public class News {

	private String title;
	
	private String reporter;
	
	private String context;
	
	private String time;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String report) {
		this.reporter = report;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public News(String title, String report, String context, String time) {
		super();
		this.title = title;
		this.reporter = report;
		this.context = context;
		this.time = time;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", report=" + reporter + ", context="
				+ context + ", time=" + time + "]";
	}

	public News() {
		// TODO Auto-generated constructor stub
	}

}
