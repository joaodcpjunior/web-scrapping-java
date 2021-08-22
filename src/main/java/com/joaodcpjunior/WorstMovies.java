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
	
	public void setIMDbMovieID(List<Movie> tenWorstMovies, String url) throws IOException {

		Document doc = Jsoup.connect(url).get();

		Element tbody = doc.getElementsByTag("tbody").first();

		for (int i = 0; i < 10; i++) {

			Element tr = tbody.getElementsByTag("tr").get(i);

			Element td = tr.getElementsByTag("td").get(3);

			Elements IMDbIDs = td.getElementsByAttribute("data-titleid");

			for (Element IMDbID : IMDbIDs) {
				
				String IMDbMovieID = IMDbID.attr("data-titleid");

				tenWorstMovies.get(i).setIMDbMovieID(IMDbMovieID);
			}
				
		}

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
	
	public void setMovieRating(List<Movie> tenWorstMovies, String url) throws IOException {
		
		Document doc = Jsoup.connect(url).get();

		Element tbody = doc.getElementsByTag("tbody").first();

		for (int i = 0; i < 10; i++) {

			Element tr = tbody.getElementsByTag("tr").get(i);

			Element td = tr.getElementsByTag("td").get(2);

			Elements ratings = td.getElementsByAttribute("title");

			for (Element rating : ratings) {
				
				String movieRating = rating.text();

				tenWorstMovies.get(i).setRating(movieRating);
			}
				
		}
	}
	
	public void setMovieDirectors(List<Movie> tenWorstMovies) throws IOException {
		
		for (int i = 0; i < 10; i++) {
			
			String movieLink = tenWorstMovies.get(i).getMovieLink();
			
			String conectLink = ("https://www.imdb.com/" + movieLink);
			
			Document doc = Jsoup.connect(conectLink).get();
			
			Element ul = doc.getElementsByClass("ipc-inline-list ipc-inline-list--show-dividers ipc-inline-list--inline ipc-metadata-list-item__list-content baseAlt").first();
			
			Elements lis = ul.getElementsByTag("li");
			
			List<String> directors = new ArrayList<>();
						
			for (Element li : lis) {
				
				String director = li.getElementsByClass("ipc-metadata-list-item__list-content-item ipc-metadata-list-item__list-content-item--link").first().text();
								
				directors.add(li.elementSiblingIndex(), director);			
			}
			
			tenWorstMovies.get(i).setDirectors(directors);
			
		}
	}
	
	public void setMovieStars(List<Movie> tenWorstMovies) throws IOException {
		
		for (int i = 0; i < 10; i++) {
			
			String movieLink = tenWorstMovies.get(i).getMovieLink();
			
			String conectLink = ("https://www.imdb.com/" + movieLink);
			
			Document doc = Jsoup.connect(conectLink).get();
			
			Element ul = doc.getElementsByClass("ipc-inline-list ipc-inline-list--show-dividers ipc-inline-list--inline ipc-metadata-list-item__list-content baseAlt").get(2);
			
			Elements lis = ul.getElementsByTag("li");
			
			List<String> stars = new ArrayList<>();
						
			for (Element li : lis) {
				
				String star = li.getElementsByClass("ipc-metadata-list-item__list-content-item ipc-metadata-list-item__list-content-item--link").first().text();
								
				stars.add(li.elementSiblingIndex(), star);			
			}
			
			tenWorstMovies.get(i).setStars(stars);
			
		}
	}
	
	public void setMovieComment(List<Movie> tenWorstMovies) throws IOException {
		
		for (int i = 0; i < 10; i++) {
			
			String IMDbMovieID = tenWorstMovies.get(i).getIMDbMovieID();
			
			String conectLink = ("https://www.imdb.com/title/" + IMDbMovieID + "/reviews?sort=helpfulnessScore&dir=desc&ratingFilter=5");
			
			Document doc = Jsoup.connect(conectLink).get();
			
			Element comment = doc.getElementsByClass("text show-more__control").first();
			
			tenWorstMovies.get(i).setComment(comment.text());
			
		}
	}

}
