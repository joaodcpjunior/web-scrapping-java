package com.joaodcpjunior;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) throws IOException {

		WorstMovies worstMovies = new WorstMovies();
		
		List<Movie> movies = new ArrayList<>();
		
		movies.addAll(worstMovies.findTenWorstMovies("https://www.imdb.com/chart/bottom"));
		
		//worstMovies.setMovieName(movies);
		
		//worstMovies.setMovieRating(movies);
		
		System.out.println(movies);
	}
}
