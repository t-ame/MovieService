package com.starwars.services;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;

import com.starwars.models.Comment;
import com.starwars.models.SortDirection;
import com.starwars.repos.CommentRepo;
import com.starwars.repos.CommentRepoImpl;
import com.starwars.utils.AppException;

public class CommentServiceImpl implements CommentService {

	private CommentRepo commentRepo = new CommentRepoImpl();

	@Override
	public Comment addComment(Comment comment) throws AppException {
		if (comment == null) {
			throw new AppException("Comment data is required");
		}
		if (comment.getComment().length() > 500) {
			throw new AppException("Comment cannot have more than 500 characters");
		}
		return commentRepo.saveNewComment(comment);
	}

	@Override
	public Comment getComment(int movieId, int commentId) throws AppException {
		return commentRepo.getCommentByID(movieId, commentId);
	}

	@Override
	public List<Comment> getComments(UriInfo uriInfo, int movieId) throws AppException {
		List<Comment> comments = commentRepo.getMovieComments(movieId, SortDirection.DESC);
		for (Comment comment : comments) {
			Link self = Link
					.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(comment.getCommentId())))
					.rel("self").build();
			comment.setSelf(self.getUri().toString());
		}
		return comments;
	}

	@Override
	public int getCommentCount(int movieId) throws AppException {
		return commentRepo.countMovieComments(movieId);
	}

}
