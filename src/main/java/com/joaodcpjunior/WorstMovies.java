package com.joaodcpjunior;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WorstMovies {

	public List<Movie> findTenWorstMovies(String url) throws IOException {

		List<Movie> tenWorstMovies = new ArrayList<>();

		Document doc = Jsoup.connect(url).get();

		Element tbody = doc.getElementsByTag("tbody").first();

		for (int i = 0; i < 10; i++) {

			Element tr = tbody.getElementsByTag("tr").get(i);

			Element td = tr.getElementsByTag("td").get(1);

			Elements links = td.getElementsByTag("a");

			for (Element link : links) {
				String linkHref = link.attr("href");

				Movie movie = new Movie();
				movie.setMovieLink(linkHref);
				
				tenWorstMovies.add(movie);
			}

		}

		return tenWorstMovies;
	}
	
	public void setMovieName(List<Movie> tenWorstMovies) throws IOException {
				
		for (int i = 0; i < 10; i++) {
			String movieLink = tenWorstMovies.get(i).getMovieLink();
			
			String conectLink = ("https://www.imdb.com/" + movieLink);
			
			Document doc = Jsoup.connect(conectLink).get();
			
			Element originalTitle = doc.getElementsByClass("OriginalTitle__OriginalTitleText-jz9bzr-0 llYePj").first();
			
			if (originalTitle != null) {			
				tenWorstMovies.get(i).setName(originalTitle.text());
			} else {
				Element originalTitleOptional = doc.getElementsByClass("TitleHeader__TitleText-sc-1wu6n3d-0 dxSWFG").first();
				tenWorstMovies.get(i).setName(originalTitleOptional.text());
			}
			
		}
	}
	
	public void setMovieRating(List<Movie> tenWorstMovies) throws IOException {
		
		for (int i = 0; i < 10; i++) {
			String movieLink = tenWorstMovies.get(i).getMovieLink();
			
			String conectLink = ("https://www.imdb.com/" + movieLink);
			
			Document doc = Jsoup.connect(conectLink).get();
			
			Element rating = doc.getElementsByClass("AggregateRatingButton__RatingScore-sc-1ll29m0-1 iTLWoV").first();
			
			tenWorstMovies.get(i).setRating(rating.text());
			
		}
	}

}