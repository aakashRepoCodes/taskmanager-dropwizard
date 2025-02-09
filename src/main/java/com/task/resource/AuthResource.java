package com.task.resource;

import com.task.api.request.UserDTO;
import com.task.auth.UserPrincipal;
import com.task.core.AuthService;
import com.task.core.model.User;
import com.task.db.AuthRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    private AuthService authService;

    public AuthResource(AuthRepository authRepository) {
        this.authService = new AuthService(authRepository);
    }

    @Path("/register")
    @POST
    public void registerUser(UserDTO userDTO) {
        authService.createUser(userDTO);
    }

    @Path("/login")
    @POST
    public Response loginUser(UserDTO userDTO) {
        UserPrincipal user = authService.login(userDTO);
        return Response.status(Response.Status.OK).entity(user).build();
    }
}
