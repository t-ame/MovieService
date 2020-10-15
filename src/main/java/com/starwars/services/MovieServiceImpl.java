package com.starwars.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.starwars.models.Movie;
import com.starwars.repos.MovieRepo;
import com.starwars.repos.MovieRepoImpl;
import com.starwars.utils.AppException;

public class MovieServiceImpl implements MovieService {

	private final static String base_url = "https://swapi.dev/api/";

	private MovieRepo moveRepo = new MovieRepoImpl();
	private CommentService commentService = new CommentServiceImpl();

	@Override
	public List<Movie> getMovieList() throws AppException {

		List<Movie> movies = new ArrayList<>();

		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(base_url + "films");

		HttpResponse response;
		StringBuffer result = new StringBuffer();
		try {
			response = client.execute(get);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		} catch (IOException e1) {
			throw new AppException("Starwars movie service is unavailable. Please try again later.", e1);
		}
		String responseData = result.toString();

		try {
			Gson gson = new Gson();
			JsonObject responseJson = gson.fromJson(responseData, JsonObject.class);
			movies = gson.fromJson(responseJson.get("results"), new TypeToken<List<Movie>>() {
			}.getType());
			updateMovieRepository(movies);
			for (Movie movie : movies) {
				movie.setComments(String.format("/movies/%d/comments", movie.getEpisode_id()));
				movie.setCharactersURL(String.format("/movies/%d/characters", movie.getEpisode_id()));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				movie.setReleaseDate(format.parse(movie.getRelease_date()));
				movie.setCommentCount(commentService.getCommentCount(movie.getEpisode_id()));
			}
		} catch (Exception e) {
			throw new AppException("An error occured when fetching movie data.", e);
		}
		movies.sort((x, y) -> x.getReleaseDate().compareTo(y.getReleaseDate()));

		return movies;
	}

	@Override
	public void updateMovieRepository(List<Movie> movies) throws AppException {
		Runnable runable = new Runnable() {
			public void run() {
				try {
					List<Movie> movies_ = new ArrayList<>();
					Set<Movie> existingMovies = moveRepo.fetchAllMovies();
					if (existingMovies.size() > 0) {
						for (Movie movie : movies) {
							if (existingMovies.add(movie)) {
								movies_.add(movie);
							}
						}
					} else {
						movies_ = movies;
					}
					moveRepo.saveNewMovies(movies_);
				} catch (AppException e) {
					e.printStackTrace();
				}
			}
		};
		Thread thread = new Thread(runable);
		thread.start();
	}

	@Override
	public Movie fetchMovieById(int movieId) throws AppException {
		return moveRepo.fetchMovieById(movieId);
	}

}
