package com.yuanyue.www.rongnannews.ui.entity;

public class Comment {

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	private String news;
	
	private String comment;
	
	private String time;
	
	public Comment(String news, String comment, String time, String user) {
		super();
		this.news = news;
		this.comment = comment;
		this.time = time;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [NewsActivity=" + news + ", comment=" + comment + ", time="
				+ time + ", user=" + user + "]";
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	private String user;
}
