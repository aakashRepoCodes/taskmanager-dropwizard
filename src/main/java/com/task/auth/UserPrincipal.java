package com.task.auth;

import com.task.core.model.Role;
import com.task.core.model.User;

import java.security.Principal;

public class UserPrincipal implements Principal {

    private final User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getEmail();
    }

    public Role getRole() {
        return user.getRole();
    }
}
