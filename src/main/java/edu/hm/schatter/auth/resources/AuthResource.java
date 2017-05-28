package edu.hm.schatter.auth.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.schatter.auth.businesslayer.AuthService;
import edu.hm.schatter.auth.businesslayer.AuthServiceImpl;
import edu.hm.schatter.auth.businesslayer.AuthServiceResult;
import edu.hm.schatter.auth.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Provides the API for the share it service.
 */
@Path("/")
public class AuthResource {

    //private final MediaService mediaService = new MediaServiceImpl();
    private final AuthService authService = new AuthServiceImpl();

    @GET
    @Path("/check/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkToken(@PathParam("token")String token) {

        final AuthServiceResult result = AuthServiceResult.OK;
        final String tokenInfo = authService.checkToken(token);

        return Response
                .status(result.getStatus())
                .entity(tokenInfo)
                .build();
    }

    @POST
    @Path("/users/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(User user) {

        final String token = authService.authenticateUser(user.getEmail(), user.getPassword());
        final AuthServiceResult result;
        final String json;

        if (token == null) {
            result = AuthServiceResult.INCORRECT_INFORMATION;
            json = result.getJSON();
        } else {
            result = AuthServiceResult.OK;
            json = String.format("{\"token\":\"%s\"}", token);
        }

        return Response
            .status(result.getStatus())
            .entity(json)
            .build();
    }

    /**
     * Converts objects to json.
     * @param o object to be converted.
     * @return json.
     * @throws JsonProcessingException object could not be converted to json.
     */
    private String convertToJSON(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }
}
