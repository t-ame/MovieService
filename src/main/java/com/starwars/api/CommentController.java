package com.starwars.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.starwars.models.Comment;
import com.starwars.services.CommentService;
import com.starwars.services.CommentServiceImpl;
import com.starwars.utils.AppException;

@Path("/movies/{movieId}/comments")
public class CommentController {

	private CommentService commentService = new CommentServiceImpl();

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovieComments(@Context UriInfo uriInfo, @PathParam("movieId") int movieId) {
		try {
			JsonObject obj = new JsonObject();
			GsonBuilder builder = new GsonBuilder();
			builder.excludeFieldsWithoutExposeAnnotation();
			Gson gson = builder.create();
			List<Comment> comments = commentService.getComments(uriInfo, movieId);
			obj.add("result", new JsonParser().parse(gson.toJson(comments)));
			obj.addProperty("count", comments.size());

			Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
			obj.addProperty("self", self.getUri().toString());

			return Response.ok().entity(obj.toString()).links(self).build();
		} catch (AppException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("{\"error_message\": \"" + e.getMessage() + "\"}").build();
		}
	}

	@GET
	@Path("/{commentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getComment(@Context UriInfo uriInfo, @PathParam("movieId") int movieId,
			@PathParam("commentId") int commentId) {
		try {
			JsonObject obj = new JsonObject();
			GsonBuilder builder = new GsonBuilder();
			builder.excludeFieldsWithoutExposeAnnotation();
			Gson gson = builder.create();
			Comment comment = commentService.getComment(movieId, commentId);
			obj.add("comment", new JsonParser().parse(gson.toJson(comment)));

			Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
			obj.addProperty("self", self.getUri().toString());

			return Response.ok().entity(obj.toString()).links(self).build();
		} catch (AppException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("{\"error_message\": \"" + e.getMessage() + "\"}").build();
		}
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewComment(@Context UriInfo uriInfo, @Context HttpServletRequest request,
			@PathParam("movieId") int movieId, Comment comment) {
		try {
			JsonObject obj = new JsonObject();
			GsonBuilder builder = new GsonBuilder();
			builder.excludeFieldsWithoutExposeAnnotation();
			Gson gson = builder.create();
			comment.setMovieId(movieId);
			comment.setIpAddress(request.getRemoteAddr());
			Comment comment2 = commentService.addComment(comment);
			obj.add("comment", new JsonParser().parse(gson.toJson(comment2)));
			Link self = Link
					.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(String.valueOf(comment2.getCommentId())))
					.rel("self").build();
			obj.addProperty("self", self.getUri().toString());

			return Response.ok().entity(obj.toString()).links(self).build();
		} catch (AppException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("{\"error_message\": \"" + e.getMessage() + "\"}").build();
		}
	}

}
