package com.joaodcpjunior;

import java.util.ArrayList;
import java.util.List;

public class Movie {

	private String movieLink;
	private String IMDbMovieID;
	private String name;
	private String rating;
	private List<String> directors = new ArrayList<>();
	private List<String> stars = new ArrayList<>();
	private String comment;

	public Movie() {
	}

	public String getIMDbMovieID() {
		return IMDbMovieID;
	}

	public void setIMDbMovieID(String iMDbMovieID) {
		IMDbMovieID = iMDbMovieID;
	}

	public String getMovieLink() {
		return movieLink;
	}

	public void setMovieLink(String movieLink) {
		this.movieLink = movieLink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public List<String> getStars() {
		return stars;
	}

	public void setStars(List<String> stars) {
		this.stars = stars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "\nMovie: {"
				+"\n	movieLink = " + movieLink + ","
				+"\n	IMDbMovieID = " + IMDbMovieID + ","
				+"\n	name = " + name + ","
				+"\n	rating = "+ rating + ","
				+"\n	directors = "+ directors + ","
				+"\n	stars = " + stars + ","
				+"\n	comment = " + comment + ","
				+"\n}";
	}

}
