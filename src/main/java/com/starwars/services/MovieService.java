package com.starwars.services;

import java.util.List;

import com.starwars.models.Movie;
import com.starwars.utils.AppException;

public interface MovieService {

	List<Movie> getMovieList() throws AppException;

	void updateMovieRepository(List<Movie> movies) throws AppException;

	Movie fetchMovieById(int movieId) throws AppException;

}
