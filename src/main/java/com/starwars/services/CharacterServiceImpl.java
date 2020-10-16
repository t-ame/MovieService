package com.starwars.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.starwars.models.Character;
import com.starwars.models.Movie;
import com.starwars.models.SortDirection;
import com.starwars.models.SortField;
import com.starwars.utils.AppException;

public class CharacterServiceImpl implements CharacterService {

	MovieService movieService = new MovieServiceImpl();

	public List<Character> getCharacters(int movieId) throws AppException {

		Movie movie = movieService.fetchMovieById(movieId);

		List<String> charactersUrl = new ArrayList<>();
		List<Character> characters = new ArrayList<>();

		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(movie.getUrl());

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

			String responseData = result.toString();

			Gson gson = new Gson();
			JsonObject responseJson = gson.fromJson(responseData, JsonObject.class);
			charactersUrl = gson.fromJson(responseJson.get("characters"), new TypeToken<List<String>>() {
			}.getType());
			
			List<Thread> threads = new ArrayList<>();

			for (String url : charactersUrl) {
				threads.add(fetchCharacter(characters, url));
			}
			for(Thread thread : threads) {
				thread.join();
			}
		} catch (Exception e) {
			throw new AppException("Starwars movie service is unavailable. Please try again later.", e);
		}

		return characters;
	}


	private Thread fetchCharacter(List<Character> characters, String url) throws AppException {

		Runnable runable = new Runnable() {
			public void run() {
				HttpResponse response;
				CloseableHttpClient client = HttpClientBuilder.create().build();
				StringBuffer result = new StringBuffer();
				HttpGet get = new HttpGet(url);
				try {
					response = client.execute(get);
					BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					String line = "";
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}
					rd.close();

					String responseData = result.toString();

					Gson gson = new Gson();
					
					Character character = gson.fromJson(responseData, Character.class);

					synchronized (characters) {
						characters.add(character);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		};
		Thread thread = new Thread(runable);
		thread.start();
		
		return thread;
	}

	private List<Character> filterAndSort(List<Character> characters, String filterGender, SortField sortField,
			SortDirection sortDirection) {

		if (filterGender != null && !filterGender.trim().equals("")) {
			characters = characters.stream().filter(x -> x.getGender() != null && x.getGender().equalsIgnoreCase(filterGender))
					.collect(Collectors.toList());
		}
		Comparator<Character> c;
		switch (sortField.getName()) {
		case "gender":
			if (sortDirection == SortDirection.ASC) {
				c = (a, b) -> a.getGender().compareTo(b.getGender());
			} else {
				c = (a, b) -> b.getGender().compareTo(a.getGender());
			}
			break;
		case "height":
			if (sortDirection == SortDirection.ASC) {
				c = (a, b) -> a.getHeight().compareTo(b.getHeight());
			} else {
				c = (a, b) -> b.getHeight().compareTo(a.getHeight());
			}
			break;
		default:
			if (sortDirection == SortDirection.ASC) {
				c = (a, b) -> a.getName().compareTo(b.getName());
			} else {
				c = (a, b) -> b.getName().compareTo(a.getName());
			}
			break;
		}
		characters.sort(c);
		return characters;
	}

	@Override
	public JsonObject getCharacters(int movieId, String filterGender, String sortField, String sortDirection)
			throws AppException {

		JsonObject obj = new JsonObject();
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		SortField field = SortField.getField(sortField);
		SortDirection dir = SortDirection.getDirection(sortDirection);

		List<Character> characters = getCharacters(movieId);
		int totalCount = characters.size();
		List<Character> filteredAndSortedCharacters = filterAndSort(characters, filterGender, field, dir);
		int totalMatch = filteredAndSortedCharacters.size();

		float totalHeight = 0;
		for (Character c : filteredAndSortedCharacters)
			totalHeight += c.getHeight();

		obj.add("result", new JsonParser().parse(gson.toJson(filteredAndSortedCharacters)));
		obj.addProperty("totalCount", totalCount);
		obj.addProperty("totalMatch", totalMatch);
		obj.addProperty("totalheighCm", totalHeight);
		double in = totalHeight / 2.54;
		int ft = (int) (in / 12);
		in = in % 12;
		obj.addProperty("totalheighFt", String.format("%d", ft));
		obj.addProperty("totalheighIn", String.format("%.2f", in));

		return obj;
	}

}
