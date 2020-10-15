package com.starwars.repos;

import java.util.List;

import com.starwars.models.Comment;
import com.starwars.models.SortDirection;
import com.starwars.utils.AppException;

public interface CommentRepo {

	Comment getCommentByID(int movieId, int commentID) throws AppException;

	List<Comment> getMovieComments(int movieId, SortDirection sortDirection) throws AppException;

	int countMovieComments(int movieId) throws AppException;

	Comment saveNewComment(Comment comment) throws AppException;

}
