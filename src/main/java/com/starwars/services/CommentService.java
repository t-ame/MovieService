package com.starwars.services;

import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.starwars.models.Comment;
import com.starwars.utils.AppException;

public interface CommentService {

	Comment addComment(Comment comment) throws AppException;

	int getCommentCount(int movieId) throws AppException;

	Comment getComment(int movieId, int commentId) throws AppException;

	List<Comment> getComments(UriInfo uriInfo, int movieId) throws AppException;

}
