package com.starwars.repos;

import java.util.List;
import java.util.Set;

import com.starwars.models.Movie;
import com.starwars.utils.AppException;

public interface MovieRepo {

	Movie fetchMovieById(int movieId) throws AppException;

	Set<Movie> fetchAllMovies() throws AppException;

	Movie saveNewMovie(Movie movie) throws AppException;

	int saveNewMovies(List<Movie> movies) throws AppException;

}
