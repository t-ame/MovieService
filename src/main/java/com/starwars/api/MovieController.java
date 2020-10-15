package com.starwars.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
import com.starwars.models.Movie;
import com.starwars.services.MovieService;
import com.starwars.services.MovieServiceImpl;
import com.starwars.utils.AppException;

@Path("/movies")
public class MovieController {

	MovieService movieService = new MovieServiceImpl();

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovies(@Context UriInfo uriInfo) {
		try {
			JsonObject obj = new JsonObject();
			GsonBuilder builder = new GsonBuilder();
			builder.excludeFieldsWithoutExposeAnnotation();
			Gson gson = builder.create();
			List<Movie> movies = movieService.getMovieList();
			obj.add("result", new JsonParser().parse(gson.toJson(movies)));
			obj.addProperty("count", movies.size());

			Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
			obj.addProperty("self", self.getUri().toString());

			return Response.ok().entity(obj.toString()).links(self).build();
		} catch (AppException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"error_message\": \"" + e.getMessage() + "\"}").build();
		}
	}

}
