package com.starwars.models;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Comment implements Cloneable {

	@Expose
	private int commentId;
	@Expose
	private int movieId;
	@Expose
	private String comment;
	@Expose
	private String date_time;
	@Expose
	private String ipAddress;
	@Expose
	private String self;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String url) {
		this.self = url;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
