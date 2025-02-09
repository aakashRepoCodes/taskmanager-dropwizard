package com.task.auth;

import com.task.api.request.UserDTO;
import com.task.core.AuthService;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class BasicAuthenticator implements Authenticator<BasicCredentials, UserPrincipal> {

    private final AuthService authService;

    public BasicAuthenticator(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Optional<UserPrincipal> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        UserPrincipal user = authService.login(new UserDTO(basicCredentials.getUsername(), basicCredentials.getPassword()));
        return Optional.ofNullable(user);
    }
}
