package com.starwars.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.starwars.database.Database;
import com.starwars.models.Movie;
import com.starwars.utils.AppException;

public class MovieRepoImpl implements MovieRepo {

	@Override
	public Set<Movie> fetchAllMovies() throws AppException {
		String sql = "SELECT MOVIEID, URL FROM MOVIES";
		Set<Movie> movies = new HashSet<>();

		try (Connection con = Database.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setEpisode_id(rs.getInt("MOVIEID"));
				movie.setUrl(rs.getString("URL"));
				movies.add(movie);
			}
			rs.close();
		} catch (SQLException e) {
			throw new AppException("An error occured. Please try again later.", e);
		}
		return movies;
	}

	@Override
	public Movie saveNewMovie(Movie movie) throws AppException {
		Movie movie_ = null;
		String sql = "INSERT INTO MOVIES (MOVIEID, URL) VALUES (?, ?)";

		try (Connection con = Database.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, movie.getEpisode_id());
			ps.setString(2, movie.getUrl());

			movie_ = ps.executeUpdate() > 0 ? movie : null;

		} catch (SQLException e) {
			throw new AppException("An error occured. Please try again later.", e);
		}
		return movie_;
	}

	@Override
	public int saveNewMovies(List<Movie> movies) throws AppException {
		int count = 0;
		for (Movie movie : movies) {
			if (saveNewMovie(movie) != null)
				++count;
		}
		return count;
	}

	@Override
	public Movie fetchMovieById(int movieId) throws AppException {
		String sql = "SELECT MOVIEID, URL FROM MOVIES WHERE MOVIEID = ?";
		Movie movie = new Movie();

		try (Connection con = Database.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, movieId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				movie.setEpisode_id(rs.getInt("MOVIEID"));
				movie.setUrl(rs.getString("URL"));
			}
			rs.close();
		} catch (SQLException e) {
			throw new AppException("An error occured. Please try again later.", e);
		}
		return movie;
	}

}
