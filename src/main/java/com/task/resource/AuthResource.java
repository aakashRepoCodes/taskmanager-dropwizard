package com.task.resource;

import com.task.api.request.SignUpDTO;
import com.task.core.AuthService;
import com.task.db.AuthRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    private AuthService authService;

    public AuthResource(AuthRepository authRepository) {
        this.authService = new AuthService(authRepository);
    }

    @POST
    public void registerUser(SignUpDTO signUpDTO) {
        authService.createUser(signUpDTO);
    }
}
