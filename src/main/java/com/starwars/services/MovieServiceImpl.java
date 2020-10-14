package com.starwars.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.starwars.models.Movie;

public class MovieServiceImpl implements MovieService {
	private final static String base_url = "https://swapi.dev/api/";
//	private final static String api_key = "https://paygatetest.softalliance.com/index.php/";

	public MovieServiceImpl() {
	}

	@Override
	public String getMovieList() throws IOException {

		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet post = new HttpGet(base_url + "films");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
//		post.setHeader("Authorization", "Bearer " + api_key);
//		post.setHeader("User-Agent", "PostmanRuntime/7.26.1");
//		HttpEntity entity = new ByteArrayEntity(payload.toString().getBytes("UTF-8"));
//		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		String responseData = result.toString();

		System.out.println(responseData);

		try {
			Gson gson = new Gson();
			JsonObject responseJson = gson.fromJson(responseData, JsonObject.class);
			List<Movie> movies = gson.fromJson(responseJson.get("results"), new TypeToken<List<Movie>>() {
			}.getType());

			for (Movie movie : movies) {
				System.out.println(movie);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseData;
	}

	public static void main(String[] args) {
		try {
			System.out.println(new MovieServiceImpl().getMovieList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
