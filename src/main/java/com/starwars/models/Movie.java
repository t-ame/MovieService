package com.starwars.models;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Movie {
	@Expose
	private String title;
	@Expose
	private int episode_id;
	@Expose
	private String opening_crawl;
	private String director;
	private String producer;
	@Expose
	private String release_date;
	private Date releaseDate;
	@Expose
	private int commentCount;
	@Expose
	private String comments;
	@Expose
	private String charactersURL;
	private String url;

	public String getCharactersURL() {
		return charactersURL;
	}

	public void setCharactersURL(String charactersURL) {
		this.charactersURL = charactersURL;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEpisode_id() {
		return episode_id;
	}

	public void setEpisode_id(int episode_id) {
		this.episode_id = episode_id;
		this.setComments(String.format("/starwars/movies/%d/comments", episode_id));
		this.setCharactersURL(String.format("/starwars/movies/%d/characters", episode_id));
	}

	public String getOpening_crawl() {
		return opening_crawl;
	}

	public void setOpening_crawl(String opening_crawl) {
		this.opening_crawl = opening_crawl;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + episode_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (episode_id != other.episode_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
