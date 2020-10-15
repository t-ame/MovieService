package com.starwars.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.starwars.database.Database;
import com.starwars.models.Comment;
import com.starwars.models.SortDirection;
import com.starwars.utils.AppException;

public class CommentRepoImpl implements CommentRepo {

	@Override
	public Comment saveNewComment(Comment comment) throws AppException {
		Comment comment2;
		try {
			comment2 = (Comment) comment.clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
			comment2 = comment;
		}
		String sql = "INSERT INTO COMMENTS (COMMENT, MOVIEID, IPADDRESS) VALUES (?, ?, ?)";

		try (Connection con = Database.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, comment.getComment());
			ps.setInt(2, comment.getMovieId());
			ps.setString(3, comment.getIpAddress());

			if (ps.executeUpdate() > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					comment2.setCommentId(rs.getInt(1));
				}
				rs.close();
			} else {
				throw new AppException("An error occured. Please try again later.");
			}
		} catch (SQLException e) {
			throw new AppException("An error occured. Please try again later.", e);
		}

		return comment2;
	}

	@Override
	public Comment getCommentByID(int movieId, int commentID) throws AppException {
		String sql = "SELECT COMMENTID, MOVIEID, IPADDRESS, STAMPDATE, COMMENT FROM COMMENTS WHERE MOVIEID = ? AND COMMENTID = ?";
		Comment comment = new Comment();

		try (Connection con = Database.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, movieId);
			ps.setInt(2, commentID);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				comment.setCommentId(rs.getInt("COMMENTID"));
				comment.setMovieId(rs.getInt("MOVIEID"));
				comment.setIpAddress(rs.getString("IPADDRESS"));
				comment.setDate_time(rs.getString("STAMPDATE"));
				comment.setComment(rs.getString("COMMENT"));
			} else {
				throw new AppException(
						String.format("Comment with ID: %d does not exist for movie with ID: %d", commentID, movieId));
			}
			rs.close();
		} catch (SQLException e) {
			throw new AppException("An error occured. Please try again later.", e);
		}
		return comment;
	}

	@Override
	public List<Comment> getMovieComments(int movieId, SortDirection sortDirection) throws AppException {

		String sql = "SELECT COMMENTID, MOVIEID, IPADDRESS, STAMPDATE, COMMENT "
				+ "FROM COMMENTS WHERE MOVIEID = ? ORDER BY STAMPDATE " + sortDirection.getName();
		List<Comment> comments = new ArrayList<>();

		try (Connection con = Database.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, movieId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();

				comment.setCommentId(rs.getInt("COMMENTID"));
				comment.setMovieId(rs.getInt("MOVIEID"));
				comment.setIpAddress(rs.getString("IPADDRESS"));
				comment.setDate_time(rs.getString("STAMPDATE"));
				comment.setComment(rs.getString("COMMENT"));

				comments.add(comment);
			}
			rs.close();
		} catch (SQLException e) {
			throw new AppException("An error occured. Please try again later.", e);
		}

		return comments;
	}

	@Override
	public int countMovieComments(int movieId) {
		String sql = "SELECT COUNT(COMMENTID) COUNT_ FROM COMMENTS WHERE MOVIEID = ?";
		int commentCount = 0;

		try (Connection con = Database.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, movieId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				commentCount = rs.getInt("COUNT_");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentCount;
	}

}
