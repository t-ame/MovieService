package com.starwars.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.google.gson.JsonObject;
import com.starwars.services.CharacterService;
import com.starwars.services.CharacterServiceImpl;
import com.starwars.utils.AppException;

@Path("/movies/{movieId}/characters")
public class CharacterController {

	private CharacterService characterService = new CharacterServiceImpl();

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovieComments(@Context UriInfo uriInfo, @PathParam("movieId") int movieId,
			@QueryParam("filtergender") String filtergender, @QueryParam("sortfield") String sortfield,
			@QueryParam("sortdirection") String sortdirection) {
		try {
			JsonObject obj = characterService.getCharacters(movieId, filtergender, sortfield, sortdirection);

			Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
			obj.addProperty("self", self.getUri().toString());

			return Response.ok().entity(obj.toString()).links(self).build();
		} catch (AppException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("{\"error_message\": \"" + e.getMessage() + "\"}").build();
		}
	}

}
