package com.task.core;

import com.task.api.request.UserDTO;
import com.task.auth.UserPrincipal;
import com.task.core.model.Role;
import com.task.core.model.User;
import com.task.db.AuthRepository;
import com.task.exceptions.UserNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void createUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        user.setRole(Role.USER);

        authRepository.insertUser(user);
    }

    public UserPrincipal login(UserDTO userDTO) {
        authRepository.findEmail(userDTO.getEmail()).orElseThrow(() -> new UserNotFoundException("No such email registered !"));
        User user = authRepository.findEmail(userDTO.getEmail()).get();

        if (!BCrypt.checkpw(userDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Bad Credentials !");
        } else {
            return new UserPrincipal(user);
        }
    }
}
