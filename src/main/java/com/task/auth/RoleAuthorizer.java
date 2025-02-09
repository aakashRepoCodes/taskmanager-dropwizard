package com.task.auth;

import io.dropwizard.auth.Authorizer;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.checkerframework.checker.nullness.qual.Nullable;

public class RoleAuthorizer implements Authorizer<UserPrincipal> {

    @Override
    public boolean authorize(UserPrincipal userPrincipal, String s, @Nullable ContainerRequestContext containerRequestContext) {
        return userPrincipal.getRole().name().equals(s);
    }

}